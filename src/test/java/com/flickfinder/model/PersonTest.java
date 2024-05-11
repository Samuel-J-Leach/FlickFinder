package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {
	private Person person;
	
	@BeforeEach
	public void setUp() {
		person = new Person(1, "Sam Leach", 2005);
	}
	
	@Test
	public void testPersonCreated() {
		assertEquals(1, person.getId());
		assertEquals("Sam Leach", person.getName());
		assertEquals(2005, person.getBirth());
	}
	
	@Test
	public void testPersonSetters() {
		person.setId(2);
		person.setName("Tom Leach");
		person.setBirth(2002);
		assertEquals(2, person.getId());
		assertEquals("Tom Leach", person.getName());
		assertEquals(2002, person.getBirth());
	}
}