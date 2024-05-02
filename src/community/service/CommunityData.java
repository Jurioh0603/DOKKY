package community.service;

import java.util.List;

import community.model.Ccontent;
import community.model.Community;
import community.model.Creply;

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
