package net.tralfamadore.dao;

import java.util.List;

import net.tralfamadore.dto.ListingListDto;
import net.tralfamadore.dto.SearchDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import net.tralfamadore.domain.FeaturedListing;
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
	
	void deleteListingDetail(ListingDetail listingDetail);

	List<FeaturedListing> getFeaturedListings();

	long saveFeaturedListing(FeaturedListing featuredListing);

	void deleteFeaturedListing(FeaturedListing featuredListing);

    List<Listing> getListings(SearchDto searchDto);
}