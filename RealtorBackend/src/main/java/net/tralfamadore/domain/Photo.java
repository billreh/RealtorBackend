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

	public void setListing(Listing listing) {
		this.listing= listing;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != null ? !id.equals(photo.id) : photo.id != null) return false;
        if (listing == null)
            if (photo.listing != null)
                return false;
        if (photo.listing == null)
            if (listing != null)
                return false;
        return !(listing != null && !listing.getId().equals(photo.listing.getId())) &&
                (name != null ? name.equals(photo.name) : photo.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (listing != null ? listing.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + "]";
	}
}