package community.service;

import community.model.Ccontent;
import community.model.Community;

public class CommunityData {
	
	private Community community;
	private Ccontent content;
	
	public CommunityData(Community community, Ccontent content) {
		this.community = community;
		this.content = content;
	}
	
	public Community getCommunity() {
		return community;
	}
	
	public String getContent() {
		return content.getContent();
	}

}
