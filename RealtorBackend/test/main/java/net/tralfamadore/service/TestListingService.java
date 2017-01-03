package net.tralfamadore.service;

import net.tralfamadore.config.AppConfig;
import net.tralfamadore.domain.*;
import net.tralfamadore.dto.FeaturedListingDto;
import net.tralfamadore.dto.ListingDetailDto;
import net.tralfamadore.dto.ListingListDto;
import net.tralfamadore.dto.SearchDto;
import net.tralfamadore.ListingProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Class: TestListingService
 * Created by billreh on 1/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
@WebAppConfiguration
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "OptionalGetWithoutIsPresent"})
public class TestListingService {
    @Inject
    private ListingService listingService;

    @Inject
    private AgentService agentService;

    @Test
    public void testListingService() {
        Listing listing = ListingProvider.getListing();
        listing.setId(null);
        listing.setPhotos(new ArrayList<>());
        listing.getAgent().setId(null);
        listing.getAgent().setListings(new ArrayList<>());


        agentService.saveAgent(listing.getAgent());
        long id = listingService.saveListing(listing);
        listing = listingService.getListing(id);
        assertNotNull(listing);

        listing.getPhotos().add(new Photo(listing, "house.jpg"));
        listingService.updateListing(listing);

        listing = listingService.getListing(id);
        assertTrue(listingService.getListings().contains(listing));

        Optional<ListingListDto> dto = listingService.getListingListDtos().stream().filter(d -> d.getId() == id).findFirst();
        assertTrue(dto.isPresent());
        assertEquals(id, dto.get().getId());
        assertEquals(listing.getPrice(), dto.get().getPrice());
        assertEquals(listing.getPhotos().get(0).getName(), dto.get().getPhotos()[0]);

        ListingDetail listingDetail = ListingProvider.getListingDetail();
        listingDetail.setId(null);
        listingDetail.setListing(listing);

        listingService.saveListingDetail(listingDetail);
        listingDetail.setOtherRooms(new ArrayList<>());
        listingDetail.setExteriorFeatures(new ArrayList<>());
        listingDetail.getOtherRooms().add(new OtherRoom(listingDetail, "Den"));
        listingDetail.getExteriorFeatures().add(new ExteriorFeature(listingDetail, "Porch"));
        listingService.updateListingDetail(listingDetail);
        listingDetail = listingService.getListingDetail(id);
        assertEquals(listingDetail, listingService.getListingDetail(id));
        ListingDetailDto ldDto = listingService.getListingDetailDto(id);
        assertEquals(ldDto.getId(), listingDetail.getListing().getId().longValue());
        assertEquals(ldDto.getExterior(), listingDetail.getExterior());
        assertEquals(ldDto.getExteriorFeatures()[0], listingDetail.getExteriorFeatures().get(0).getName());

        FeaturedListing featuredListing = new FeaturedListing(listing);
        long featuredListingId = listingService.saveFeaturedListing(featuredListing);
        assertTrue(featuredListingId > 0);
        featuredListing.setId(featuredListingId);
        assertTrue(listingService.getFeaturedListings().contains(featuredListing));
        Optional<FeaturedListingDto> flDto = listingService.getFeaturedListingDtos().stream().
                filter(fl -> fl.getId() == id).findFirst();
        assertTrue(flDto.isPresent());
        assertTrue(flDto.get().getId() == id);

        SearchDto searchDto = new SearchDto(listing.getAddress().getCity(), listing.getAddress().getState(),
                listing.getAddress().getZipCode(), listing.getHouseType(), listing.getBedrooms(), listing.getBaths(),
                listing.getSquareFeet(), listing.getPrice());
        List<ListingListDto> listingListDtos = listingService.getListingListDtos(searchDto);
        System.out.println("DTOs: " + listingListDtos);

        assertEquals(listingListDtos.get(1).getId(), listing.getId().longValue());
        assertEquals(listingListDtos.get(1).getCity(), listing.getAddress().getCity());
        assertEquals(listingListDtos.get(1).getState(), listing.getAddress().getState());
        assertEquals(listingListDtos.get(1).getZipCode(), listing.getAddress().getZipCode());
        assertEquals(listingListDtos.get(1).getHouseType(), listing.getHouseType());
        assertEquals(listingListDtos.get(1).getBedrooms(), listing.getBedrooms());
        assertTrue(listingListDtos.get(1).getBathrooms() == listing.getBaths());
        assertEquals(listingListDtos.get(1).getSquareFeet(), listing.getSquareFeet());
        assertEquals(listingListDtos.get(1).getPrice(), listing.getPrice());

        listingService.deleteFeaturedListing(featuredListing);
        assertFalse(listingService.getFeaturedListings().contains(featuredListing));
        listingService.deleteListingDetail(listingDetail);
        assertNull(listingService.getListingDetail(id));
        listingService.deleteListing(listing);
        assertNull(listingService.getListing(id));
    }
}
