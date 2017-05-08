package vn.com.kimhue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.kimhue.bean.AccessToken;
import vn.com.kimhue.dao.UserDao;
import vn.com.kimhue.model.UserModel;

@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	@RequestMapping(value = "/getUserID", method = RequestMethod.POST)
	public @ResponseBody AccessToken addNewWorker(@RequestBody AccessToken accessToken, Model model,
			HttpServletRequest request) {
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
		return accessToken;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody String logout(@RequestBody AccessToken accessToken, Model model,
			HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.removeAttribute("userId");
			session.removeAttribute("url");
			
		return "";
	}

}
