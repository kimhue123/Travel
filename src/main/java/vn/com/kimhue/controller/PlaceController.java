package vn.com.kimhue.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import vn.com.kimhue.dao.CategoryDao;
import vn.com.kimhue.dao.DetailPlaceDao;
import vn.com.kimhue.dao.ImgDao;
import vn.com.kimhue.dao.PlaceDao;
import vn.com.kimhue.dao.UserDao;
import vn.com.kimhue.model.CategoryModel;
import vn.com.kimhue.model.DetailPlaceModel;
import vn.com.kimhue.model.ImgModel;
import vn.com.kimhue.model.PlaceModel;
import vn.com.kimhue.model.UserModel;

@Controller
public class PlaceController {
	@Autowired
	private PlaceDao placeDao;
	@Autowired
	private DetailPlaceDao detailPlaceDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ImgDao imgDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/detail")
	public String detailPlace(Model model, @RequestParam(name = "id") int id) {
		PlaceModel place = placeDao.getPlaceById(id);
		List<DetailPlaceModel> detailPlace = placeDao.getListDetailPlaceByIdPlace(id);
		model.addAttribute("place", place);
		model.addAttribute("listdetail", detailPlace);
		return "about";
	}

	@RequestMapping(value = "/addPlace")
	public String formAddPlace(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String idFacebook = (String) session.getAttribute("userId");
		if (idFacebook != null) {
			List<CategoryModel> categoryList = categoryDao.getList();
			model.addAttribute("category", categoryList);
			return "addPlace";
		} else {
			return "redirect:/index";
		}

	}

	@RequestMapping(value = "/savePlace", method = RequestMethod.POST)
	public String savePlace(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("select") int idCategory, @RequestParam("content") String content,
			@RequestParam(name = "detail", required = false) ArrayList<String> detailList,
			@RequestParam("files") ArrayList<MultipartFile> files, @RequestParam("file") MultipartFile file,
			@RequestParam("cityLat") String lat, @RequestParam("cityLng") String lng, HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		String idFacebook = (String) session.getAttribute("userId");
		UserModel user = userDao.findUserByIdFacebook(idFacebook);
		String filename = "";
		DetailPlaceModel detail;
		int count = 0;
		ImgModel imgg;
		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			filename = file.getOriginalFilename() + "1" + System.nanoTime();
			Path path = Paths.get("E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/" + filename);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		float latn = 0;
		if (!lat.equals("")) {
			latn = Float.parseFloat(lat);
		}
		float lngn = 0;
		if (!lng.equals("")) {
			lngn = Float.parseFloat(lng);
		}
		ImgModel img = new ImgModel(0, filename);
		imgDao.uploadImg(img);
		PlaceModel placeModel = new PlaceModel(0, name, content, address, latn, lngn);
		placeModel.setCategory(new CategoryModel(idCategory));
		placeModel.setImage(img);
		placeModel.setUser(user);
		placeDao.save(placeModel);
		if (placeModel != null) {
			for (MultipartFile file1 : files) {
				if (file1.isEmpty()) {
					continue; // next pls
				}
				try {
					byte[] bytes = file1.getBytes();
					String namepicture = file1.getOriginalFilename() + System.nanoTime();
					Path path = Paths
							.get("E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/" + namepicture);
					Files.write(path, bytes);
					imgg = new ImgModel(0, namepicture);
					imgDao.uploadImg(imgg);
					String detailL = "";
					if (detailList.size() > count) {
						detailL = detailList.get(count).toString();
					}
					detail = new DetailPlaceModel(0, detailL);
					detail.setImage(imgg);
					detail.setPlace(placeModel);
					detailPlaceDao.save(detail);
				} catch (IOException e) {
					e.printStackTrace();
				}
				count++;
			}
		}
		return "redirect:/manager";
	}

	@RequestMapping(value = "/edit")
	public String formEditPlace(Model model, @RequestParam(name = "id") int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String idFacebook = (String) session.getAttribute("userId");
		if (idFacebook != null) {
			List<CategoryModel> categoryList = categoryDao.getList();
			PlaceModel place = placeDao.getPlaceById(id);
			List<DetailPlaceModel> detailPlace = placeDao.getListDetailPlaceByIdPlace(id);
			if (detailPlace.isEmpty()) {
				detailPlace.add(0, new DetailPlaceModel(1, "xxxx"));
				model.addAttribute("listdetailempty", detailPlace);
			} else {
				model.addAttribute("listdetail", detailPlace);
			}
			model.addAttribute("place", place);

			model.addAttribute("category", categoryList);
			return "editPlace";
		} else {
			return "redirect:/index";
		}

	}

