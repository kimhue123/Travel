package vn.com.kimhue.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import liquibase.util.file.FilenameUtils;
import vn.com.kimhue.bean.AccessToken;
import vn.com.kimhue.dao.UserDao;
import vn.com.kimhue.model.PlaceModel;
import vn.com.kimhue.model.UploadFile;
import vn.com.kimhue.model.UserModel;;

@Controller
public class Test {
	@Autowired
    ServletContext servletContext;
	private UserDao userDao;
	  private Facebook facebook; 
	  private ConnectionRepository connectionRepository ;
	  
	  
	@RequestMapping(value = "test")
	public String hello(Model model) {
		return "test";

	}

	@RequestMapping(value = "/getJson", method = RequestMethod.POST)
	@ResponseBody
	public String addNewWorker(@RequestBody AccessToken accessToken,HttpServletRequest request) {
		String token = accessToken.getToken();
		facebook = new FacebookTemplate(token);
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		if (profile != null) {
			String url = "http://graph.facebook.com/" + profile.getId() + "/picture?type=square";
			HttpSession session = request.getSession();
			session.setAttribute("userId", profile.getId());
			session.setAttribute("url", url);
			if (userDao.countUserByIdFacebook(profile.getId()) == 0) {
				UserModel userModel = new UserModel(0, profile.getName(), url, profile.getId(), 0);
				userDao.save(userModel);
			}
		}

		return token;
	}

	@RequestMapping(value = "formTest")
	public String test(@ModelAttribute("uploadFile") UploadFile uploadFile) {

		return "tested";
	}

	@RequestMapping(value="/place" , method = RequestMethod.POST)
	public String savePlace(@RequestParam("file[]")  ArrayList<MultipartFile> file1
			){
		for( @SuppressWarnings("unused") MultipartFile uploadedFile : file1) {
          
			System.out.println("aa");
        }
		return "redirect:/index";
		}
	
	@RequestMapping(value = "formTest1")
	public String test1(@ModelAttribute("uploadFile") UploadFile uploadFile) {

		return "list";
	}

	@PostMapping(value="/upload2", consumes= "multipart/form-data")
	@ResponseBody
	public void handleFileUpload1(@RequestParam("name") String name,@RequestParam("address") String address,
			@RequestParam("file") ArrayList<MultipartFile> file,@RequestParam("file1") MultipartFile file1) {
		
		  try {

	            // Get the file and save it somewhere
	            byte[] bytes = file1.getBytes();
	           
	            Path path = Paths.get("E:/spring/workspase/Doan/src/main/webapp/WEB-INF/resources/upload/" + file1.getOriginalFilename());
	            Files.write(path, bytes);

	            System.out.println("aa"+file1.getOriginalFilename());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}
