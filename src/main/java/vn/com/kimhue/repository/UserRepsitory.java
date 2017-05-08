package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.kimhue.model.UserModel;

public interface UserRepsitory extends JpaRepository<UserModel, Integer>{
	
	@Query(value="select COUNT(userModel)  from UserModel as userModel where userModel.idFacebook =?1")
	int countUserByIdFacebook(String id);
	@Query(value="select userModel  from UserModel as userModel where userModel.idFacebook =?1")
	UserModel findUserByIdFacebook(String idFacebook);

}
