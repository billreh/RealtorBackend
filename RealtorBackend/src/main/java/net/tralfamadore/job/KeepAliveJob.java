package net.tralfamadore.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.tralfamadore.service.AgentService;

public class KeepAliveJob {
	private static Logger log = Logger.getLogger(KeepAliveJob.class);
	@Autowired
	private AgentService agentService;
	
	public void keepAlive() {
		log.info("keeping it alive");
		agentService.getAgents();
	}

}