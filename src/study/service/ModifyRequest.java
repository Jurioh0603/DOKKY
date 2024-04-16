package study.service;

import java.util.Map;

public class ModifyRequest {
	
	private String memId;
	private int studyNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String memId, int studyNumber, String title, String content) {
	this.memId = memId;
	this.studyNumber = studyNumber;
	this.title = title;
	this.content = content;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getStudyNumber() {
		return studyNumber;
	}

	public void setStudyNumber(int studyNumber) {
		this.studyNumber = studyNumber;
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
