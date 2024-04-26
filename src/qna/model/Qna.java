package qna.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import member.model.Member;

public class Qna {
	
	private int bno;
	private String memId;
	private String title;
	private Date regDate;
	private int hit;
	
	public Qna(int bno, String memid, String title, Date regDate, int hit) {
		this.bno = bno;
		this.memId = memid;
		this.title = title;
		this.regDate = regDate;
		this.hit = hit;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	//시간까지 포함된 문자열로 변환하여 반환하는 메서드
	public String getFormattedRegDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(regDate);
	}
	
	//글목록 시간 메서드
   public String getFormattedRegDateSel() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd / HH시 mm분");
	    return dateFormat.format(regDate);
	}
}
