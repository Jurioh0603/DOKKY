package lunch.model;

import java.sql.Date;

public class LunchRequest {
	
	private int bno;
	private String memId;
	private String title;
	private Date regDate;
	private int hit;
	private String fileName;
	private String fileRealName;
	
	public LunchRequest(int bno, String memId, String title, Date regDate, int hit, String fileName,
			String fileRealName) {
		super();
		this.bno = bno;
		this.memId = memId;
		this.title = title;
		this.regDate = regDate;
		this.hit = hit;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
}