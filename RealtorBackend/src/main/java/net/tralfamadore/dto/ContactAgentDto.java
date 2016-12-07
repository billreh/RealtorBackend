package net.tralfamadore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactAgentDto {
	@JsonProperty("_listingId")
	private long listingId;
	@JsonProperty("_name")
	private String name;
	@JsonProperty("_email")
	private String email;
	@JsonProperty("_phone")
	private String phone;
	@JsonProperty("_message")
	private String message;
	
	public ContactAgentDto() {
	}
	
	public ContactAgentDto(long listingId, String name, String email, String phone, String message) {
		this.listingId = listingId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.message = message;
	}

	public long getListingId() {
		return listingId;
	}

	public void setListingId(long listingId) {
		this.listingId = listingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactAgentDto [listingId=" + listingId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", message=" + message + "]";
	}
}
