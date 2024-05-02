
package lunch.service;

import java.util.List;

import lunch.model.Lcontent;
import lunch.model.Lunch;
import lunch.model.Lreply;

public class LunchData {
	
	private Lunch lunch;
	private Lcontent content;
	private List<Lreply> reply;
	
	public LunchData(Lunch lunch, Lcontent content, List<Lreply> reply) {
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

	public List<Lreply> getReply() {
		return reply;
	}

	public void setReply(List<Lreply> reply) {
		this.reply = reply;
	}

}
