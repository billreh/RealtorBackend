package net.tralfamadore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

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

	public void setListingDetail(ListingDetail listingDetail) {
		this.listingDetail= listingDetail;
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

        ExteriorFeature that = (ExteriorFeature) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (listingDetail != null)
            if(that.listingDetail == null)
                return false;
        if(that.listingDetail != null)
            if(listingDetail == null)
                return false;
        if(listingDetail != null)
            if(!Objects.equals(listingDetail.getId(), that.listingDetail.getId()))
                return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (listingDetail != null ? listingDetail.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "ExteriorFeature [id=" + id + ", name=" + name + "]";
	}
}