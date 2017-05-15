package vn.com.kimhue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.kimhue.dao.CategoryDao;
import vn.com.kimhue.dao.PlaceDao;
import vn.com.kimhue.dao.SlideDao;
import vn.com.kimhue.model.CategoryModel;
import vn.com.kimhue.model.PlaceModel;
import vn.com.kimhue.model.SlideModel;

@Controller
public class MainController {
	@Autowired
	private SlideDao slideDao;
	@Autowired
	private  CategoryDao categoryDao;
	@Autowired 
	private PlaceDao placeDao;
	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/index")
	public String index(Model model, @RequestParam(name = "id", required = false) String id) {
		List<CategoryModel> listC = categoryDao.getList();
		List<SlideModel> listSlide = slideDao.getList();
		SlideModel slide = listSlide.get(0);
		listSlide = listSlide.subList(1, 3);
		model.addAttribute("listc",listC);
		model.addAttribute("lists",listSlide);
		model.addAttribute("slideActive",slide);
		if (id != null && !"all".equals(id)) {
			int idCat = Integer.parseInt(id);
			List<PlaceModel> listP = placeDao.getListByIdCategory(idCat);
			model.addAttribute("listp", listP);
			return "index";
		} else {
			List<PlaceModel> listP = placeDao.getList();
			model.addAttribute("listp", listP);
			return "index";
		}
	}

}
