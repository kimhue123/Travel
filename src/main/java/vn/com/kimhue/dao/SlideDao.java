package vn.com.kimhue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.kimhue.model.SlideModel;
import vn.com.kimhue.repository.SlideRepsitory;

@Service
public class SlideDao {
	@Autowired
	private SlideRepsitory slideRepsitory;
	public List<SlideModel> getList(){
		return slideRepsitory.findAll();
	}
	

}
