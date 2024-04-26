package study.service;

import java.util.List;

import study.model.Sreply;
import study.model.Scontent;
import study.model.Study;

public class StudyData {
	
	private Study study;
	private Scontent content;
	private List<Sreply> reply;
	
	public StudyData(Study study, Scontent content, List<Sreply> reply) {
		this.study = study;
		this.content = content;
		this.setReply(reply);
	}
	
	public Study getStudy() {
		return study;
	}
	
	public String getContent() {
		return content.getContent();
	}
	
	public List<Sreply> getReply() {
		return reply;
	}

	public void setReply(List<Sreply> reply) {
		this.reply = reply;
	}

}
