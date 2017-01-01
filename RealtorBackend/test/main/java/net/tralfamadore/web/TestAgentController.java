package net.tralfamadore.web;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.service.AgentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class TestAgentController {
	@Mock
	private AgentService agentService;
	@Mock
	private FacesContext facesContext;
	@Mock
	private ExternalContext externalContext;
	@Mock
	private HttpServletResponse response;
	private AgentController agentController;

	@Before
	public void setup() {
		agentController = new AgentController(agentService);
	}
	
	@After
	public void after() {
		agentController = null;
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testGotoEdit() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		agentController.gotoEdit();
		verify(response).sendRedirect("editAgent.xhtml");
		assertFalse(agentController.isNewAgent());
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testAddAgent() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		agentController.addAgent();
		verify(response).sendRedirect("editAgent.xhtml");
		assertTrue(agentController.isNewAgent());
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testBack() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		
		agentController.back();
		
		verify(response).sendRedirect("agents.xhtml");
	}

	@Test
	@PrepareForTest({FacesContext.class})
	public void testSave() {
		PowerMockito.mockStatic(FacesContext.class);
		Agent agent = new Agent(null, new ArrayList<>(), "Bob", "Parker", "215-222-1212", "bparker@matrix.gs");
		agentController.setAgent(agent);
		
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(agentService.saveAgent(agent)).thenReturn(1L);
		
		agentController.save();
		verify(facesContext).addMessage(argThat(
				new ArgumentMatcher<String>() {
					@Override
					public boolean matches(Object o) {
						return o == null;
					}
				}), argThat(
				new ArgumentMatcher<FacesMessage>() {
					@Override
					public boolean matches(Object o) {
						return ((FacesMessage)o).getDetail().equals("The agent Bob Parker has been saved. (id: 1)");
					}
				}));
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testEdit() {
		PowerMockito.mockStatic(FacesContext.class);
		Agent agent = new Agent(null, new ArrayList<>(), "Bob", "Parker", "215-222-1212", "bparker@matrix.gs");
		agentController.setAgent(agent);
		
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(agentService.saveAgent(agent)).thenReturn(1L);
		
		agentController.edit();
		verify(facesContext).addMessage(argThat(
				new ArgumentMatcher<String>() {
					@Override
					public boolean matches(Object o) {
						return o == null;
					}
				}), argThat(
				new ArgumentMatcher<FacesMessage>() {
					@Override
					public boolean matches(Object o) {
						return ((FacesMessage)o).getDetail().equals("The agent Bob Parker has been updated");
					}
				}));
	}
}
