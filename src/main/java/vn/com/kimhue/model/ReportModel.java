package vn.com.kimhue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="report")
public class ReportModel {
	private int id;
	private int idUser;
	private int idPlace;
	private String content;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "idUser", length = 10, nullable = false)
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	@Column(name = "idPlace", length = 255, nullable = false)
	public int getIdPlace() {
		return idPlace;
	}
	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}
	@Column(name = "content", length = 255, nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
