package net.tralfamadore.dao;

import java.util.List;

import net.tralfamadore.dto.SearchDto;

import net.tralfamadore.domain.FeaturedListing;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;

public interface ListingDao {

	long saveListing(Listing listing);

	void updateListingDetail(ListingDetail listingDetail);

	long saveListingDetail(ListingDetail listingDetail);

    List<Listing> getListings(SearchDto searchDto);
}