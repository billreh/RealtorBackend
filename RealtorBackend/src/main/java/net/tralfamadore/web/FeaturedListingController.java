package net.tralfamadore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.tralfamadore.domain.FeaturedListing;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.service.ListingService;

@ManagedBean
@SessionScoped
@Controller
public class FeaturedListingController {
	private static Logger log = Logger.getLogger(FeaturedListingController.class);
	
	private ListingService listingService;
	
	private List<FeaturedListing> featuredListings = new ArrayList<>();
	
	private FeaturedListing listingToRemove;
	
	@Autowired
	public FeaturedListingController(ListingService listingService) {
		this.listingService = listingService;
	}
	
	@PostConstruct
	public void init() {
		featuredListings = listingService.getFeaturedListings();
	}
	public List<FeaturedListing> getFeaturedListings() {
		return featuredListings;
	}

	public FeaturedListing getListingToRemove() {
		return listingToRemove;
	}
	
	public void setListingToRemove(FeaturedListing listingToRemove) {
		this.listingToRemove = listingToRemove;
	}
	
	public void dropEvent(DragDropEvent ddEvent) {
		for(FeaturedListing fl : featuredListings) {
			if(fl.getListing().getId().equals(((Listing)ddEvent.getData()).getId())) {
				return;
			}
		}
		FeaturedListing fl = new FeaturedListing((Listing) ddEvent.getData());
		listingService.saveFeaturedListing(fl);
		featuredListings.add(fl);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Featured listing updated"));
    }
	
	public void removeListing() {
	    log.info("removing listing " + listingToRemove);
		featuredListings.removeIf(fl -> {
			if(Objects.equals(fl.getListing().getId(), listingToRemove.getListing().getId())) {
				listingService.deleteFeaturedListing(fl);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Featured listing updated"));
				return true;
			}
			return false;
		});
	}
}