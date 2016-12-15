package net.tralfamadore.web;

import java.util.List;

import net.tralfamadore.dto.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.service.ListingService;
import net.tralfamadore.service.MailService;

@RestController
@EnableWebMvc
public class RealtyRestController {
	private static Logger logger = Logger.getLogger(RealtyRestController.class);
	
	private ListingService listingService;
	
	private MailService mailService;
	
	@Autowired
	public RealtyRestController(ListingService listingService, MailService mailService) {
		this.listingService = listingService;
		this.mailService = mailService;
	}
	
    @CrossOrigin
    @GetMapping("/listings")
    public @ResponseBody List<ListingListDto> getListings() {
    	return listingService.getListingListDtos();
    }

	@CrossOrigin
	@PostMapping("/listings/search")
	public @ResponseBody List<ListingListDto> getListings(@RequestBody SearchDto searchDto) {
        logger.info("got searchDto: " + searchDto);
        return listingService.getListingListDtos(searchDto);
    }

    @CrossOrigin
    @GetMapping("/listing-detail")
    public @ResponseBody ListingDetailDto getListingDetail(@RequestParam(value = "id", required=true) Long id) {
    	return listingService.getListingDetailDto(id);
    }
    
    @CrossOrigin
    @PostMapping("/contact-agent")
	public @ResponseBody ServerResponse contactAgent(@RequestBody ContactAgentDto contactAgent) {
		Listing listing = listingService.getListing(contactAgent.getListingId());
		Agent agent = listing.getAgent();
		String message = "Thank you for your interest in " + listing.getAddress().getStreet()
				+ ".  You can contact me at " + agent.getContactNumber() + " or " + agent.getEmail()
				+ " to set up a showing.\n\nSincerely\n\n" + agent.getFirstName() + " " + agent.getLastName();
		try {
			mailService.sendMail(contactAgent.getEmail(), agent.getEmail(), "Re: " + listing.getAddress().getStreet(),
					message);
		} catch (Exception e) {
			e.printStackTrace();
			return new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), 500, e.getMessage());
		}
		return new ServerResponse("OK", 200, "OK");
	}
    
    @CrossOrigin
    @GetMapping("/featured-listings")
    public @ResponseBody List<FeaturedListingDto> getFeaturedListings() {
    	return listingService.getFeaturedListingDtos();
    }
}