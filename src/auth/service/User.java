package auth.service;

import java.util.Date;

public class User {

	private String id;
	private String name;
	private String email;
	private int grade;
	
	public User(String id, String name, String email, int grade) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.grade = grade;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public String getEmail() {
		return email;
	}
	
}
