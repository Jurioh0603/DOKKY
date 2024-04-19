package lunch.model;

import java.sql.Date;

public class LunchRequest {
	int bno;
	String title;
	Date regdate;
	int hit;
	String memid;
	String filerealname;
	String filename;
	
	
	


	public LunchRequest(int bno, String title, Date regdate, int hit, String memid, String filerealname,
			String filename) {
		super();
		this.bno = bno;
		this.title = title;
		this.regdate = regdate;
		this.hit = hit;
		this.memid = memid;
		this.filerealname = filerealname;
		this.filename = filename;
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





	public String getFilerealname() {
		return filerealname;
	}





	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
	}





	public String getFilename() {
		return filename;
	}





	public void setFilename(String filename) {
		this.filename = filename;
	}


	
}