package net.tralfamadore.dao;

import net.tralfamadore.domain.ExteriorFeature;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;
import net.tralfamadore.dto.SearchDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ListingDaoImpl implements ListingDao {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	@Transactional
	public long saveListing(Listing listing) {
		if(listing.getAddress().getId() != null)
			listing.setAddress(em.merge(listing.getAddress()));

		if(listing.getAgent().getId() != null)
			listing.setAgent(em.merge(listing.getAgent()));

		em.persist(listing);
		return listing.getId();
	}

	@Override
	@Transactional
	public void updateListingDetail(ListingDetail listingDetail) {
		ListingDetail d = em.find(ListingDetail.class, listingDetail.getId());
		d.setListing(listingDetail.getListing());
		d.setDiningKitchen(listingDetail.isDiningKitchen());
		d.setDiningRoom(listingDetail.isDiningRoom());
		d.setExterior(listingDetail.getExterior());
		d.setExteriorFeatures(listingDetail.getExteriorFeatures());
		d.setFullBathrooms(listingDetail.getFullBathrooms());
		d.setHalfBathrooms(listingDetail.getHalfBathrooms());
		d.setMasterBedroom(listingDetail.getMasterBedroom());
		d.setOtherRooms(listingDetail.getOtherRooms());
		d.setOverview(listingDetail.getOverview());
		d.setParking(listingDetail.getParking());
		d.setSchoolDistrict(listingDetail.getSchoolDistrict());
		d.setStatus(listingDetail.getStatus());
		d.setStories(listingDetail.getStories());
		d.setStyle(listingDetail.getStyle());
		d.setYearBuilt(listingDetail.getYearBuilt());
		for(ExteriorFeature feature : d.getExteriorFeatures())
			if(feature.getId() == null)
				em.persist(feature);
		d.getOtherRooms().forEach(r -> {
			if(r.getId() == null)
				em.persist(r);
		});
		em.merge(d);
	}
	
	@Override
	@Transactional
	public long saveListingDetail(ListingDetail listingDetail) {
		for(ExteriorFeature feature : listingDetail.getExteriorFeatures())
			if(feature.getId() == null)
				em.persist(feature);
		listingDetail.getOtherRooms().forEach(r -> {
			if(r.getId() == null)
				em.persist(r);
		});
		em.persist(listingDetail);
		return listingDetail.getId();
	}
	
	@Override
	public List<Listing> getListings(SearchDto searchDto) {
	    String sql = "from Listing where 1 = 1";
	    if(searchDto.getBathrooms() != 0)
	    	sql += " and bathrooms >= :bathrooms";
		if(searchDto.getBedrooms() != 0)
			sql += " and bedrooms >= :bedrooms";
		if(searchDto.getCity() != null && !searchDto.getCity().trim().isEmpty())
			sql += " and address.city = :city";
		if(searchDto.getState() != null && !searchDto.getState().trim().isEmpty())
			sql += " and address.state = :state";
		if(searchDto.getZipCode() != null && !searchDto.getZipCode().trim().isEmpty())
			sql += " and address.zipCode = :zipCode";
		if(searchDto.getPrice() != 0)
			sql += " and price <= :price";

		Query query = em.createQuery(sql, Listing.class);

		if(searchDto.getBathrooms() != 0)
			query.setParameter("bathrooms", searchDto.getBathrooms());
		if(searchDto.getBedrooms() != 0)
			query.setParameter("bedrooms", searchDto.getBedrooms());
        if(searchDto.getCity() != null && !searchDto.getCity().trim().isEmpty())
            query.setParameter("city", searchDto.getCity());
        if(searchDto.getState() != null && !searchDto.getState().trim().isEmpty())
			query.setParameter("state", searchDto.getState());
		if(searchDto.getZipCode() != null && !searchDto.getZipCode().trim().isEmpty())
			query.setParameter("zipCode", searchDto.getZipCode());
		if(searchDto.getPrice() != 0)
			query.setParameter("price", searchDto.getPrice());

		//noinspection unchecked
		return query.getResultList();
	}
}