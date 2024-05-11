package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

class PersonDAOTest {
	private PersonDAO personDAO;
	Seeder seeder;
	
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		personDAO = new PersonDAO();
	}
	
	@Test
	void testGetAllPeople() {
		try {
			List<Person> people = personDAO.getAllPeople(50);
			assertEquals(5, people.size());
			people = personDAO.getAllPeople(3);
			assertEquals(3, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testInvalidGetAllPeople() {
		try {
			List<Person> people = personDAO.getAllPeople(-5);
			assertEquals(0, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetPersonById() {
		Person person;
		try {
			person = personDAO.getPersonById(2);
			assertEquals("Morgan Freeman", person.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetPersonByInvalidId() {
		try {
			Person person = personDAO.getPersonById(1000);
			assertEquals(null, person);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetMoviesStarringPerson() {
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(1);
			assertEquals(1, movies.size());
		} catch(SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testInvalidGetMoviesStarringPerson() {
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(999);
			assertEquals(0, movies.size());
		} catch(SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}
}