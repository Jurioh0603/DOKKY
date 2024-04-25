package qna.service;

import java.util.List;

import qna.model.Qcontent;
import qna.model.Qna;
import reply.model.ReplyDTO;

public class QnaData {
	
	private Qna qna;
	private Qcontent content;
	private List<ReplyDTO> reply;
	
	public QnaData(Qna qna, Qcontent content, List<ReplyDTO> reply) {
		this.qna = qna;
		this.content = content;
		this.reply = reply;
	}
	


	public List<ReplyDTO> getReply() {
		return reply;
	}


	public void setReply(List<ReplyDTO> reply) {
		this.reply = reply;
	}


	public Qna getQna() {
		return qna;
	}
	public String getContent() {
		return content.getContent();
	}
}
