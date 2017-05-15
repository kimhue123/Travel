package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.kimhue.model.UserModel;

public interface UserRepsitory extends JpaRepository<UserModel, Integer>{
	
	@Query(value="select COUNT(userModel)  from UserModel as userModel where userModel.idFacebook =?1")
	int countUserByIdFacebook(String id);
	@Query(value="select userModel  from UserModel as userModel where userModel.idFacebook =?1")
	UserModel findUserByIdFacebook(String idFacebook);
	@Query(value="select userModel  from UserModel as userModel where userModel.idFacebook =?1")
	UserModel getUserByIdFacebook(String idFacebook);
	@Query(value="select userModel  from UserModel as userModel where userModel.username =?1 and userModel.password =?2 ")
	UserModel getUserByUsernameAndPassword(String username, String password);

}
