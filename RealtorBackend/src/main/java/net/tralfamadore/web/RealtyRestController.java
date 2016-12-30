package net.tralfamadore.web;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.dto.*;
import net.tralfamadore.service.ListingService;
import net.tralfamadore.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import java.util.List;

@RestController
@EnableWebMvc
public class RealtyRestController {
	private static Logger logger = Logger.getLogger(RealtyRestController.class);
	
	private ListingService listingService;
	
	private MailService mailService;
	
	@Inject
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
    public @ResponseBody ListingDetailDto getListingDetail(@RequestParam(value = "id") Long id) {
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