package net.tralfamadore.web;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;
import net.tralfamadore.dto.ContactAgentDto;
import net.tralfamadore.dto.ListingDetailDto;
import net.tralfamadore.dto.ListingListDto;
import net.tralfamadore.dto.ServerResponse;
import net.tralfamadore.service.ListingService;
import net.tralfamadore.service.MailService;

@RunWith(PowerMockRunner.class)
public class TestRealtyRestController {
	@Mock
	private ListingService listingService;
	
	@Mock
	private MailService mailService;
	
	private List<Listing> listings;
	
	private ListingDetail listingDetail;
	
	private RealtyRestController realtyRestController;
	
	@Before
	public void init() {
		listings = new ArrayList<>();
		listingDetail = ListingProvider.getListingDetail();
		listings.add(ListingProvider.getListing());
		realtyRestController = new RealtyRestController(listingService, mailService);
	}
	
	@Test
	public void testGetListings() {
		when(listingService.getListings()).thenReturn(listings);
		when(listingService.getListingListDtos()).thenCallRealMethod();
		
		List<ListingListDto> listingDtos = realtyRestController.getListings();
		
		ListingListDto listingDto = new ListingListDto(1, "534 Queen St", "Philadelphia", "PA", "19147", "Row Home", 3,
				2.5f, 1642, 500000, "main.jpg", new String[] { "bedroom.jpg" });
		
		assertEquals(listingDtos.size(), 1);
		assertEquals(listingDtos.get(0), listingDto);
	}
	
	@Test
	public void testGetListingDetail() {
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		when(listingService.getListingDetailDto(anyLong())).thenCallRealMethod();
		
		ListingDetailDto ldd = realtyRestController.getListingDetail(1L);
		
		ListingDetailDto expected = new ListingDetailDto(1L, "534 Queen St", "Philadelphia", "PA", "19147", "Row Home",
				3, 2.5f, 1642, "Overview", "Yes", 2, 1, false, true, new String[] { "Sidewalks" },
				new String[] { "Living Room" }, 3, "Brick", "Street", 500000, "main.jpg",
				new String[] { "bedroom.jpg" }, "Active", "Traditional", 1924, "Philadelphia");
		
		assertEquals(ldd, expected);
	}
	
	@Test
	public void testContactAgent() throws MessagingException {
		ContactAgentDto cad = new ContactAgentDto(1L, "Bill Reh", "billreh@gmail.com", "215-555-1212",
				"I'm interested in 534 Queen St");
		when(listingService.getListing(anyLong())).thenReturn(listings.get(0));
		ServerResponse response = realtyRestController.contactAgent(cad);
		verify(mailService).sendMail(argThat(new ArgumentMatcher<String>() {
			public boolean matches(Object argument) {
				return "billreh@gmail.com".equals(argument);
			}
		}),
				argThat(new ArgumentMatcher<String>() {
					public boolean matches(Object argument) {
						return "bparker@matrix.gs".equals(argument);
					}
				}),
				argThat(new ArgumentMatcher<String>() {
					public boolean matches(Object argument) {
						return "Re: 534 Queen St".equals(argument);
					}
				}),
				argThat(new ArgumentMatcher<String>() {
					public boolean matches(Object argument) {
						return ("Thank you for your interest in 534 Queen St.  You can contact me at 222-555-1212 or " +
								"bparker@matrix.gs to set up a showing.\n\nSincerely\n\nBob Parker").equals(argument);
					}
				})
		);
		assertEquals(response.getStatus(), "OK");
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getStatusMessage(), "OK");
	}
}
