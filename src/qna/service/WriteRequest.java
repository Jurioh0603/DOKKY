package qna.service;

import java.util.Map;

import member.model.Member;

public class WriteRequest {
	
	private Member memid;
	private String title;
	private String content;
	
	public WriteRequest(Member memid, String title, String content) {
		this.memid = memid;
		this.title = title;
		this.content = content;
	}

	public Member getMemId() {
		return memid;
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
