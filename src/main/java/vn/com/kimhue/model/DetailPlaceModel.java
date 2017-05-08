package vn.com.kimhue.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="detailplace")
public class DetailPlaceModel {
	private int id;
	private String content;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 255, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "content", length = 255, nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public DetailPlaceModel(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}


	public DetailPlaceModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	private PlaceModel place;
	
	private ImgModel image;
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinColumn(name="idPlace")
	public PlaceModel getPlace() {
		return place;
	}
	public void setPlace(PlaceModel place) {
		this.place = place;
	}
	
	@OneToOne
	@JoinColumn(name="idImg")
	public ImgModel getImage() {
		return image;
	}
	public void setImage(ImgModel image) {
		this.image = image;
	}

	public DetailPlaceModel(int id, String content, PlaceModel place, ImgModel image) {

		this.id = id;
		this.content = content;
		this.place = place;
		this.image = image;
	}
	
	
	

}
