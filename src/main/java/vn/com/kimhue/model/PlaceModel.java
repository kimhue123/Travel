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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
@Entity
@Table(name="place")
public class PlaceModel {
	
	private int id;
	
	private String name;
	
	private String content;
	
	private String address;
	
	private float lat;
	
	private float lng;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 255, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "content", length = 255, nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "address", length = 255, nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "lat", length = 255, nullable = false)
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	@Column(name = "lng", length = 255, nullable = false)
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}

	public PlaceModel(int id, String name, String content, String address, float lat, float lng) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		
	}

	
	private CategoryModel category;
	 
	
	/*@ManyToOne
	@JoinColumn(name="idUser")
	private UserModel user;*/
	@ManyToOne
	@JoinColumn(name="idCategory")
	public CategoryModel getCategory() {
		return category;
	}
	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	private UserModel user;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	private List<DetailPlaceModel> detailPlaceModel;
	
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="place")
	public List<DetailPlaceModel> getDetailPlaceModel() {
		return detailPlaceModel;
	}
	public void setDetailPlaceModel(List<DetailPlaceModel> detailPlaceModel) {
		this.detailPlaceModel = detailPlaceModel;
	}


	private ImgModel image;
	
	@OneToOne
	@JoinColumn(name="idImg")
	public ImgModel getImage() {
		return image;
	}
	public void setImage(ImgModel image) {
		this.image = image;
	}
	public PlaceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//chay lai 
	
}
