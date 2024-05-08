package community.service;

import java.util.Map;

//작성자와 입력 받은 글 제목, 글 내용을 저장하는 WriteRequest
public class WriteRequest {
	
	private String memid;
	private String title;
	private String content;
	
	public WriteRequest(String memid, String title, String content) {
		this.memid = memid;
		this.title = title;
		this.content = content;
	}

	public String getMemId() {
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
		if (content == null || content.trim().isEmpty()) {
	        errors.put("content",Boolean.TRUE);
	    }
	}
}
