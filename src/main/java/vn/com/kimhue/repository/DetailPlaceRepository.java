package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.kimhue.model.DetailPlaceModel;

@Repository
public interface DetailPlaceRepository extends JpaRepository<DetailPlaceModel, Integer> {
	@Modifying 
	@Query("delete  from DetailPlaceModel as detailPlaceModel where detailPlaceModel.place.id =?1")
	void deleteDetailPlaceById(int id);

}
