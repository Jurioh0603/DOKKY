package member.service;

import java.util.Map;

public class JoinRequest {

	private String memid;
	private String mempw;
	private String verifyMemPw;
	private String name;
	private String email;
	
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getMempw() {
		return mempw;
	}
	public void setMempw(String mempw) {
		this.mempw = mempw;
	}
	public String getVerifyMemPw() {
		return verifyMemPw;
	}
	public void setVerifyMemPw(String verifyMemPw) {
		this.verifyMemPw = verifyMemPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean equalPassword() {
		return mempw != null && mempw.equals(verifyMemPw);
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, memid, "id");
		checkEmpty(errors, mempw, "password");
		checkEmpty(errors, verifyMemPw, "verifyPassword");
		if(!errors.containsKey("verifyPassword")) {
			if(!equalPassword()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
		checkEmpty(errors, name, "name");
		checkEmptyEmail(errors, email, "email");
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) 
			errors.put(fieldName, Boolean.TRUE);
	}
	
	private void checkEmptyEmail(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
