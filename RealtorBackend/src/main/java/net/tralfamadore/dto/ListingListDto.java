package net.tralfamadore.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingListDto {
	@JsonProperty("_id")
	private long id;
	@JsonProperty("_street")
	private String street;
	@JsonProperty("_city")
	private String city;
	@JsonProperty("_state")
	private String state;
	@JsonProperty("_zipCode")
	private String zipCode;
	@JsonProperty("_houseType")
	private String houseType;
	@JsonProperty("_bedrooms")
	private int bedrooms;
	@JsonProperty("_bathrooms")
	private float bathrooms;
	@JsonProperty("_squareFeet")
	private int squareFeet;
	@JsonProperty("_price")
	private int price;
	@JsonProperty("_mainPhoto")
	private String mainPhoto;
	@JsonProperty("_photos")
	private String[] photos;
	
	public ListingListDto() {
	}
	
	public ListingListDto(long id, String street, String city, String state, String zipCode, String houseType, int bedrooms,
			float bathrooms, int squareFeet, int price, String mainPhoto, String[] photos) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.houseType = houseType;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.squareFeet = squareFeet;
		this.price = price;
		this.mainPhoto = mainPhoto;
		this.photos = photos;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public float getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(float bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(int squareFeet) {
		this.squareFeet = squareFeet;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public String[] getPhotos() {
		return photos;
	}

	public void setPhotos(String[] photos) {
		this.photos = photos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(bathrooms);
		result = prime * result + bedrooms;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((houseType == null) ? 0 : houseType.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mainPhoto == null) ? 0 : mainPhoto.hashCode());
		result = prime * result + Arrays.hashCode(photos);
		result = prime * result + price;
		result = prime * result + squareFeet;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListingListDto other = (ListingListDto) obj;
		if (Float.floatToIntBits(bathrooms) != Float.floatToIntBits(other.bathrooms))
			return false;
		if (bedrooms != other.bedrooms)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (houseType == null) {
			if (other.houseType != null)
				return false;
		} else if (!houseType.equals(other.houseType))
			return false;
		if (id != other.id)
			return false;
		if (mainPhoto == null) {
			if (other.mainPhoto != null)
				return false;
		} else if (!mainPhoto.equals(other.mainPhoto))
			return false;
		if (!Arrays.equals(photos, other.photos))
			return false;
		if (price != other.price)
			return false;
		if (squareFeet != other.squareFeet)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListingListDto [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", houseType=" + houseType + ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms
				+ ", squareFeet=" + squareFeet + ", price=" + price + ", mainPhoto=" + mainPhoto + ", photos="
				+ Arrays.toString(photos) + "]";
	}
}