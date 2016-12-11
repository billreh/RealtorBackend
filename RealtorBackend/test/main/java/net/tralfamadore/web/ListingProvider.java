package net.tralfamadore.web;

import java.util.ArrayList;
import java.util.List;

import net.tralfamadore.domain.Address;
import net.tralfamadore.domain.Agent;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.Photo;

public class ListingProvider {
	public static Listing getListing() {
		Address address = new Address(1L, "534 Queen St", "Philadelphia", "PA", "19147");
		Agent agent = new Agent(1L, new ArrayList<>(), "Bob", "Parker", "222-555-1212", "bparker@matrix.gs");
		List<Photo> photos = new ArrayList<>();
		Listing listing = new Listing(1L, address, 500000, "Row Home", 2.5f, 3, 1642, agent, "main.jpg", null);
		photos.add(new Photo(listing, "bedroom.jpg"));
		agent.getListings().add(listing);
		listing.setPhotos(photos);
		return listing;
	}
}
