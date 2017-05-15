package vn.com.kimhue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="slide")
public class SlideModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 255, nullable = false)
	private int id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	
	@OneToOne
	@JoinColumn(name="idImg")
	public ImgModel imgModel;
	
	
	public ImgModel getImgModel() {
		return imgModel;
	}
	
	public void setImgModel(ImgModel imgModel) {
		this.imgModel = imgModel;
	}
}
