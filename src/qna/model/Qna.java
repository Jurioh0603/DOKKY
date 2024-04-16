package qna.model;
import java.util.Date;
import member.model.Member;

public class Qna {
	
	private int bno;
	private String memId;
	private String title;
	private Date regDate;
	private int hit;
	
	//Qna��ü ���� �� ������ �ڸ��� ���� �Ű�������(������)
	public Qna(int bno, String memid, String title, Date regDate, int hit) {
		this.bno = bno;
		this.memId = memid;
		this.title = title;
		this.regDate = regDate;
		this.hit = hit;
	}

	//�Һ��� ������ ���� getter �޼��常 ����. ��ü�� ���°� ������� �����Ƿ� �ٸ� �κп��� ��ü�� ���� ���� ������ ���� �Һ� ��ü.
	public int getBno() {
		return bno;
	}

	public String getMemId() {
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
