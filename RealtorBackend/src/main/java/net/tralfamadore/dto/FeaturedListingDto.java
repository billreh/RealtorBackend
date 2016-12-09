package net.tralfamadore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeaturedListingDto {
	@JsonProperty("_id")
	private long id;
	@JsonProperty("_mainPhoto")
	private String mainPhoto;
		
	public FeaturedListingDto() {
	}
		
	public FeaturedListingDto(long id, String mainPhoto) {
		super();
		this.id = id;
		this.mainPhoto = mainPhoto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}
}
