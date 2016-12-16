package net.tralfamadore.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tralfamadore.dao.AgentDao;
import net.tralfamadore.domain.Agent;

@Service
public class AgentService {
	private static Logger log = Logger.getLogger(AgentService.class);
	
	private AgentDao agentDao;

	@Autowired
	public AgentService(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
	
	public List<Agent> getAgents() {
		return agentDao.getAgents();
	}
	
	public long saveAgent(Agent agent) {
		return agentDao.saveAgent(agent);
	}
	
	public  void updateAgent(Agent agent) {
		agentDao.updateAgent(agent);
	}

	public void deleteAgent(Agent agent) {
	    log.info("removing agent " + agent);
		agentDao.deleteAgent(agent);
	}

	public Agent getAgent(Long id) {
		return agentDao.getAgent(id);
	}
}
