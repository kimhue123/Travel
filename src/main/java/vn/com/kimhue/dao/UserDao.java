package vn.com.kimhue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.kimhue.model.UserModel;
import vn.com.kimhue.repository.UserRepsitory;

@Service
public class UserDao {
	@Autowired 
	private UserRepsitory userRepsitory;

	public int countUserByIdFacebook(String id) {
		return userRepsitory.countUserByIdFacebook(id);
	}

	public UserModel save(UserModel userModel) {
		return userRepsitory.save(userModel);
		
	}

	public UserModel findUserByIdFacebook(String idFacebook) {
		return userRepsitory.findUserByIdFacebook(idFacebook);
	}

	public List<UserModel> getList() {
		return userRepsitory.findAll();
	}

	public UserModel getUserByIdFacebook(String idFacebook) {
		return userRepsitory.getUserByIdFacebook(idFacebook);
	}

	public UserModel getUserByUsernameAndPassword(String username, String password) {
		return userRepsitory.getUserByUsernameAndPassword(username,password);
	}

	
	

}
