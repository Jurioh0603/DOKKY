package model;

public class Writer {
	
	private String memId;
	private String name;
	
	public Writer(String memId, String name) {
		this.memId = memId;
		this.name = name;
	}
	
	public String getMemId () {
		return memId;
	}
	
	public String getName () {
		return name;
	}
}
