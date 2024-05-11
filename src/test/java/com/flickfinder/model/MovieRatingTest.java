package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieRatingTest {
	private MovieRating movieRating;
	
	@BeforeEach
	public void setUp() {
		movieRating = new MovieRating(1, "The Matrix", 1999, (float) 8.7, 1895415);
	}
	
	@Test
	public void testMovieRatingCreated() {
		assertEquals(1, movieRating.getId());
		assertEquals("The Matrix", movieRating.getTitle());
		assertEquals(1999, movieRating.getYear());
		assertEquals((float) 8.7, movieRating.getRating());
		assertEquals(1895415, movieRating.getVotes());
	}
	
	@Test
	public void testMovieRatingSetters() {
		movieRating.setId(2);
		movieRating.setTitle("The Matrix Reloaded");
		movieRating.setYear(2003);
		movieRating.setRating((float) 7.9);
		movieRating.setVotes(1387890);
		assertEquals(2, movieRating.getId());
		assertEquals("The Matrix Reloaded", movieRating.getTitle());
		assertEquals(2003, movieRating.getYear());
		assertEquals((float) 7.9, movieRating.getRating());
		assertEquals(1387890, movieRating.getVotes());
	}
}
