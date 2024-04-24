package lunch.service;

import java.util.Map;

public class ModifyRequest {
	
	private String memId;
	private int lunchNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String memId, int lunchNumber, String title, String content) {
	this.memId = memId;
	this.lunchNumber = lunchNumber;
	this.title = title;
	this.content = content;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getLunchNumber() {
		return lunchNumber;
	}

	public void setLunchNumber(int lunchNumber) {
		this.lunchNumber = lunchNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
