package net.tralfamadore.domain;

import java.util.List;

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

@Entity
@Table(name = "listing_detail")
public class ListingDetail {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "listing_id", referencedColumnName = "id")
	private Listing listing;
	@Column
	private String overview;
	@Column(name = "master_bedroom")
	private String masterBedroom;
	@Column(name = "full_bathrooms")
	private int fullBathrooms;
	@Column(name = "half_bathrooms")
	private int halfBathrooms;
	@Column(name = "dining_kitchen")
	private boolean diningKitchen;
	@Column(name = "dining_room")
	private boolean diningRoom;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "listing_detail_id", referencedColumnName = "id")
	private List<ExteriorFeature> exteriorFeatures;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "listing_detail_id", referencedColumnName = "id")
	private List<OtherRoom> otherRooms;
	@Column
	@DecimalMin(value = "1")
	private int stories;
	@Column
	private String exterior;
	@Column
	private String parking;
	@Column
	private String status;
	@Column
	private String style;
	@Column(name = "year_built")
	private int yearBuilt;
	@Column(name = "school_district")
	private String schoolDistrict;
	
	public ListingDetail() {
	}
	
	public ListingDetail(Long id, Listing listing, String overview, String masterBedroom, int fullBathrooms, int halfBathrooms,
			boolean diningKitchen, boolean diningRoom, List<ExteriorFeature> exteriorFeatures, List<OtherRoom> otherRooms,
			int stories, String exterior, String parking, String status) {
		this.id = id;
		this.listing= listing;
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
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing= listing;
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

	public List<ExteriorFeature> getExteriorFeatures() {
		return exteriorFeatures;
	}

	public void setExteriorFeatures(List<ExteriorFeature> exteriorFeatures) {
		this.exteriorFeatures = exteriorFeatures;
	}

	public List<OtherRoom> getOtherRooms() {
		return otherRooms;
	}

	public void setOtherRooms(List<OtherRoom> otherRooms) {
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
		return "ListingDetail [id=" + id + ", listing=" + listing + ", overview=" + overview + ", masterBedroom="
				+ masterBedroom + ", fullBathrooms=" + fullBathrooms + ", halfBathrooms=" + halfBathrooms
				+ ", diningKitchen=" + diningKitchen + ", diningRoom=" + diningRoom + ", exteriorFeatures="
				+ exteriorFeatures + ", otherRooms=" + otherRooms + ", stories=" + stories + ", exterior=" + exterior
				+ ", parking=" + parking + ", status=" + status + "]";
	}
}
