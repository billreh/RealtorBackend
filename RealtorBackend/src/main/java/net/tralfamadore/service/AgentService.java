package net.tralfamadore.service;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.repository.AgentRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AgentService {
	private static Logger log = Logger.getLogger(AgentService.class);
	
	private AgentRepository agentRepository;

	@Inject
	public AgentService(AgentRepository agentRepository) {
		this.agentRepository = agentRepository;
	}
	
	public List<Agent> getAgents() {
		return agentRepository.findAll();
	}
	
	public long saveAgent(Agent agent) {
	    return agentRepository.save(agent).getId();
	}
	
	public  void updateAgent(Agent agent) {
	    agentRepository.save(agent);
	}

	public void deleteAgent(Agent agent) {
	    log.info("removing agent " + agent);
	    agentRepository.delete(agent);
	}

	public Agent getAgent(Long id) {
	    return agentRepository.findOne(id);
	}
}
