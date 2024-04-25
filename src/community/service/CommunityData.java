package community.service;

import java.util.List;

import community.model.Ccontent;
import community.model.Community;
import community.model.ReplyDTO;

public class CommunityData {
	
	private Community community;
	private Ccontent content;
	private List<ReplyDTO> reply;
	
	public CommunityData(Community community, Ccontent content, List<ReplyDTO> reply) {
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
	
	public List<ReplyDTO> getReply() {
		return reply;
	}

	public void setReply(List<ReplyDTO> reply) {
		this.reply = reply;
	}

}
