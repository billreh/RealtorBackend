package net.tralfamadore.domain;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class TestAddress {
	private Validator validator;
	
	@Before
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@Test
	public void testValidate() {
		Address address = new Address(1L, "534 Queen St", "Philadelphia", "PA", "19147");
		Set<ConstraintViolation<Address>> v = validator.validate(address);
		assertTrue(v.isEmpty());
		address.setZipCode("123");
		v = validator.validate(address);
		assertEquals(v.iterator().next().getMessage(), "size must be between 5 and 5");
		address.setZipCode("12345");
		address.setCity("");
		v = validator.validate(address);
		assertEquals(v.iterator().next().getMessage(), "size must be between 3 and 50");
		address.setCity("Philadelphia");
		address.setState("Philadelphia");
		v = validator.validate(address);
		assertEquals(v.iterator().next().getMessage(), "size must be between 2 and 2");
		address.setState("PA");
		address.setStreet(null);
		v = validator.validate(address);
		assertEquals(v.iterator().next().getMessage(), "may not be null");
	}
}
