package vn.com.kimhue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class UserModel {
	
	private int id;
	private String username;
	private String password;
	private String avatar;
	private String idFacebook;
	private int role;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 255, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "username", length = 100, nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "avatar", length = 100, nullable = false)
	public String getAvater() {
		return avatar;
	}
	public void setAvater(String avater) {
		this.avatar = avater;
	}
	@Column(name = "idFacebook", length = 100, nullable = false)
	public String getIdFacebook() {
		return idFacebook;
	}
	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}
	@Column(name = "password", length = 30, nullable = true)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "role", length = 10, nullable = false)
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public UserModel(int id) {
		super();
		this.id = id;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserModel(int id, String username, String avater, String string, int role) {
		super();
		this.id = id;
		this.username = username;
		this.avatar = avater;
		this.idFacebook = string;
		this.role = role;
	}

}
