package member.board;

import java.util.Date;

public class Board {
	
	private int bno;
	private String title;
	private Date regdate;
	private int hit;
	
	public Board(int bno, String title, Date regdate, int hit) {
		this.bno = bno;
		this.title = title;
		this.regdate = regdate;
		this.hit = hit;
	}
	
	public int getBno() {
		return bno;
	}
	public String getTitle() {
		return title;
	}
	public Date getRegdate() {
		return regdate;
	}
	public int getHit() {
		return hit;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
