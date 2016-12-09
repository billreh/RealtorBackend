package net.tralfamadore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@Table(name = "photo")
public class Photo {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "listing_id", referencedColumnName = "id")
	private Listing listing;

	@Column
	private String name;

	public Photo() {
	}
	
	public Photo(Listing listing, String name) {
		this.listing = listing;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Listing getListing() {
		return this.listing;
	}

	public void setListingId(Listing listing) {
		this.listing= listing;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + "]";
	}
}