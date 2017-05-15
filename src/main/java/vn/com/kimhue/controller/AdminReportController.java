package vn.com.kimhue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.kimhue.dao.PlaceDao;
import vn.com.kimhue.dao.ReportDao;
import vn.com.kimhue.model.ReportModel;

@Controller
public class AdminReportController {
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private PlaceDao placeDao;;
	@RequestMapping(value="admin/report")
	public String index(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		if(admin!=null){
		List<ReportModel> list = reportDao.getList();
		model.addAttribute("list",list);
		return "admin/report";
		}else{
			return "admin/login";
		}
		
	}
	@RequestMapping(value="admin/accept")
	public String accept(Model model, @RequestParam("id")int id){
		ReportModel reportModel = reportDao.getReportById(id);
		if(reportModel!=null){
			placeDao.deletePlaceById(reportModel.getIdPlace());
		}
		return "redirect:report";
		
	}
	@RequestMapping(value="admin/noaccept")
	public String noAccept(Model model, @RequestParam("id")int id){
		ReportModel reportModel = reportDao.getReportById(id);
		if(reportModel!=null){
			reportDao.delete(id);
		}
		return "redirect:report";
		
	}
	
}
