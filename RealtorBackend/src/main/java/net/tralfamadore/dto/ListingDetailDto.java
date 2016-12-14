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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(bathrooms);
		result = prime * result + bedrooms;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (diningKitchen ? 1231 : 1237);
		result = prime * result + (diningRoom ? 1231 : 1237);
		result = prime * result + ((exterior == null) ? 0 : exterior.hashCode());
		result = prime * result + Arrays.hashCode(exteriorFeatures);
		result = prime * result + fullBathrooms;
		result = prime * result + halfBathrooms;
		result = prime * result + ((houseType == null) ? 0 : houseType.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mainPhoto == null) ? 0 : mainPhoto.hashCode());
		result = prime * result + ((masterBedroom == null) ? 0 : masterBedroom.hashCode());
		result = prime * result + Arrays.hashCode(otherRooms);
		result = prime * result + ((overview == null) ? 0 : overview.hashCode());
		result = prime * result + ((parking == null) ? 0 : parking.hashCode());
		result = prime * result + Arrays.hashCode(photos);
		result = prime * result + price;
		result = prime * result + ((schoolDistrict == null) ? 0 : schoolDistrict.hashCode());
		result = prime * result + squareFeet;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + stories;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		result = prime * result + yearBuilt;
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
		ListingDetailDto other = (ListingDetailDto) obj;
		if (Float.floatToIntBits(bathrooms) != Float.floatToIntBits(other.bathrooms))
			return false;
		if (bedrooms != other.bedrooms)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (diningKitchen != other.diningKitchen)
			return false;
		if (diningRoom != other.diningRoom)
			return false;
		if (exterior == null) {
			if (other.exterior != null)
				return false;
		} else if (!exterior.equals(other.exterior))
			return false;
		if (!Arrays.equals(exteriorFeatures, other.exteriorFeatures))
			return false;
		if (fullBathrooms != other.fullBathrooms)
			return false;
		if (halfBathrooms != other.halfBathrooms)
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
		if (masterBedroom == null) {
			if (other.masterBedroom != null)
				return false;
		} else if (!masterBedroom.equals(other.masterBedroom))
			return false;
		if (!Arrays.equals(otherRooms, other.otherRooms))
			return false;
		if (overview == null) {
			if (other.overview != null)
				return false;
		} else if (!overview.equals(other.overview))
			return false;
		if (parking == null) {
			if (other.parking != null)
				return false;
		} else if (!parking.equals(other.parking))
			return false;
		if (!Arrays.equals(photos, other.photos))
			return false;
		if (price != other.price)
			return false;
		if (schoolDistrict == null) {
			if (other.schoolDistrict != null)
				return false;
		} else if (!schoolDistrict.equals(other.schoolDistrict))
			return false;
		if (squareFeet != other.squareFeet)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (stories != other.stories)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		if (yearBuilt != other.yearBuilt)
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