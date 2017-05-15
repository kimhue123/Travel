package vn.com.kimhue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.kimhue.dao.UserDao;
import vn.com.kimhue.model.UserModel;

@Controller
public class AdminUserController {
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "admin/user")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if (admin != null) {
			List<UserModel> list = userDao.getList();
			model.addAttribute("list", list);
			return "admin/user";
		} else {
			return "admin/login";
		}
	}

}
