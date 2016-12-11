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
		Address address = new Address(1L, "534 Queen St", "Philadelphia", "PA", "19147");
		Agent agent = new Agent(1L, new ArrayList<>(), "Bob", "Parker", "222-555-1212", "bparker@matrix.gs");
		List<Photo> photos = new ArrayList<>();
		Listing listing = new Listing(1L, address, 500000, "Row Home", 2.5f, 3, 1642, agent, "main.jpg", null);
		photos.add(new Photo(listing, "bedroom.jpg"));
		agent.getListings().add(listing);
		listing.setPhotos(photos);
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
