package vn.com.kimhue.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.kimhue.model.DetailPlaceModel;
import vn.com.kimhue.model.PlaceModel;
@Repository
public interface PlaceRepsitory extends JpaRepository<PlaceModel, Integer>{
	
	@Query("select placeModel.detailPlaceModel from PlaceModel as placeModel where placeModel.id = ?1")
	List<DetailPlaceModel> findDetailPlaceModelById(int placeModelId);
	@Query("select placeModel from PlaceModel as placeModel where placeModel.id = ?1")
	PlaceModel findOnebyIdUser(int id);
	@Query("select placeModel from PlaceModel as placeModel where placeModel.user.idFacebook =?1")
	List<PlaceModel> getListByIdUser(String userId);
	/*@Query("delete placeModel from PlaceModel as placeModel where placeModel.category.id =?1")
	void deleteByIdCat(int id);*/
	@Query("select placeModel from PlaceModel as placeModel where placeModel.category.id = ?1")
	List<PlaceModel> getListByIdCategory(int idCat);
	/*@Query("select placeModel from PlaceModel as placeModel ")
	List<PlaceModel> getList(int offset, int row_count);*/
	

}
