package net.tralfamadore.web;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.Photo;
import net.tralfamadore.service.ListingService;

@ManagedBean
@SessionScoped
@Controller
@PropertySource("classpath:META-INF/images.properties")
public class PhotoController {
	private static Logger log = Logger.getLogger(PhotoController.class);
	
	private ListingService listingService;
	
	private Environment env;
	
	private PhotoFileHelper photoFileService;
	
	private long listingId;
	
	private Listing listing;
	
	private Photo photoToRemove;

	@Autowired
	public PhotoController(ListingService listingService, Environment env, PhotoFileHelper photoFileService) {
		this.listingService = listingService;
		this.env = env;
		this.photoFileService = photoFileService;
	}
	
	public long getListingId() {
		return listingId;
	}

	public void setListingId(long listingId) {
		this.listingId = listingId;
		listing = listingService.getListing(listingId);
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}
	
	public Photo getPhotoToRemove() {
		return photoToRemove;
	}

	public void setPhotoToRemove(Photo photoToRemove) {
		this.photoToRemove = photoToRemove;
	}
	
	public boolean getHasMainPhoto() {
		return listing.getMainPhoto() != null && !listing.getMainPhoto().isEmpty();
	}
	
	public void back() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		try {
			response.sendRedirect("editListing.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mainPhotoUpload(FileUploadEvent event) {
		String imgName;
		try {
			imgName = copyPhoto(event);
		} catch(Exception e) {
			// Add message
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return;
		}
		listing.setMainPhoto(imgName);
		listingService.updateListing(listing);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Photo saved"));
	}

	public void fileUploadListener(FileUploadEvent event) {
		String imgName;
		try {
			imgName = copyPhoto(event);
		} catch(Exception e) {
			// Add message
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return;
		}
		listing.getPhotos().add(new Photo(listing, imgName));
		listingService.updateListing(listing);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Photo saved"));
	}
	
	public void removePhoto() {
		listing.getPhotos().removeIf(p -> Objects.equals(p.getId(), photoToRemove.getId()));
		listingService.updateListing(listing);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Photo removed"));
	}
	
	private String copyPhoto(FileUploadEvent event) throws Exception {
		String fileBase = env.getProperty("remote.base") + File.separator + listing.getId();
		String imgName = photoFileService.writeUploadedFile(event, fileBase);
		
		String filePath = fileBase + File.separator + imgName;
		String destDir = env.getProperty("image.base") + File.separator + listing.getId();
		photoFileService.copyFile(filePath, destDir, imgName);
		
		destDir = env.getProperty("source.base") + File.separator + listing.getId();
		photoFileService.copyFile(filePath, destDir, imgName);
		
		return imgName;
	}
}