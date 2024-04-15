package member.model;

import java.util.Date;

public class Member {

	private String memid;
	private String mempw;
	private String name;
	private String email;
	private int grade;
	private Date regdate;
	
	public Member(String memid, String mempw, String name, String email, int grade, Date regdate) {
		this.memid = memid;
		this.mempw = mempw;
		this.name = name;
		this.email = email;
		this.grade = grade;
		this.regdate = regdate;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getMempw() {
		return mempw;
	}

	public void setMempw(String mempw) {
		this.mempw = mempw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
