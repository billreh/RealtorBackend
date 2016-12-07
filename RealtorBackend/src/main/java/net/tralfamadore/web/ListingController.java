package net.tralfamadore.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import net.tralfamadore.domain.Address;
import net.tralfamadore.domain.Agent;
import net.tralfamadore.domain.ExteriorFeature;
import net.tralfamadore.domain.Listing;
import net.tralfamadore.domain.ListingDetail;
import net.tralfamadore.domain.OtherRoom;
import net.tralfamadore.domain.Photo;
import net.tralfamadore.service.AgentService;
import net.tralfamadore.service.ListingService;

@ManagedBean
@SessionScoped
@Controller
@PropertySource("classpath:META-INF/images.properties")
public class ListingController {
	private static Logger log = Logger.getLogger(ListingController.class);
	
	@Autowired
	private ListingService listingService;

	@Autowired
	private AgentService agentService;

	@Autowired
	Validator validator;
	
	@Autowired
	Environment env;
	
	private String title;
	private Listing listing;
	private ListingDetail listingDetail;
	private String exteriorFeature;
	private String room;
	private String agentId;
	private List<Agent> agentList;
	private List<SelectItem> agents;
	private List<SelectItem> statusItems;
	private ExteriorFeature featureToRemove;
	private OtherRoom roomToRemove;
	private boolean newListing = false;
	
	@PostConstruct
	public void init() {
		agents = new ArrayList<>();
		agentList = agentService.getAgents();
		agentList.forEach(agent -> agents.add(new SelectItem(agent.getId().toString(), agent.getFirstName() + " " + agent.getLastName())));
		statusItems = new ArrayList<>();
		statusItems.add(new SelectItem("Active", "Active"));
		statusItems.add(new SelectItem("Pending", "Pending"));
		statusItems.add(new SelectItem("Sold", "Sold"));
	}
	
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void addRoom() {
		listingDetail.getOtherRooms().add(new OtherRoom(listingDetail, room));
		room = "";
	}
	
	public void addFeature() {
		listingDetail.getExteriorFeatures().add(new ExteriorFeature(listingDetail, exteriorFeature));
		exteriorFeature = "";
	}
	
	public void removeRoom() {
		listingDetail.getOtherRooms().removeIf(r -> r.getId() == roomToRemove.getId());
	}
	
	public void removeFeature() {
		listingDetail.getExteriorFeatures().removeIf(f -> f.getId() == featureToRemove.getId());
	}
	
	public OtherRoom getRoomToRemove() {
		return roomToRemove;
	}

	public void setRoomToRemove(OtherRoom roomToRemove) {
		this.roomToRemove = roomToRemove;
	}

	public ExteriorFeature getFeatureToRemove() {
		return featureToRemove;
	}

	public void setFeatureToRemove(ExteriorFeature featureToRemove) {
		this.featureToRemove = featureToRemove;
	}

	public String getExteriorFeature() {
		return exteriorFeature;
	}

	public void setExteriorFeature(String exteriorFeature) {
		this.exteriorFeature = exteriorFeature;
	}

	@Cacheable(value = { "listingList" })
	public List<Listing> getListings() {
		// this is cached, so we don't mind banging on the service
		log.info(env.getProperty("remote.base"));
		return listingService.getListings();
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}
	
	public ListingDetail getListingDetail() {
		return listingDetail;
	}
	
	public List<SelectItem> getStatusItems() {
		return statusItems;
	}
	
	public List<SelectItem> getAgents() {
		return agents;
	}
	
	public String getAgentId() {
		return listing.getAgent().getId().toString();
	}
	
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgent() {
		return agentId;
	}
	
	public void setAgent(String agent) {
		this.agentId = agent;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean isNewListing() {
		return newListing;
	}
	
	public void addListing() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		listing = new Listing();
		listing.setAgent(new Agent());
		listing.setAddress(new Address());
		listing.getAgent().setId(0L);
		listingDetail = new ListingDetail();
		listingDetail.setExteriorFeatures(new ArrayList<>());
		listingDetail.setOtherRooms(new ArrayList<>());
		title = "Add";
		newListing = true;
		try {
			response.sendRedirect("editListing.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void gotoEdit() {
		newListing = false;
		title = "Edit";
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		listingDetail = listingService.getListingDetail(listing.getId());
		try {
			response.sendRedirect("editListing.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@CacheEvict(value = { "listingList" }, allEntries = true, beforeInvocation = true)
	public void edit() {
		Agent agent = agentService.getAgent(new Long(agentId));
		listing.setAgent(agent);
		listingService.updateListing(listing);
		listingService.updateListingDetail(listingDetail);
		
		// show update message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The listing for " + listing.getAddress().getStreet() + " has been saved."));
	}
	
	@CacheEvict(value = { "listingList" }, allEntries = true, beforeInvocation = true)
	public void save() {
		Agent agent = agentService.getAgent(new Long(agentId));
		listing.setAgent(agent);
		listingService.saveListing(listing);
		listingDetail.setListing(listing);
		listingService.saveListingDetail(listingDetail);
		
		// show update message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The listing for " + listing.getAddress().getStreet() + " has been saved."));
	}
	
	@CacheEvict(value = { "listingList" }, allEntries = true, beforeInvocation = true)
	public void delete() {
		listingDetail = listingService.getListingDetail(listing.getId());
		listingService.deleteListingDetail(listingDetail);
		listing.setAgent(null);
		listingService.deleteListing(listing);
	}
}