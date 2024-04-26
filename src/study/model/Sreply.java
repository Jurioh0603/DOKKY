package study.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sreply {
	private int rno;
	private int bno;
	private String memid;
	private String rcontent;
	private Date date;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	//시간까지 포함된 문자열로 변환하여 반환하는 메서드
   public String getFormattedRegDate() {
	   System.out.println(date);
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return dateFormat.format(date);
   }
	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", bno=" + bno + ", memid=" + memid + ", rcontent=" + rcontent + ", date=" + date + "]";
	}
	
}
