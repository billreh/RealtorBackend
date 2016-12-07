package net.tralfamadore.dao;

import java.util.List;

import net.tralfamadore.domain.Agent;

public interface AgentDao {
	public List<Agent> getAgents();
	
	public Agent getAgent(long agentId);
	
	public long saveAgent(Agent agent);
	
	public void updateAgent(Agent agent);
	
	public void deleteAgent(Agent agent);
}
