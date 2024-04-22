package lunch.service;

import lunch.model.Lcontent;
import lunch.model.Lunch;

public class LunchData {
	
	private Lunch lunch;
	private Lcontent content;
	
	public LunchData(Lunch lunch, Lcontent content) {
		this.lunch = lunch;
		this.content = content;
	}
	
	public Lunch getLunch() {
		return lunch;
	}
	
	public String getContent() {
		return content.getContent();
	}

}
