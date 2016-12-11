package net.tralfamadore.web;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Configuration;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;

import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.tralfamadore.domain.Agent;
import net.tralfamadore.service.AgentService;

@RunWith(PowerMockRunner.class)
public class TestAgentController {
	@Mock
	private AgentService agentService;
	@Mock
	private Validator validator;
	@Mock
	private FacesContext facesContext;
	@Mock
	private ExternalContext externalContext;
	@Mock
	private HttpServletResponse response;
	private AgentController agentController;
	
	@Before
	public void setup() {
		agentController = new AgentController(agentService, validator);
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
	}
	
	@Test
	@PrepareForTest({FacesContext.class})
	public void testAddAgent() throws Exception {
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResponse()).thenReturn(response);
		agentController.addAgent();
		verify(response).sendRedirect("addAgent.xhtml");
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
	public void testInvalidSave() {
		/*
		ConstraintValidatorFactory constraintValidatorFactory = new ConstraintValidatorFactory() {

			@Override
			public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				return (T) new SizeValidatorForCharSequence();
			}

			@Override
			public void releaseInstance(ConstraintValidator<?, ?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		Configuration<?> config = Validation.byDefaultProvider().configure();
	    config.constraintValidatorFactory(constraintValidatorFactory);

	    ValidatorFactory factory = config.buildValidatorFactory();

	    validator = factory.getValidator();
	    */
		PowerMockito.mockStatic(FacesContext.class);
		Agent agent = new Agent(null, new ArrayList<>(), "", "Parker", "215-222-1212", "bparker@matrix.gs");
		agentController.setAgent(agent);
		Set<ConstraintViolation<Agent>> violations = new HashSet<>();
		@SuppressWarnings("unchecked")
		ConstraintViolation<Agent> violation = mock(ConstraintViolation.class);
		
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(agentService.saveAgent(agent)).thenReturn(1L);
		when(violation.getMessage()).thenReturn("firstName size must be between 1 and 100");
		violations.add(violation);
		when(validator.validate(agent)).thenReturn(violations);
		
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
						return ((FacesMessage)o).getDetail().equals("null firstName size must be between 1 and 100");
					}
				}));
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
