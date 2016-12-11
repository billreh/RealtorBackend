package net.tralfamadore.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class TestAgent {
	private Validator validator;
	
	@Before
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@Test
	public void testValidate() {
		Agent agent = new Agent(null, new ArrayList<>(), "Bob", "Parker", "215-222-1212", "bparker@matrix.gs");
		Set<ConstraintViolation<Agent>> v = validator.validate(agent);
		assertTrue(v.isEmpty());
		agent.setFirstName("");
		v = validator.validate(agent);
		assertEquals(v.iterator().next().getMessage(), "size must be between 1 and 100");
		agent.setFirstName("Bob");
		agent.setLastName("");
		v = validator.validate(agent);
		assertEquals(v.iterator().next().getMessage(), "size must be between 1 and 100");
		agent.setLastName("Parker");
		agent.setContactNumber("");
		v = validator.validate(agent);
		assertEquals(v.iterator().next().getMessage(), "size must be between 1 and 100");
		agent.setContactNumber("215-222-1212");
		agent.setEmail("bob");
		v = validator.validate(agent);
		assertEquals(v.iterator().next().getMessage(), "not a well-formed email address");
	}
}
