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
import vn.com.kimhue.model.CategoryModel;

@Controller
public class AdminCategoryController {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PlaceDao placeDao;
	@RequestMapping(value="admin/category")
	public String index(Model model){
		List<CategoryModel> list = categoryDao.getList();
		model.addAttribute("list",list);
		return "admin/category";
		
	}
	@RequestMapping(value="admin/addCat")
	public String form(@ModelAttribute("category") CategoryModel category){
		return "admin/addCategory";
		
	}
	@RequestMapping(value="admin/addCat", method = RequestMethod.POST)
	public String save(Model model , @ModelAttribute("category") CategoryModel category){
		categoryDao.save(category);
		return "redirect:category";
	}
	@RequestMapping(value="admin/editCat")
	public String formEdit(Model model,@RequestParam("id") int id,@ModelAttribute("category") CategoryModel category){
		CategoryModel cat = categoryDao.getObject(id);
		model.addAttribute("category",cat);
		return "admin/editCategory";
		
	}
	@RequestMapping(value="admin/editCat", method = RequestMethod.POST)
	public String editCat(@ModelAttribute("category") CategoryModel category){
		categoryDao.save(category);
		return "redirect:category";
		
	}
	@RequestMapping(value="admin/delCat")
	public String formEdit(@RequestParam("id") int id){
		CategoryModel cat = categoryDao.getObject(id);
		categoryDao.delete(cat);
		
		return "redirect:category";
		
	}

}
