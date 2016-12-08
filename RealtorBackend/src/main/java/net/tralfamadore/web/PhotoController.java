package net.tralfamadore.web;

import java.io.File;
import java.nio.file.Files;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	private static String localBase = "C:\\Users\\Bill\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\RealtorBackend\\resources\\img";
	private static String localBase2 = "D:\\git\\RealtorBackend\\src\\main\\webapp\\resources\\img";
	
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
	
	public void mainPhotoUpload(FileUploadEvent event) {
		String imgName = copyPhoto(event);
		listing.setMainPhoto(imgName);
		listingService.updateListing(listing);
	}

	public void fileUploadListener(FileUploadEvent event) {
		String imgName = copyPhoto(event);
		listing.getPhotos().add(new Photo(listing, imgName));
		listingService.updateListing(listing);
	}
	
	public void removePhoto() {
		listing.getPhotos().removeIf(p -> p.getId() == photoToRemove.getId());
		listingService.updateListing(listing);
	}
	
	private String copyPhoto(FileUploadEvent event) {
		String imgName = event.getFile().getFileName();
		String fileBase = env.getProperty("remote.base") + "\\" + listing.getId();
		String filePath = env.getProperty("remote.base") + "\\" + listing.getId()+ "\\" + imgName;
		try {
			File base = new File(fileBase);
			if(!base.exists())
				base.mkdir();
			event.getFile().write(filePath);
			base = new File(localBase + "\\" + listing.getId());
			if(!base.exists())
				base.mkdir();
			File dest = new File(localBase + "\\" + listing.getId()+ "\\" + imgName);
			if(dest.exists())
				Files.delete(dest.toPath());
			Files.copy(new File(filePath).toPath(), dest.toPath());
			base = new File(localBase2 + "\\" + listing.getId());
			if(!base.exists())
				base.mkdir();
			dest = new File(localBase2 + "\\" + listing.getId()+ "\\" + imgName);
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
