package vn.com.kimhue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.kimhue.dao.CategoryDao;
import vn.com.kimhue.dao.PlaceDao;
import vn.com.kimhue.dao.UserDao;
import vn.com.kimhue.model.CategoryModel;
import vn.com.kimhue.model.UserModel;

@Controller
public class AdminUserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="admin/user")
	public String index(Model model){
		List<UserModel> list = userDao.getList();
		model.addAttribute("list",list);
		return "admin/user";
		
	}
	
}
