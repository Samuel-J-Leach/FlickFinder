package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

/**
 * The controller for the movie endpoints.
 * 
 * The controller acts as an intermediary between the HTTP routes and the DAO.
 * 
 * As you can see each method in the controller class is responsible for
 * handling a specific HTTP request.
 * 
 * Methods a Javalin Context object as a parameter and uses it to send a
 * response back to the client.
 * We also handle business logic in the controller, such as validating input and
 * handling errors.
 *
 * Notice that the methods don't return anything. Instead, they use the Javalin
 * Context object to send a response back to the client.
 */

public class MovieController {

	/**
	 * The movie data access object.
	 */

	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initializes the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @param ctx the Javalin context
	 */
	/*public void getAllMovies(Context ctx) {
		try {
			ctx.json(movieDAO.getAllMovies());
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}*/
	public void getAllMovies(Context ctx) {
		int limit;
		try {
			limit = Integer.parseInt(ctx.queryParam("limit"));
		} catch (Exception e) {
			limit = 50;
		}
		try {
			ctx.json(movieDAO.getAllMovies(limit));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movie);
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	public void getPeopleByMovieId(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Person> people = movieDAO.getPeopleByMovieId(id);
			if (people == null) {
				ctx.status(404);
				ctx.result("people not found");
				return;
			}
			ctx.json(people);
		} catch(SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}
	
	public void getRatingsByYear(Context ctx) {
		int year = Integer.parseInt(ctx.pathParam("year"));
		int limit;
		int votes;
		try {
			limit = Integer.parseInt(ctx.queryParam("limit"));
		} catch (Exception e) {
			limit = 50;
		}
		try{
			votes = Integer.parseInt(ctx.queryParam("votes"));
		} catch (Exception e) {
			votes = 1000;
		}
		try {
			List<MovieRating> movieRatings = movieDAO.getRatingsByYear(year, limit, votes);
			if (movieRatings == null) {
				ctx.status(404);
				ctx.result("ratings not found");
				return;
			}
			ctx.json(movieRatings);
		} catch(SQLException e) {
			ctx.status(500);
			ctx.result("database error");
			e.printStackTrace();
		}
	}

}