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
		listings = new ArrayList<>();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		Agent other = (Agent) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (listings == null) {
			if (other.listings != null)
				return false;
		} else if (listings.isEmpty() && other.listings.isEmpty())
		    return true;
        else if (!listings.equals(other.listings))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agent{" +
				"id=" + id +
				", listings=" + (listings == null ? 0 : listings.size()) +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", contactNumber='" + contactNumber + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}