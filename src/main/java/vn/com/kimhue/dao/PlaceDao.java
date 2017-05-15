package vn.com.kimhue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.kimhue.model.DetailPlaceModel;
import vn.com.kimhue.model.PlaceModel;
import vn.com.kimhue.repository.PlaceRepsitory;

@Service
public class PlaceDao {
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

	/*public void deleteByIdCat(int id) {
		placeRepsitory.deleteByIdCat(id);
		
	}*/

	
}
//run lai di ok