package net.tralfamadore;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.tralfamadore.domain.Listing;
import net.tralfamadore.dto.ListingListDto;
import net.tralfamadore.service.ListingService;


public class Main {
	public static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String args[]) throws Exception {
		/*
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("net.tralfamadore");
		context.refresh();

		ListingService listingService = context.getBean(ListingService.class);
		
//		for(Listing listing : listingService.getListings())
//			logger.info(listing);
		
//		for(ListingListDto dto : listingService.getListingListDtos())
//			logger.info(dto);
		
		Listing listing = listingService.getListings().get(0);
		logger.info(listing);
		
		logger.info(listingService.getListingDetail(listing.getId()));
		
		logger.info(listingService.getListingDetailDto(listing.getId()));
		
		context.close();
		*/
	}
}
