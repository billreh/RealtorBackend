package net.tralfamadore.web;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.tralfamadore.ListingProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.domain.ExteriorFeature;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;
import net.tralfamadore.domain.OtherRoom;
import net.tralfamadore.service.AgentService;
import net.tralfamadore.service.ListingService;

@RunWith(PowerMockRunner.class)
public class TestListingController {
	@Mock
	private ListingService listingService;
	@Mock
	private AgentService agentService;
	@Mock
	private FacesContext facesContext;
	@Mock
	private HttpServletResponse response;
	@Mock
	private ExternalContext externalContext;
	
	private Validator validator;
	private ListingController listingController;
	private List<Listing> listings = new ArrayList<>();
	private List<Agent> agents = new ArrayList<>();
	private ListingDetail listingDetail;
	
	@Before
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
		listingController = new ListingController(listingService, agentService);
		listings.add(ListingProvider.getListing());
		listingDetail = ListingProvider.getListingDetail();
		agents.add(listings.get(0).getAgent());
		when(listingService.getListings()).thenReturn(listings);
		when(agentService.getAgents()).thenReturn(agents);
		listingController.init();
	}
	
	@Test
	public void testDetStatusItems() {
		List<SelectItem> statusItems = listingController.getStatusItems();
		assertEquals(statusItems.size(), 3);
		assertEquals(statusItems.get(0).getLabel(), "Active");
		assertEquals(statusItems.get(0).getValue(), "Active");
		assertEquals(statusItems.get(1).getLabel(), "Pending");
		assertEquals(statusItems.get(1).getValue(), "Pending");
		assertEquals(statusItems.get(2).getLabel(), "Sold");
		assertEquals(statusItems.get(2).getValue(), "Sold");
	}
	
	@Test
	public void testGetAgents() {
		List<SelectItem> theAgents = listingController.getAgents();
		assertEquals(theAgents.size(), 1);
		assertEquals(theAgents.get(0).getLabel(), "Bob Parker");
		assertEquals(theAgents.get(0).getValue(), "1");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testAddRemoveRoom() {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		listingController.addListing();
		listingController.setRoom("Den");
		listingController.addRoom();
		assertEquals(listingController.getListingDetail().getOtherRooms().size(), 1);
		assertEquals(listingController.getListingDetail().getOtherRooms().get(0).getName(), "Den");
		assertEquals(listingController.getListingDetail().getOtherRooms().get(0).getListingDetail(),
				listingController.getListingDetail());
		
		listingController.getListingDetail().getOtherRooms().get(0).setId(1L);
		OtherRoom or = new OtherRoom(listingController.getListingDetail(), "Den");
		or.setId(1L);
		listingController.setRoomToRemove(or);
		listingController.removeRoom();
		assertEquals(listingController.getListingDetail().getOtherRooms().size(), 0);
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testAddRemoveExteriorFeature() {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		listingController.addListing();
		listingController.setExteriorFeature("Porch");
		listingController.addFeature();
		assertEquals(listingController.getListingDetail().getExteriorFeatures().size(), 1);
		assertEquals(listingController.getListingDetail().getExteriorFeatures().get(0).getName(), "Porch");
		assertEquals(listingController.getListingDetail().getExteriorFeatures().get(0).getListingDetail(),
				listingController.getListingDetail());
		
		listingController.getListingDetail().getExteriorFeatures().get(0).setId(1L);
		ExteriorFeature ef = new ExteriorFeature(listingController.getListingDetail(), "Porch");
		ef.setId(1L);
		listingController.setFeatureToRemove(ef);
		listingController.removeFeature();
		assertEquals(listingController.getListingDetail().getExteriorFeatures().size(), 0);
	}
	
	@Test
	public void testGetAgentId() {
		listingController.setListing(ListingProvider.getListing());
		assertEquals(listingController.getAgentId(), "1");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testAddListing() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		listingController.addListing();
		assertNotNull(listingController.getListing());
		assertNotNull(listingController.getListingDetail());
		assertNotNull(listingController.getListing().getAddress());
		assertNotNull(listingController.getListing().getAgent());
		assertNotNull(listingController.getListingDetail().getExteriorFeatures());
		assertNotNull(listingController.getListingDetail().getOtherRooms());
		assertEquals(listingController.getTitle(), "Add");
		assertTrue(listingController.isNewListing());
		assertFalse(listingController.isNotNew());
		verify(response).sendRedirect("editListing.xhtml");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testGotoEdit() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		listingController.setListing(listings.get(0));
		listingController.gotoEdit();
		assertEquals(listingController.getListing(), listings.get(0));
		assertEquals(listingController.getListingDetail(), listingDetail);
		assertEquals(listingController.getListing().getAddress(), listings.get(0).getAddress());
		assertEquals(listingController.getListing().getAgent(), listings.get(0).getAgent());
		assertEquals(listingController.getListingDetail().getExteriorFeatures(), listingDetail.getExteriorFeatures());
		assertEquals(listingController.getListingDetail().getOtherRooms(), listingDetail.getOtherRooms());
		assertEquals(listingController.getTitle(), "Edit");
		assertFalse(listingController.isNewListing());
		assertTrue(listingController.isNotNew());
		verify(response).sendRedirect("editListing.xhtml");
	}
	@Test
	@PrepareForTest({FacesContext.class})
	public void testBack() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		listingController.back();
		verify(response).sendRedirect("listings.xhtml");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testEdit() {
		PowerMockito.mockStatic(FacesContext.class);
		listingController.setAgentId("1");
		listingController.setListing(listings.get(0));
		when(agentService.getAgent(1L)).thenReturn(listings.get(0).getAgent());
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		listingController.setListing(listings.get(0));
		listingController.gotoEdit();
		
		listingController.edit();
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
						return ((FacesMessage)o).getDetail().equals("The listing for 534 Queen St has been saved.");
					}
				}));
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testEditFailValidate() {
		PowerMockito.mockStatic(FacesContext.class);
		listingController.setAgentId("");
		listingController.setListing(listings.get(0));
		when(agentService.getAgent(1L)).thenReturn(null);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		listingController.setListing(listings.get(0));
		listingController.gotoEdit();
		
		listingController.edit();
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
						return ((FacesMessage)o).getDetail().equals("You must specify an agent");
					}
				}));
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testSave() {
		PowerMockito.mockStatic(FacesContext.class);
		listingController.setAgentId("1");
		listingController.setListing(listings.get(0));
		when(agentService.getAgent(1L)).thenReturn(listings.get(0).getAgent());
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		listingController.addListing();
		listingController.setListing(listings.get(0));
		listingController.setListingDetail(listingDetail);
		
		listingController.save();
		assertTrue(listingController.isNotNew());
		assertFalse(listingController.isNewListing());
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
						return ((FacesMessage)o).getDetail().equals("The listing for 534 Queen St has been saved.");
					}
				}));
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testSaveFailValidation() {
		PowerMockito.mockStatic(FacesContext.class);
		listingController.setAgentId("");
		listingController.setListing(listings.get(0));
		when(agentService.getAgent(1L)).thenReturn(listings.get(0).getAgent());
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		listingController.addListing();
		listingController.setListing(listings.get(0));
		listingController.setListingDetail(listingDetail);
		
		listingController.save();
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
						return ((FacesMessage)o).getDetail().equals("You must specify an agent");
					}
				}));
	}
	
	@Test
	public void testDelete() {
		listingController.setListing(listings.get(0));
		when(listingService.getListingDetail(1L)).thenReturn(listingDetail);
		listingController.delete();
		verify(listingService, times(1)).deleteListing(listings.get(0));
		verify(listingService, times(1)).deleteListingDetail(listingDetail);
	}
}