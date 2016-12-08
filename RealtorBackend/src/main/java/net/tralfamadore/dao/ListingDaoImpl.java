package net.tralfamadore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tralfamadore.domain.ExteriorFeature;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;

@Repository
public class ListingDaoImpl implements ListingDao {
	private static Logger log = Logger.getLogger(ListingDaoImpl.class);

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
	public List<Listing> getListings() {
		log.info("fetching listings list");
		List<Listing> listings = em.createQuery("from Listing", Listing.class).getResultList();
		return listings;
	}
	
	@Override
	@Transactional
	public void deleteListing(Listing listing) {
		em.remove(em.contains(listing) ? listing : em.merge(listing));
	}

	@Override
	@Transactional
	public void updateListing(Listing listing) {
		Listing l = em.find(Listing.class, listing.getId());
		l.setAddress(listing.getAddress());
		l.setAgent(listing.getAgent());
		l.setBaths(listing.getBaths());
		l.setBedrooms(listing.getBedrooms());
		l.setHouseType(listing.getHouseType());
		l.setPrice(listing.getPrice());
		l.setSquareFeet(listing.getSquareFeet());
		l.setMainPhoto(listing.getMainPhoto());
		l.setPhotos(listing.getPhotos());
		em.merge(l);
	}

	@Override
	public Listing getListing(long listingId) {
		return em.find(Listing.class, listingId);
	}

	@Override
	@Transactional
	public ListingDetail getListingDetail(long listingId) {
		ListingDetail listingDetail = em.createQuery("from ListingDetail where listing_id = :id", ListingDetail.class).setParameter("id", listingId).getSingleResult();
		em.refresh(listingDetail);
		return listingDetail;
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
	@Transactional
	public void deleteListingDetail(ListingDetail listingDetail) {
		em.remove(em.contains(listingDetail) ? listingDetail : em.merge(listingDetail));
	}
}