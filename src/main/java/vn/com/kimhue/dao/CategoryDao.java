package vn.com.kimhue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.kimhue.model.CategoryModel;
import vn.com.kimhue.repository.CategoryRepsitory;

@Service
public class CategoryDao {
	@Autowired
	private CategoryRepsitory categoryRepsitory;
	
	@Transactional
	public List<CategoryModel> getList(){
		return categoryRepsitory.findAll();
	}

	public CategoryModel save(CategoryModel cat) {
		return categoryRepsitory.save(cat);
		
	}

	public CategoryModel getObject(Integer id) {
		return categoryRepsitory.findOne(id);
	}

	public void delete(CategoryModel cat) {
		 categoryRepsitory.delete(cat);
		
	}
}
