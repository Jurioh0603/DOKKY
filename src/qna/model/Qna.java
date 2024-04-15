package qna.model;
import java.util.Date;
import member.model.Member;

public class Qna {
	
	private int bno;
	private Member memId;
	private String title;
	private Date regDate;
	private int hit;
	
	//Qna객체 생성 시 각각의 자리에 들어가는 매개변수들(생성자)
	public Qna(int bno, Member memid, String title, Date regDate, int hit) {
		this.bno = bno;
		this.memId = memid;
		this.title = title;
		this.regDate = regDate;
		this.hit = hit;
	}

	//불변성 유지를 위해 getter 메서드만 생성. 객체의 상태가 변경되지 않으므로 다른 부분에서 객체에 대한 조작 방지를 위해 불변 객체.
	public int getBno() {
		return bno;
	}

	public Member getMemId() {
		return memId;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public int getHit() {
		return hit;
	}
	
}
