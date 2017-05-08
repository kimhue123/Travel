package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.kimhue.model.ImgModel;
@Repository
public interface ImgRepsitory extends JpaRepository<ImgModel, Integer>{
	/*@Modifying 
	@Query("delete  from ImgModel as imgModel where imgModel.detail.id =?1")
	void deleteByIdDetail(int id);
	@Modifying 
	@Query("delete  from ImgModel as imgModel where imgModel.place.id =?1")
	void deleteByIdPlace(int id);*/

}
