package net.tralfamadore.job;

import net.tralfamadore.service.AgentService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class KeepAliveJob {
	private static Logger log = Logger.getLogger(KeepAliveJob.class);
	private AgentService agentService;

	@Inject
	public KeepAliveJob(AgentService agentService) {
		this.agentService = agentService;
	}

	@Scheduled(fixedDelay = 600000)
	public void keepAlive() {
		log.info("keeping it alive");
		agentService.getAgents();
	}

}
