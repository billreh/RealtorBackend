package net.tralfamadore.service;

import net.tralfamadore.repository.FeaturedListingRepository;
import net.tralfamadore.dao.ListingDao;
import net.tralfamadore.repository.ListingDetailRepository;
import net.tralfamadore.repository.ListingRepository;
import net.tralfamadore.domain.*;
import net.tralfamadore.dto.FeaturedListingDto;
import net.tralfamadore.dto.ListingDetailDto;
import net.tralfamadore.dto.ListingListDto;
import net.tralfamadore.dto.SearchDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ListingService {
	private static Logger log = Logger.getLogger(ListingService.class);
	
	private final ListingDao listingDao;
	private final ListingRepository listingRepository;
	private final ListingDetailRepository listingDetailRepository;
	private final FeaturedListingRepository featuredListingRepository;

    @Inject
    public ListingService(ListingDao listingDao,
						  ListingRepository listingRepository,
						  ListingDetailRepository listingDetailRepository,
						  FeaturedListingRepository featuredListingRepository)
	{
        this.listingDao = listingDao;
        this.listingRepository = listingRepository;
        this.listingDetailRepository = listingDetailRepository;
        this.featuredListingRepository = featuredListingRepository;
    }

    public List<Listing> getListings() {
        log.info("fetching listings");
        return listingRepository.findAll();
	}
	
	public long saveListing(Listing listing) {
        return listingDao.saveListing(listing);
	}
	
	public void updateListing(Listing listing) {
        listingRepository.save(listing);
	}
	
	public Listing getListing(long listingId) {
        return listingRepository.findOne(listingId);
	}
	
	public void deleteListing(Listing listing) {
        listingRepository.delete(listing);
	}
	
	public ListingDetail getListingDetail(long listingId) {
        return listingDetailRepository.findByListingId(listingId);
	}
	
	public void updateListingDetail(ListingDetail listingDetail) {
		listingDao.updateListingDetail(listingDetail);
	}
	
	public void saveListingDetail(ListingDetail listingDetail) {
		listingDao.saveListingDetail(listingDetail);
	}
	
	public void deleteListingDetail(ListingDetail listingDetail) {
        listingDetailRepository.delete(listingDetail);
	}
	
	public List<FeaturedListing> getFeaturedListings() {
        return featuredListingRepository.findAll();
	}
	
	public long saveFeaturedListing(FeaturedListing featuredListing) {
        return featuredListingRepository.save(featuredListing).getId();
	}
	
	public void deleteFeaturedListing(FeaturedListing featuredListing) {
        featuredListingRepository.delete(featuredListing);
	}
	
	public List<ListingListDto> getListingListDtos() {
		return getListings().stream().map(listing -> new ListingListDto(listing.getId(), listing.getAddress().getStreet(), listing.getAddress().getCity(),
					listing.getAddress().getState(), listing.getAddress().getZipCode(), listing.getHouseType(), listing.getBedrooms(),
					listing.getBaths(), listing.getSquareFeet(), listing.getPrice(), listing.getMainPhoto(),
					listing.getPhotos().stream().map(Photo::getName).toArray(String[]::new)))
				.collect(toList());
	}
	
	public ListingDetailDto getListingDetailDto(long listingId) {
		ListingDetail listingDetail = getListingDetail(listingId);
				
		return new ListingDetailDto(listingId, listingDetail.getListing().getAddress().getStreet(), listingDetail.getListing().getAddress().getCity(),
				listingDetail.getListing().getAddress().getState(), listingDetail.getListing().getAddress().getZipCode(), listingDetail.getListing().getHouseType(),
				listingDetail.getListing().getBedrooms(), listingDetail.getListing().getBaths(), listingDetail.getListing().getSquareFeet(),
				listingDetail.getOverview(), listingDetail.getMasterBedroom(), listingDetail.getFullBathrooms(), listingDetail.getHalfBathrooms(),
				listingDetail.isDiningKitchen(), listingDetail.isDiningRoom(),
				listingDetail.getExteriorFeatures().stream().map(ExteriorFeature::getName).toArray(String[]::new),
				listingDetail.getOtherRooms().stream().map(OtherRoom::getName).toArray(String[]::new),
				listingDetail.getStories(), listingDetail.getExterior(), listingDetail.getParking(), listingDetail.getListing().getPrice(),
				listingDetail.getListing().getMainPhoto(),
				listingDetail.getListing().getPhotos().stream().map(Photo::getName).toArray(String[]::new),
				listingDetail.getStatus(), listingDetail.getStyle(), listingDetail.getYearBuilt(), listingDetail.getSchoolDistrict());
	}
	
	public List<FeaturedListingDto> getFeaturedListingDtos() {
		List<FeaturedListing> featuredListings = getFeaturedListings();
		return featuredListings.stream().map(fl -> new FeaturedListingDto(fl.getListing().getId(), fl.getListing().getMainPhoto())).collect(toList());
	}

    public List<ListingListDto> getListingListDtos(SearchDto searchDto) {
        log.info("fetching listing dtos for " + searchDto);
		return listingDao.getListings(searchDto).stream().map(l -> new ListingListDto(l.getId(), l.getAddress().getStreet(), l.getAddress().getCity(),
				l.getAddress().getState(), l.getAddress().getZipCode(), l.getHouseType(), l.getBedrooms(), l.getBaths(), l.getSquareFeet(),
				l.getPrice(), l.getMainPhoto(), l.getPhotos().stream().map(Photo::getName).toArray(String[]::new))).collect(toList());
    }
}