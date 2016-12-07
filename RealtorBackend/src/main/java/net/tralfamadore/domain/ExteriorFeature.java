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
@Table(name = "exterior_feature")
public class ExteriorFeature {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "listing_detail_id", referencedColumnName = "id")
	private ListingDetail listingDetail;

	@Column
	private String name;

	public ExteriorFeature() {
	}
	
	public ExteriorFeature(ListingDetail listingDetail, String name) {
		this.listingDetail = listingDetail;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ListingDetail getListingDetail() {
		return this.listingDetail;
	}

	public void setListingId(ListingDetail listingDetail) {
		this.listingDetail= listingDetail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ExteriorFeature [id=" + id + ", name=" + name + "]";
	}
}