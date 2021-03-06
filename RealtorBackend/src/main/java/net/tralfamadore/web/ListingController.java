package net.tralfamadore.web;

import net.tralfamadore.domain.*;
import net.tralfamadore.service.AgentService;
import net.tralfamadore.service.ListingService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ManagedBean
@SessionScoped
@Controller
public class ListingController {
	private ListingService listingService;

	private AgentService agentService;

	private String title;
	private Listing listing;
	private ListingDetail listingDetail;
	private String exteriorFeature;
	private String room;
	private String agentId;
    private List<SelectItem> agents;
	private List<SelectItem> statusItems;
	private ExteriorFeature featureToRemove;
	private OtherRoom roomToRemove;
	private boolean newListing = false;
	private boolean notNew = false;
	
	@Inject
	public ListingController(ListingService listingService, AgentService agentService) {
		this.listingService = listingService;
		this.agentService = agentService;
	}
	
	@PostConstruct
	public void init() {
		agents = new ArrayList<>();
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
		listingDetail.getOtherRooms().removeIf(r -> Objects.equals(r.getId(), roomToRemove.getId()));
	}
	
	public void removeFeature() {
		listingDetail.getExteriorFeatures().removeIf(f -> Objects.equals(f.getId(), featureToRemove.getId()));
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
	
	public void setListingDetail(ListingDetail listingDetail) {
		this.listingDetail = listingDetail;
	}
	
	public List<SelectItem> getStatusItems() {
		return statusItems;
	}
	
	public List<SelectItem> getAgents() {
		agents = new ArrayList<>();
        List<Agent> agentList = agentService.getAgents();
		agentList.forEach(agent -> agents.add(new SelectItem(agent.getId().toString(), agent.getFirstName() + " " + agent.getLastName())));
		return agents;
	}
	
	public String getAgentId() {
		if(listing.getAgent() == null || listing.getAgent().getId() == null)
			return "";
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
	
	public boolean isNotNew() {
		return notNew;
	}

	public void setNotNew(boolean notNew) {
		this.notNew = notNew;
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
		notNew = false;
		try {
			response.sendRedirect("editListing.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void gotoEdit() {
		newListing = false;
		notNew = true;
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
	
	public void back() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		listing = new Listing();
		listingDetail = new ListingDetail();
		try {
			response.sendRedirect("listings.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@CacheEvict(value = { "listingList" }, allEntries = true, beforeInvocation = true)
	public void edit() {
		if(!validateListing())
			return;
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
		if(!validateListing())
			return;
		Agent agent = agentService.getAgent(new Long(agentId));
		listing.setAgent(agent);
		listingService.saveListing(listing);
		listingDetail.setListing(listing);
		listingService.saveListingDetail(listingDetail);
		notNew = true;
		newListing = false;
		title = "Edit";
		
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

	private boolean validateListing() {
		if(agentId == null || agentId.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "You must specify an agent", null));
			return false;
		}
		return true;
	}
}