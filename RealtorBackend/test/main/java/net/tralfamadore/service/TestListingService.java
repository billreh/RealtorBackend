package net.tralfamadore.service;

import net.tralfamadore.config.AppConfig;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.dto.ListingListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

/**
 * Class: TestListingService
 * Created by billreh on 1/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
@WebAppConfiguration
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class TestListingService {
    @Inject
    private ListingService listingService;

    @Test
    public void testListingService() {
        for(Listing listing : listingService.getListings())
            System.out.println(listing);

        for(ListingListDto dto : listingService.getListingListDtos())
            System.out.println(dto);

        System.out.println(listingService.getListing(1L));
        System.out.println(listingService.getListingDetail(1L));
    }
}
