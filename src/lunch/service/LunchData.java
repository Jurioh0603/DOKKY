package lunch.service;

import java.util.List;

import lunch.model.Lcontent;
import lunch.model.Lunch;
import lunch.model.ReplyDTO;

public class LunchData {
	
	private Lunch lunch;
	private Lcontent content;
	private List<ReplyDTO> reply;
	
	public LunchData(Lunch lunch, Lcontent content, List<ReplyDTO> reply) {
		this.lunch = lunch;
		this.content = content;
		this.setReply(reply);
	}
	
	public Lunch getLunch() {
		return lunch;
	}
	
	public String getContent() {
		return content.getContent();
	}
	
	public String getFilerealname() {
        return content.getFilerealname();
    }

	public List<ReplyDTO> getReply() {
		return reply;
	}

	public void setReply(List<ReplyDTO> reply) {
		this.reply = reply;
	}

}