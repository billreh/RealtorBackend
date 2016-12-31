package net.tralfamadore.web;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.core.env.Environment;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.Photo;
import net.tralfamadore.service.ListingService;

@RunWith(PowerMockRunner.class)
public class TestPhotoController {
	@Mock
	private ListingService listingService;
	
	@Mock
	private Environment env;
	
	@Mock
	private PhotoFileHelper photoFileService;
	
	@Mock
	private FacesContext facesContext;
	
	@Mock
	private ExternalContext externalContext;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private FileUploadEvent fileUploadEvent;
	
	@Mock
	private UploadedFile uploadedFile;
	
	private Listing listing;
	
	private PhotoController photoController;
	
	@Before
	public void init() {
		photoController = new PhotoController(listingService, env, photoFileService);
		listing = ListingProvider.getListing();
	}
	
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testBack() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		
		photoController.back();
		
		verify(response).sendRedirect("editListing.xhtml");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testMainPhotoUpload() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(uploadedFile.getFileName()).thenReturn("image.jpg");
		when(fileUploadEvent.getFile()).thenReturn(uploadedFile);
		when(env.getProperty("remote.base")).thenReturn("/remote");
		when(env.getProperty("image.base")).thenReturn("/image");
		when(env.getProperty("source.base")).thenReturn("/source");
		when(photoFileService.writeUploadedFile(fileUploadEvent, "/remote/" + listing.getId())).thenReturn("image.jpg");
		when(listingService.getListing(anyInt())).thenReturn(listing);
		photoController.setListingId(listing.getId());
		assertEquals(photoController.getListing().getPhotos().size(), 1);
		
		photoController.mainPhotoUpload(fileUploadEvent);
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
						return ((FacesMessage)o).getDetail().equals("Photo saved");
					}
				}));
		assertEquals(photoController.getListing().getMainPhoto(), "image.jpg");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testFileUploadListener() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(fileUploadEvent.getFile()).thenReturn(uploadedFile);
		when(uploadedFile.getFileName()).thenReturn("image.jpg");
		when(env.getProperty("remote.base")).thenReturn("/remote");
		when(env.getProperty("image.base")).thenReturn("/image");
		when(env.getProperty("source.base")).thenReturn("/source");
		when(photoFileService.writeUploadedFile(anyObject(), anyString())).thenReturn("image.jpg");
		when(listingService.getListing(anyInt())).thenReturn(listing);
		photoController.setListingId(listing.getId());
		assertEquals(photoController.getListing().getPhotos().size(), 1);
		
		photoController.fileUploadListener(fileUploadEvent);
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
						return ((FacesMessage)o).getDetail().equals("Photo saved");
					}
				}));
		assertEquals(photoController.getListing().getPhotos().size(), 2);
		assertEquals(photoController.getListing().getPhotos().get(1).getName(), "image.jpg");
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testRemovePhoto() {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		Photo photo = new Photo(listing, "bedroom.jpg");
		photo.setId(1L);
		when(listingService.getListing(anyInt())).thenReturn(listing);
		photoController.setListingId(listing.getId());
		photoController.setPhotoToRemove(photo);
		assertEquals(photoController.getListing().getPhotos().size(), 1);
		
		photoController.removePhoto();
		
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
						return ((FacesMessage)o).getDetail().equals("Photo removed");
					}
				}));
		assertEquals(photoController.getListing().getPhotos().size(), 0);
	}
}
