package vn.com.kimhue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping(value="admin/index")
	public String index(Model model){
		return "admin/index";
		
	}

}
