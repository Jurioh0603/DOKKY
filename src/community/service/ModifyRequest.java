package community.service;

import java.util.Map;

public class ModifyRequest {
	
	private String memId;
	private int communityNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String memId, int communityNumber, String title, String content) {
	this.memId = memId;
	this.communityNumber = communityNumber;
	this.title = title;
	this.content = content;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(int communityNumber) {
		this.communityNumber = communityNumber;
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
