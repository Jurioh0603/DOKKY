package qna.service;

import java.util.List;

import qna.model.Qcontent;
import qna.model.Qna;
import qna.model.Qreply;

public class QnaData {
	
	private Qna qna;
	private Qcontent content;
	private List<Qreply> reply;
	
	public QnaData(Qna qna, Qcontent content, List<Qreply> reply) {
		this.qna = qna;
		this.content = content;
		this.reply = reply;
	}
	


	public List<Qreply> getReply() {
		return reply;
	}


	public void setReply(List<Qreply> reply) {
		this.reply = reply;
	}


	public Qna getQna() {
		return qna;
	}
	public String getContent() {
		return content.getContent();
	}
}
