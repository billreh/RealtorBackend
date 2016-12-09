package net.tralfamadore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "agent")
public class Agent {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private List<Listing> listings;
	@Column(name = "first_name")
	@NotNull
	@Size(min = 1, max = 100)
	private String firstName;
	@Column(name = "last_name")
	@NotNull
	@Size(min = 1, max = 100)
	private String lastName;
	@Column(name = "contact_number")
	@NotNull
	@Size(min = 1, max = 100)
	private String contactNumber;
	@Column
	@NotNull
	@Size(min = 1, max = 100)
	@Email
	private String email;
	
	public Agent() {
		listings = new ArrayList<Listing>();
	}
	
	public Agent(Long id, List<Listing> listings, String firstName, String lastName, String contactNumber, String email) {
		this.id = id;
		this.listings = listings;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Listing> getListings() {
		return listings;
	}
	
	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Agent [listings=" + listings.size() + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactNumber=" + contactNumber + ", email=" + email + "]";
	}
}