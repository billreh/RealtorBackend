package net.tralfamadore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "featured_listing")
public class FeaturedListing {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "listing_id", referencedColumnName = "id")
	private Listing listing;
	
	public FeaturedListing() {
	}
	
	public FeaturedListing(Listing listing) {
		this.listing = listing;
	}
	
	public FeaturedListing(Long id, Listing listing) {
		this.id = id;
		this.listing = listing;
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
		this.listing = listing;
	}

	@Override
	public String toString() {
		return "FeaturedListing [id=" + id + ", listing=" + listing + "]";
	}
}
