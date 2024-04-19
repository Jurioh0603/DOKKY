package lunch.model;

import java.sql.Date;

public class Lunch {
	
	private int bno;
	private String title;
	private Date regdate;
	private int hit;
	private String memid;
	
	public Lunch(int bno, String title, Date regdate, int hit, String memid) {
		this.bno = bno;
		this.title = title;
		this.regdate = regdate;
		this.hit = hit;
		this.memid = memid;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}
	
}