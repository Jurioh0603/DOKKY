package member;

import java.util.Date;

public class Member {
	
	private String memId;
	private String memPw;
	private String name;
	private String email;
	private int grade;
	private Date regDate;
	
	
	public Member(String memId, String memPw, String name, String email, int grade, Date regDate) {
		this.memId = memId;
		this.memPw = memPw;
		this.name = name;
		this.email = email;
		this.grade = grade;
		this.regDate = regDate;
	}
	

	public String getMemId() {
		return memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getGrade() {
		return grade;
	}
	public Date getRegDate() {
		return regDate;
	}
	
	public boolean matchPassword (String pw) {
		return memPw.equals(pw);
	}
	
}
