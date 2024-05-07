package community.service;

import java.util.List;

import community.model.Ccontent;
import community.model.Community;
import community.model.Creply;

//게시글 정보, 내용, 댓글의 내용을 한번에 저장하기 위한 CommunityData
public class CommunityData {
	
	private Community community;
	private Ccontent content;
	private List<Creply> reply;
	
	public CommunityData(Community community, Ccontent content, List<Creply> reply) {
		this.community = community;
		this.content = content;
		this.setReply(reply);
	}
	
	public Community getCommunity() {
		return community;
	}
	
	public String getContent() {
		return content.getContent();
	}
	
	public List<Creply> getReply() {
		return reply;
	}

	public void setReply(List<Creply> reply) {
		this.reply = reply;
	}

}
