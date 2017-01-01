package net.tralfamadore.web;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.service.AgentService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ManagedBean
@SessionScoped
@Controller
public class AgentController {
	private static Logger log = Logger.getLogger(AgentController.class);
	
	private AgentService agentService;

	private Agent agent;

	private boolean newAgent;

	@Inject
	public AgentController(AgentService agentService) {
		this.agentService = agentService;
	}

	public List<Agent> getAgents() {
		return agentService.getAgents();
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

    public boolean isNewAgent() {
        return newAgent;
    }

    public void gotoEdit() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		newAgent = false;
		try {
			response.sendRedirect("editAgent.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addAgent() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        newAgent = true;
		agent = new Agent();
		try {
			response.sendRedirect("editAgent.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void back() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		agent = new Agent();
		try {
			response.sendRedirect("agents.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		log.info("saving agent " + agent);
		long id = agentService.saveAgent(agent);
		
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The agent " + agent.getFirstName() + " " + agent.getLastName() + " has been saved. (id: " + id + ")"));
		agent = new Agent();
	}
	
	public void edit() {
		agentService.updateAgent(agent);
		
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The agent " + agent.getFirstName() + " " + agent.getLastName() + " has been updated"));
	}
	
	public void remove() {
		agentService.deleteAgent(agent);
		agent = new Agent();
	}
}
