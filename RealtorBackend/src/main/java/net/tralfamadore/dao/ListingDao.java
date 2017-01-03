package net.tralfamadore.dao;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;
import net.tralfamadore.dto.SearchDto;

import java.util.List;

public interface ListingDao {

	long saveListing(Listing listing);

    void updateListingDetail(ListingDetail listingDetail);

	long saveListingDetail(ListingDetail listingDetail);

    List<Listing> getListings(SearchDto searchDto);
}