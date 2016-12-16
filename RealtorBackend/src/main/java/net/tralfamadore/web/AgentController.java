package net.tralfamadore.web;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.service.AgentService;

@ManagedBean
@SessionScoped
@Controller
public class AgentController {
	private static Logger log = Logger.getLogger(AgentController.class);
	
//	@Autowired
	private AgentService agentService;

//	@Autowired
	private Validator validator;
	
	private Agent agent;
	
	@Autowired
	public AgentController(AgentService agentService, Validator validator) {
		this.agentService = agentService;
		this.validator = validator;
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
	
	public void gotoEdit() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		try {
			response.sendRedirect("editAgent.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addAgent() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		agent = new Agent();
		try {
			response.sendRedirect("addAgent.xhtml");
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
		if(!validateAgent())
			return;

		log.info("saving agent " + agent);
		long id = agentService.saveAgent(agent);
		
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The agent " + agent.getFirstName() + " " + agent.getLastName() + " has been saved. (id: " + id + ")"));
		agent = new Agent();
	}
	
	public void edit() {
		if(!validateAgent())
			return;
		
		agentService.updateAgent(agent);
		
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The agent " + agent.getFirstName() + " " + agent.getLastName() + " has been updated"));
	}
	
	public void remove() {
		agentService.deleteAgent(agent);
		agent = new Agent();
	}
	
	private boolean validateAgent() {
		// Check for validation errors
		Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
		if(!violations.isEmpty()) {
			violations.forEach(violation -> {
				String msg = violation.getPropertyPath() + " " + violation.getMessage();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
			});
			return false;
		}
	
		return true;
	}
}