	@RequestMapping(value = "/manager")
	public String managerPlace(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String idFacebook = (String) session.getAttribute("userId");
		if (idFacebook != null) {
			List<CategoryModel> categoryList = categoryDao.getList();
			List<PlaceModel> placeL = placeDao.getListByIdUser(idFacebook);
			model.addAttribute("place", placeL);
			model.addAttribute("listc", categoryList);
			return "manager";
		} else {
			return "redirect:/index";
		}

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPlace(@RequestParam(name = "id") int id, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("select") int idCategory,
			@RequestParam("content") String content, @RequestParam("detailOld") ArrayList<String> detailListOld,
			@RequestParam("fileOld") ArrayList<MultipartFile> filesOld,
			@RequestParam("detail") ArrayList<String> detailList, @RequestParam("files") ArrayList<MultipartFile> files,
			@RequestParam("file") MultipartFile file, @RequestParam("lat") String lat, @RequestParam("lng") String lng,
			HttpServletRequest request) {
		PlaceModel place = placeDao.getPlaceById(id);
		List<DetailPlaceModel> detailPlace = placeDao.getListDetailPlaceByIdPlace(id);
		String filename = "";
		DetailPlaceModel detail;
		int countNew = 0;
		int countOld = 0;
		ImgModel imgg;
		ImgModel img = place.getImage();
		if (!file.isEmpty()) {
			String pathOld = "E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/"
					+ place.getImage().getName();
			File fileOld = new File(pathOld);
			if (fileOld.exists()) {
				fileOld.delete();
			}
			try {
				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				filename = file.getOriginalFilename() + "1" + System.nanoTime();
				Path path = Paths.get("E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/" + filename);
				Files.write(path, bytes);

			} catch (IOException e) {
				e.printStackTrace();
			}
			img.setName(filename);
		}
		float latn = 0;
		if (!lat.equals("")) {
			latn = Float.parseFloat(lat);
		} else {
			latn = place.getLat();
		}
		float lngn = 0;
		if (!lng.equals("")) {
			lngn = Float.parseFloat(lng);
		} else {
			lngn = place.getLng();
		}

		imgDao.uploadImg(img);
		PlaceModel placeModel = new PlaceModel(place.getId(), name, content, address, latn, lngn);
		placeModel.setCategory(new CategoryModel(idCategory));
		placeModel.setImage(img);
		placeModel.setUser(place.getUser());
		placeDao.save(placeModel);
		if (placeModel != null) {
			if (detailListOld.size() > 0) {
				for (int i = 0; i < filesOld.size(); i++) {
					detail = detailPlace.get(countOld);
					if (!filesOld.get(i).isEmpty()) {
						imgg = detail.getImage();
						String pathOld = "E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/"
								+ imgg.getName();
						File fileOld = new File(pathOld);
						if (fileOld.exists()) {
							fileOld.delete();
						}
						try {
							byte[] bytes = filesOld.get(countOld).getBytes();
							String namepicture = filesOld.get(countOld).getOriginalFilename() + System.nanoTime();
							Path path = Paths.get(
									"E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/" + namepicture);
							Files.write(path, bytes);
							imgg.setName(namepicture);
							imgDao.uploadImg(imgg);
							detail.setImage(imgg);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					String detailL = "";
					if (detailListOld.size() > countOld) {
						detailL = detailListOld.get(countOld).toString();
					}
					detail.setContent(detailL);
					detail.setPlace(placeModel);
					detailPlaceDao.save(detail);
					countOld++;
				}
			}
			for (MultipartFile file1 : files) {
				if (file1.isEmpty()) {
					continue; // next pls
				}
				try {
					byte[] bytes = file1.getBytes();
					String namepicture = file1.getOriginalFilename() + System.nanoTime();
					Path path = Paths
							.get("E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/" + namepicture);
					Files.write(path, bytes);
					imgg = new ImgModel(0, namepicture);
					imgDao.uploadImg(imgg);
					String detailL = "";
					if (detailList.size() > countNew) {
						detailL = detailList.get(countNew).toString();
					}
					detail = new DetailPlaceModel(0, detailL);
					detail.setImage(imgg);
					detail.setPlace(placeModel);
					detailPlaceDao.save(detail);
				} catch (IOException e) {
					e.printStackTrace();
				}
				countNew++;
			}
		}
		return "redirect:/manager";
	}

	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String deletePlace(@RequestParam(name = "id") int id) {
		PlaceModel place = placeDao.getPlaceById(id);
		List<DetailPlaceModel> detailPlace = placeDao.getListDetailPlaceByIdPlace(id);
		for (DetailPlaceModel detail : detailPlace) {
			String pathOld = "E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/"
					+ detail.getImage().getName();
			File file = new File(pathOld);
			if (file.exists()) {
				file.delete();
			}
		}
		String pathOld = "E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/"
				+ place.getImage().getName();
		File file = new File(pathOld);
		if (file.exists()) {
			file.delete();
		}
		placeDao.deletePlaceById(id);
		return "redirect:/manager";
	}
}
