package net.tralfamadore.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.event.DragDropEvent;

import net.tralfamadore.domain.Address;
import net.tralfamadore.domain.Agent;
import net.tralfamadore.domain.FeaturedListing;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.Photo;
import net.tralfamadore.service.ListingService;

@RunWith(PowerMockRunner.class)
public class TestFeaturedListingController {
	@Mock
	private ListingService listingService;
	
	@Mock
	DragDropEvent ddEvent;
	
	private List<FeaturedListing> featuredListings = new ArrayList<>();
	
	private FeaturedListingController featuredListingController;
	
	@Before
	public void init() {
		featuredListingController = new FeaturedListingController(listingService);
		Listing listing = ListingProvider.getListing();
		featuredListings.add(new FeaturedListing(null, listing));
	}

	@Test
	public void testDropEvent() {
		when(listingService.getFeaturedListings()).thenReturn(new ArrayList<>());
		when(ddEvent.getData()).thenReturn(featuredListings.get(0).getListing());
		featuredListingController.dropEvent(ddEvent);
		assertEquals(featuredListingController.getFeaturedListings(), featuredListings);
		// make sure it doesn't add duplicates
		featuredListingController.dropEvent(ddEvent);
		assertEquals(featuredListingController.getFeaturedListings(), featuredListings);
	}
	
	@Test
	public void testRemove() {
		when(listingService.getFeaturedListings()).thenReturn(new ArrayList<>());
		when(ddEvent.getData()).thenReturn(featuredListings.get(0).getListing());
		featuredListingController.dropEvent(ddEvent);
		assertEquals(featuredListingController.getFeaturedListings(), featuredListings);
		featuredListingController.setListingToRemove(featuredListings.get(0));
		featuredListingController.removeListing();
		assertTrue(featuredListingController.getFeaturedListings().isEmpty());
	}
}
