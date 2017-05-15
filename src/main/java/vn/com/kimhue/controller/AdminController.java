package vn.com.kimhue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.com.kimhue.dao.UserDao;
import vn.com.kimhue.model.UserModel;

@Controller
public class AdminController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="admin/login")
	public String loginForm(@ModelAttribute UserModel user){
		return"admin/login";
	}
	@RequestMapping(value="admin/login", method = RequestMethod.POST)
	public String login(@ModelAttribute UserModel user, HttpServletRequest request, Model model){
		UserModel userModel = userDao.getUserByUsernameAndPassword(user.getUsername(),user.getPassword());
		if (userModel != null) {
			if (userModel.getRole() == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", user.getUsername());
				return "redirect:index";
			} else {
				model.addAttribute("message", "login fail");
				return "admin/login";
			}
		} else {
			model.addAttribute("message", "login fail");
			return "admin/login";
		}
		
	}
	@RequestMapping(value="admin/index")
	public String index(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			return "admin/index";
		} else {
			return "admin/login";
		}
	}
	@RequestMapping(value="admin/logout")
	public String logout( HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
			return"admin/login";
		
	}

}
