package net.tralfamadore.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;

public interface ListingDao {

	long saveListing(Listing listing);

	List<Listing> getListings();

	void deleteListing(Listing listing);

	void updateListing(Listing listing);

	Listing getListing(long listingId);

	ListingDetail getListingDetail(long listingId);

	void updateListingDetail(ListingDetail listingDetail);
	
	long saveListingDetail(ListingDetail listingDetail);
	
	public void deleteListingDetail(ListingDetail listingDetail);
}