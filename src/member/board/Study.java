package member.board;

import java.util.Date;


public class Study {
	
	private int bno;
	private Writer writer;
	private String title;
	private Date regdate;
	private int hit;
	
	public Study(int bno, Writer writer, String title, Date regdate, int hit) {
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.regdate = regdate;
		this.hit = hit;
	}
	
	public int getBno() {
		return bno;
	}
	public Writer getWriter() {
		return writer;
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

}
