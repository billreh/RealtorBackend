package net.tralfamadore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.tralfamadore.domain.Agent;

@Repository
public class AgentDaoImpl implements AgentDao {
	private static Logger log = Logger.getLogger(AgentDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Agent> getAgents() {
		List<Agent> agents = em.createQuery("from Agent", Agent.class).getResultList();
		return agents;
	}

	@Override
	public Agent getAgent(long agentId) {
		return em.createQuery("from Agent where id =:id", Agent.class).setParameter("id", agentId).getSingleResult();
	}

	@Override
	@Transactional
	public long saveAgent(Agent agent) {
		em.persist(agent);
		return agent.getId();
	}

	@Override
	@Transactional
	public void updateAgent(Agent agent) {
		Agent a = em.find(Agent.class, agent.getId());
		a.setFirstName(agent.getFirstName());
		a.setLastName(agent.getLastName());
		a.setContactNumber(agent.getContactNumber());
		a.setEmail(agent.getEmail());
		em.merge(a);
	}

	@Override
	@Transactional
	public void deleteAgent(Agent agent) {
		em.remove(em.contains(agent) ? agent : em.merge(agent));
	}

}
