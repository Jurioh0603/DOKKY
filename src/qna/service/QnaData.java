package qna.service;

import java.util.List;

import qna.model.Qcontent;
import qna.model.Qna;
import qna.model.QreplyDTO;

public class QnaData {
	
	private Qna qna;
	private Qcontent content;
	private List<QreplyDTO> reply;
	
	public QnaData(Qna qna, Qcontent content, List<QreplyDTO> reply) {
		this.qna = qna;
		this.content = content;
		this.reply = reply;
	}
	


	public List<QreplyDTO> getReply() {
		return reply;
	}


	public void setReply(List<QreplyDTO> reply) {
		this.reply = reply;
	}


	public Qna getQna() {
		return qna;
	}
	public String getContent() {
		return content.getContent();
	}
}
