package net.tralfamadore.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	
	@Autowired
	private ListingService listingService;
	
	List<FeaturedListing> featuredListings;
	
	private FeaturedListing listingToRemove;
	
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
		};
		FeaturedListing fl = new FeaturedListing((Listing) ddEvent.getData());
		listingService.saveFeaturedListing(fl);
		featuredListings.add(fl);
    }
	
	public void removeListing() {
		featuredListings.removeIf(fl -> {
			if(fl.getListing().getId() == listingToRemove.getListing().getId()) {
				listingService.deleteFeaturedListing(fl);
				return true;
			}
			return false;
		});
	}
}