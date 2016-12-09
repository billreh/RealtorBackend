package net.tralfamadore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "listing")
public class Listing {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@Column
	@DecimalMin(value = "1000")
	private int price;
	
	@Column(name = "house_type")
	private String houseType;
	
	@Column(name = "bathrooms")
	private float baths;
	
	@Column
	@DecimalMin(value = "1")
	private int bedrooms;
	
	@DecimalMin(value = "100")
	@Column(name = "square_feet")
	private int squareFeet;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "agent_id", referencedColumnName = "id")
	@NotNull
	private Agent agent;
	
	@Column(name = "main_photo")
	private String mainPhoto;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "listing_id", referencedColumnName = "id")
	private List<Photo> photos;
	
	public Listing() {
		
	}
	
	public Listing(Long id, Address address, int price, String houseType, float baths, int bedrooms, int squareFeet, Agent agent, String mainPhoto, List<Photo> photos) {
		this.id = id;
		this.address = address;
		this.price = price;
		this.houseType = houseType;
		this.baths = baths;
		this.bedrooms = bedrooms;
		this.squareFeet = squareFeet;
		this.agent = agent;
		this.mainPhoto = mainPhoto;
		this.photos = photos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public float getBaths() {
		return baths;
	}
	
	public void setBaths(float baths) {
		this.baths = baths;
	}
	
	public int getBedrooms() {
		return bedrooms;
	}
	
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public int getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(int squareFeet) {
		this.squareFeet = squareFeet;
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Listing [id=" + id + ", address=" + address + ", price=" + price + ", houseType=" + houseType
				+ ", baths=" + baths + ", bedrooms=" + bedrooms + ", squareFeet=" + squareFeet + ", agent=" + agent
				+ ", mainPhoto=" + mainPhoto + ", photos=" + photos + "]";
	}
}