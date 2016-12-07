package net.tralfamadore.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingDetailDto {
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
	@JsonProperty("_overview")
	private String overview;
	@JsonProperty("_masterBedroom")
	private String masterBedroom;
	@JsonProperty("_fullBathrooms")
	private int fullBathrooms;
	@JsonProperty("_halfBathrooms")
	private int halfBathrooms;
	@JsonProperty("_diningKitchen")
	private boolean diningKitchen;
	@JsonProperty("_diningRoom")
	private boolean diningRoom;
	@JsonProperty("_exteriorFeatures")
	private String[] exteriorFeatures;
	@JsonProperty("_otherRooms")
	private String[] otherRooms;
	@JsonProperty("_stories")
	private int stories;
	@JsonProperty("_exterior")
	private String exterior;
	@JsonProperty("_parking")
	private String parking;
	@JsonProperty("_price")
	private int price;
	@JsonProperty("_mainPhoto")
	private String mainPhoto;
	@JsonProperty("_photos")
	private String[] photos;
	@JsonProperty("_status")
	private String status;
	@JsonProperty("_style")
	private String style;
	@JsonProperty("_yearBuilt")
	private int yearBuilt;
	@JsonProperty("_schoolDistrict")
	private String schoolDistrict;
	
	public ListingDetailDto() {
	}

	public ListingDetailDto(long id, String street, String city, String state, String zipCode, String houseType, int bedrooms,
			float bathrooms, int squareFeet, String overview, String masterBedroom, int fullBathrooms,
			int halfBathrooms, boolean diningKitchen, boolean diningRoom, String[] exteriorFeatures, String[] otherRooms,
			int stories, String exterior, String parking, int price, String mainPhoto, String[] photos, String status,
			String style, int yearBuilt, String schoolDistrict) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.houseType = houseType;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.squareFeet = squareFeet;
		this.overview = overview;
		this.masterBedroom = masterBedroom;
		this.fullBathrooms = fullBathrooms;
		this.halfBathrooms = halfBathrooms;
		this.diningKitchen = diningKitchen;
		this.diningRoom = diningRoom;
		this.exteriorFeatures = exteriorFeatures;
		this.otherRooms = otherRooms;
		this.stories = stories;
		this.exterior = exterior;
		this.parking = parking;
		this.price = price;
		this.mainPhoto = mainPhoto;
		this.photos = photos;
		this.status = status;
		this.style = style;
		this.yearBuilt = yearBuilt;
		this.schoolDistrict = schoolDistrict;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getMasterBedroom() {
		return masterBedroom;
	}

	public void setMasterBedroom(String masterBedroom) {
		this.masterBedroom = masterBedroom;
	}

	public int getFullBathrooms() {
		return fullBathrooms;
	}

	public void setFullBathrooms(int fullBathrooms) {
		this.fullBathrooms = fullBathrooms;
	}

	public int getHalfBathrooms() {
		return halfBathrooms;
	}

	public void setHalfBathrooms(int halfBathrooms) {
		this.halfBathrooms = halfBathrooms;
	}

	public boolean isDiningKitchen() {
		return diningKitchen;
	}

	public void setDiningKitchen(boolean diningKitchen) {
		this.diningKitchen = diningKitchen;
	}

	public boolean isDiningRoom() {
		return diningRoom;
	}

	public void setDiningRoom(boolean diningRoom) {
		this.diningRoom = diningRoom;
	}

	public String[] getExteriorFeatures() {
		return exteriorFeatures;
	}

	public void setExteriorFeatures(String[] exteriorFeatures) {
		this.exteriorFeatures = exteriorFeatures;
	}

	public String[] getOtherRooms() {
		return otherRooms;
	}

	public void setOtherRooms(String[] otherRooms) {
		this.otherRooms = otherRooms;
	}

	public int getStories() {
		return stories;
	}

	public void setStories(int stories) {
		this.stories = stories;
	}

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public String getSchoolDistrict() {
		return schoolDistrict;
	}

	public void setSchoolDistrict(String schoolDistrict) {
		this.schoolDistrict = schoolDistrict;
	}

	@Override
	public String toString() {
		return "ListingDetailDto [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + ", houseType=" + houseType + ", bedrooms=" + bedrooms + ", bathrooms="
				+ bathrooms + ", squareFeet=" + squareFeet + ", overview=" + overview + ", masterBedroom="
				+ masterBedroom + ", fullBathrooms=" + fullBathrooms + ", halfBathrooms=" + halfBathrooms
				+ ", diningKitchen=" + diningKitchen + ", diningRoom=" + diningRoom + ", exteriorFeatures="
				+ Arrays.toString(exteriorFeatures) + ", otherRooms=" + Arrays.toString(otherRooms) + ", stories="
				+ stories + ", exterior=" + exterior + ", parking=" + parking + ", price=" + price + ", mainPhoto="
				+ mainPhoto + ", photos=" + Arrays.toString(photos) + ", status=" + status + ", style=" + style
				+ ", yearBuilt=" + yearBuilt + ", schoolDistrict=" + schoolDistrict + "]";
	}
}