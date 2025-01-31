package com.flickfinder.model;

/**
 * A person in the movie database.
 * 
 * @TODO: Implement this class
 */
public class Person {

	// - Add your code here: use the MovieDAO.java as an example
	// - Check the ERD and database schema in the docs folder
	// (./docs/database_schema.md) to ensure each column in the People table
	// has an attribute in the model. (DELETE THIS COMMENT WHEN DONE)
	
	private int id;
	private String name;
	private int birth;
	
	public Person(int id, String name, int birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBirth() {
		return this.birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}

}
