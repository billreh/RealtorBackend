package net.tralfamadore.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.Photo;
import net.tralfamadore.service.ListingService;

@ManagedBean
@SessionScoped
@Controller
@PropertySource("classpath:META-INF/images.properties")
public class PhotoController {
	private static Logger log = Logger.getLogger(PhotoController.class);
	
	@Autowired
	private ListingService listingService;
	
	@Autowired
	Environment env;
	
	private long listingId;
	
	private Listing listing;
	
	private Photo photoToRemove;

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
		String imgName = copyPhoto(event);
		listing.setMainPhoto(imgName);
		listingService.updateListing(listing);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Photo saved"));
	}

	public void fileUploadListener(FileUploadEvent event) {
		String imgName = copyPhoto(event);
		listing.getPhotos().add(new Photo(listing, imgName));
		listingService.updateListing(listing);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Photo saved"));
	}
	
	public void removePhoto() {
		listing.getPhotos().removeIf(p -> p.getId() == photoToRemove.getId());
		listingService.updateListing(listing);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Photo removed"));
	}
	
	private String copyPhoto(FileUploadEvent event) {
		String imgName = event.getFile().getFileName();
		String fileBase = env.getProperty("remote.base") + File.separator + listing.getId();
		String filePath = env.getProperty("remote.base") + File.separator + listing.getId()+ "\\" + imgName;
		try {
			File base = new File(fileBase);
			if(!base.exists())
				base.mkdir();
			event.getFile().write(filePath);
			base = new File(env.getProperty("image.base") + File.separator + listing.getId());
			if(!base.exists())
				base.mkdir();
			File dest = new File(env.getProperty("image.base") + File.separator + listing.getId()+ File.separator + imgName);
			if(dest.exists())
				Files.delete(dest.toPath());
			Files.copy(new File(filePath).toPath(), dest.toPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgName;
	}
}