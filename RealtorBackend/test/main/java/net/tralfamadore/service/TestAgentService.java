package net.tralfamadore.service;

import net.tralfamadore.config.AppConfig;
import net.tralfamadore.domain.Agent;
import net.tralfamadore.ListingProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Class: TestAgentService
 * Created by billreh on 1/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
@WebAppConfiguration
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class TestAgentService {
    @Inject
    private AgentService agentService;

    @Test
    public void testAgentService() throws Exception {
        Agent agent = ListingProvider.getListing().getAgent();
        agent.setListings(new ArrayList<>());
        agent.setId(null);
        Long id = agentService.saveAgent(agent);
        agent.setId(id);
        Agent retrievedAgent = agentService.getAgent(id);
        assertEquals(agent, retrievedAgent);
        assertTrue(agentService.getAgents().contains(retrievedAgent));
        retrievedAgent.setFirstName("Test2");
        agentService.updateAgent(retrievedAgent);
        assertEquals("Test2", agentService.getAgent(id).getFirstName());
        agentService.deleteAgent(retrievedAgent);
        assertNull(agentService.getAgent(id));
    }
}
