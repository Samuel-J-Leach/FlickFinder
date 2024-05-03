package com.flickfinder.model;

public class MovieRating extends Movie{
	private float rating;
	private int votes;
	public MovieRating(int id, String title, int year, float rating, int votes) {
		super(id, title, year);
		this.rating = rating;
		this.votes = votes;
	}
	
	public float getRating() {
		return this.rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public int getVotes() {
		return this.votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
