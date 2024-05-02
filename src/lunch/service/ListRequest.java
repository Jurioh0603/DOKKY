package lunch.service;

import java.util.Date;

public class ListRequest {

	private int bno;
	private String title;
	private Date regdate;
	private int hit;
	private String memid;
	private String filerealname;
	private int replyCount;
	
	public ListRequest(int bno, String title, Date regdate, int hit, String memid, String filerealname, int replyCount) {
		this.bno = bno;
		this.title = title;
		this.regdate = regdate;
		this.hit = hit;
		this.memid = memid;
		this.filerealname = filerealname;
	    this.replyCount = replyCount;
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

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
}
