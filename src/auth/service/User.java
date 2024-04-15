package auth.service;

import java.util.Date;

public class User {

	private String id;
	private String name;
	private int grade;
	private Date regDate;
	
	public User(String id, String name, int grade, Date regDate) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.regDate = regDate;
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
	
	public Date getRegDate() {
		return regDate;
	}
}
