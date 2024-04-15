package qna.service;

import java.util.Map;

import member.Member;

public class WriteRequest {
	
	private Member memId;
	private String title;
	private String content;
	
	public WriteRequest(Member memId, String title, String content) {
		this.memId = memId;
		this.title = title;
		this.content = content;
	}

	public Member getMemId() {
		return memId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}
}
