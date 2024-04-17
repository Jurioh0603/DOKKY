package study.service;

import study.model.Scontent;
import study.model.Study;

public class StudyData {
	
	private Study study;
	private Scontent content;
	
	public StudyData(Study study, Scontent content) {
		this.study = study;
		this.content = content;
	}
	
	public Study getStudy() {
		return study;
	}
	
	public String getContent() {
		return content.getContent();
	}

}
