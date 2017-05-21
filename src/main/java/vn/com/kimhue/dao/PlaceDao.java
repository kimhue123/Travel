package vn.com.kimhue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.com.kimhue.model.DetailPlaceModel;
import vn.com.kimhue.model.PlaceModel;
import vn.com.kimhue.repository.PlaceRepsitory;
import vn.com.kimhue.repository.reportRepsitory;

@Service
public class PlaceDao {
	private static final int PAGE_SIZE = 50;
	@Autowired
	private PlaceRepsitory placeRepsitory;

	public List<PlaceModel> getList() {
		return placeRepsitory.findAll();
	}

	public PlaceModel getPlaceById(int id) {
		return placeRepsitory.findOne(id);
	}

	public PlaceModel save(PlaceModel placeModel) {
		return placeRepsitory.save(placeModel);
		
	}
	
     public List<DetailPlaceModel> getListDetailPlaceByIdPlace(int placeId) {
		
		return placeRepsitory.findDetailPlaceModelById(placeId);
	}

	public PlaceModel findOnebyIdUser(int id) {
		return placeRepsitory.findOnebyIdUser(id);
	}

	public List<PlaceModel> getListByIdUser(String userId) {
		return placeRepsitory.getListByIdUser(userId);
	}

	public void deletePlaceById(int id) {
		 placeRepsitory.delete(id);
		
	}

	public List<PlaceModel> getListByIdCategory(int idCat) {
		return placeRepsitory.getListByIdCategory(idCat);
	}
	public Page<PlaceModel> getPlaceLog(Integer pageNumber) {
        PageRequest request =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime");
        return placeRepsitory.findAll(request);
    }
/*	public Page<PlaceModel> findAll(PageRequest request) {
		return placeRepsitory.findAll(request);
	}*/

	

	public Page<PlaceModel> getAllPublishedPosts(int page, int pageSize) {
		Pageable pageable = new PageRequest(page, pageSize);
		Page<PlaceModel> blogList = placeRepsitory.findAll(pageable);
		return blogList;
	}

	/*public void deleteByIdCat(int id) {
		placeRepsitory.deleteByIdCat(id);
		
	}*/

	
}
//run lai di ok