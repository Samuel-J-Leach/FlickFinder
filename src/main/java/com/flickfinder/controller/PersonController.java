package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class PersonController {

	// to complete the must-have requirements you need to add the following methods:
	// getAllPeople
	// getPersonById
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.
	
	private final PersonDAO personDAO;
	
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	/*public void getAllPeople(Context ctx) {
		try {
			ctx.json(personDAO.getAllPeople());
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}*/
	
	public void getAllPeople(Context ctx) {
		int limit;
		try {
			limit = Integer.parseInt(ctx.queryParam("limit"));
		} catch (Exception e) {
			limit = 50;
		}
		try {
			ctx.json(personDAO.getAllPeople(limit));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}
	
	public void getPersonById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("person not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}
	
	public void getMoviesStarringPerson(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(id);
			if (movies == null) {
				ctx.status(404);
				ctx.result("movies not found");
				return;
			}
			ctx.json(movies);
		} catch(SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}

}