package net.tralfamadore.dao;

import java.util.List;

import net.tralfamadore.domain.Agent;

public interface AgentDao {
	List<Agent> getAgents();
	
	Agent getAgent(long agentId);
	
	long saveAgent(Agent agent);
	
	void updateAgent(Agent agent);

	void deleteAgent(Agent agent);
}
