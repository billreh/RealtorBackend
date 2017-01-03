package net.tralfamadore.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.tralfamadore.ListingProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.event.DragDropEvent;

import net.tralfamadore.domain.FeaturedListing;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.service.ListingService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@RunWith(PowerMockRunner.class)
public class TestFeaturedListingController {
	@Mock
	private ListingService listingService;
	
	@Mock
	private DragDropEvent ddEvent;

	@Mock
    private FacesContext facesContext;
	
	private List<FeaturedListing> featuredListings = new ArrayList<>();
	
	private FeaturedListingController featuredListingController;
	
	@Before
	public void init() {
		featuredListingController = new FeaturedListingController(listingService);
		Listing listing = ListingProvider.getListing();
		featuredListings.add(new FeaturedListing(null, listing));
	}

	@Test
	@PrepareForTest({FacesContext.class})
	public void testDropEvent() {
        PowerMockito.mockStatic(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(listingService.getFeaturedListings()).thenReturn(new ArrayList<>());
		when(ddEvent.getData()).thenReturn(featuredListings.get(0).getListing());
		featuredListingController.dropEvent(ddEvent);
		assertEquals(featuredListingController.getFeaturedListings(), featuredListings);
		// make sure it doesn't add duplicates
		featuredListingController.dropEvent(ddEvent);
		assertEquals(featuredListingController.getFeaturedListings(), featuredListings);
        verify(facesContext).addMessage(argThat(
                new ArgumentMatcher<String>() {
                    @Override
                    public boolean matches(Object o) {
                        return o == null;
                    }
                }), argThat(
                new ArgumentMatcher<FacesMessage>() {
                    @Override
                    public boolean matches(Object o) {
                        return ((FacesMessage)o).getDetail().equals("Featured listing updated");
                    }
                }));
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testRemove() {
        PowerMockito.mockStatic(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(listingService.getFeaturedListings()).thenReturn(new ArrayList<>());
		when(ddEvent.getData()).thenReturn(featuredListings.get(0).getListing());
		featuredListingController.dropEvent(ddEvent);
		assertEquals(featuredListingController.getFeaturedListings(), featuredListings);
		featuredListingController.setListingToRemove(featuredListings.get(0));
		featuredListingController.removeListing();
		assertTrue(featuredListingController.getFeaturedListings().isEmpty());
        verify(facesContext, times(2)).addMessage(argThat(
                new ArgumentMatcher<String>() {
                    @Override
                    public boolean matches(Object o) {
                        return o == null;
                    }
                }), argThat(
                new ArgumentMatcher<FacesMessage>() {
                    @Override
                    public boolean matches(Object o) {
                        return ((FacesMessage)o).getDetail().equals("Featured listing updated");
                    }
                }));
	}
}
