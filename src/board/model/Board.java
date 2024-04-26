package board.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Board {
	
	private int bno;
	private String title;
	private Date regdate;
	private int hit;
	private String memid;
	
	public Board(int bno, String title, Date regdate, int hit, String memid) {
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
	
    public String getFormattedRegDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(regdate);
	}
	
}
