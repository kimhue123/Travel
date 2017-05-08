package vn.com.kimhue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.kimhue.model.ImgModel;
import vn.com.kimhue.repository.ImgRepsitory;

@Service
public class ImgDao {
	@Autowired
	private ImgRepsitory imgRepsitory;
	public ImgModel uploadImg(ImgModel imgModel){
		return imgRepsitory.save(imgModel);
	}
	/*public void deleteByIdDetail(int id) {
		imgRepsitory.deleteByIdDetail(id);
		
	}
	public void deleteByIdPlace(int id) {
		imgRepsitory.deleteByIdPlace(id);
		
	}*/
	


}
