package net.tralfamadore.service;

import static java.util.stream.Collectors.toList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tralfamadore.dao.ListingDao;
import net.tralfamadore.domain.ExteriorFeature;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;
import net.tralfamadore.domain.OtherRoom;
import net.tralfamadore.domain.Photo;
import net.tralfamadore.dto.ListingDetailDto;
import net.tralfamadore.dto.ListingListDto;

@Service
public class ListingService {
	private static Logger log = Logger.getLogger(ListingService.class);
	
	@Autowired
	private ListingDao listingDao;
	
	public List<Listing> getListings() {
		return listingDao.getListings();
	}
	
	public long saveListing(Listing listing) {
		return listingDao.saveListing(listing);
	}
	
	public void updateListing(Listing listing) {
		listingDao.updateListing(listing);
	}
	
	public List<ListingListDto> getListingListDtos() {
		return getListings().stream().map(listing -> new ListingListDto(listing.getId(), listing.getAddress().getStreet(), listing.getAddress().getCity(),
					listing.getAddress().getState(), listing.getAddress().getZipCode(), listing.getHouseType(), listing.getBedrooms(),
					listing.getBaths(), listing.getSquareFeet(), listing.getPrice(), listing.getMainPhoto(),
					listing.getPhotos().stream().map(Photo::getName).toArray(size -> new String[size])))
				.collect(toList());
	}
	
	public Listing getListing(long listingId) {
		return listingDao.getListing(listingId);
	}
	
	public void deleteListing(Listing listing) {
		listingDao.deleteListing(listing);
	}
	
	public ListingDetail getListingDetail(long listingId) {
		ListingDetail listingDetail = listingDao.getListingDetail(listingId);
		return listingDetail;
	}
	
	public void updateListingDetail(ListingDetail listingDetail) {
		listingDao.updateListingDetail(listingDetail);
	}
	
	public void saveListingDetail(ListingDetail listingDetail) {
		listingDao.saveListingDetail(listingDetail);
	}
	
	public void deleteListingDetail(ListingDetail listingDetail) {
		listingDao.deleteListingDetail(listingDetail);
	}
	
	public ListingDetailDto getListingDetailDto(long listingId) {
		ListingDetail listingDetail = getListingDetail(listingId);
				
		return new ListingDetailDto(listingId, listingDetail.getListing().getAddress().getStreet(), listingDetail.getListing().getAddress().getCity(),
				listingDetail.getListing().getAddress().getState(), listingDetail.getListing().getAddress().getZipCode(), listingDetail.getListing().getHouseType(),
				listingDetail.getListing().getBedrooms(), listingDetail.getListing().getBaths(), listingDetail.getListing().getSquareFeet(),
				listingDetail.getOverview(), listingDetail.getMasterBedroom(), listingDetail.getFullBathrooms(), listingDetail.getHalfBathrooms(),
				listingDetail.isDiningKitchen(), listingDetail.isDiningRoom(),
				listingDetail.getExteriorFeatures().stream().map(ExteriorFeature::getName).toArray(size -> new String[size]),
				listingDetail.getOtherRooms().stream().map(OtherRoom::getName).toArray(size -> new String[size]),
				listingDetail.getStories(), listingDetail.getExterior(), listingDetail.getParking(), listingDetail.getListing().getPrice(),
				listingDetail.getListing().getMainPhoto(),
				listingDetail.getListing().getPhotos().stream().map(Photo::getName).toArray(size -> new String[size]),
				listingDetail.getStatus(), listingDetail.getStyle(), listingDetail.getYearBuilt(), listingDetail.getSchoolDistrict());
	}
}
