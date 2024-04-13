package auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.FindIdFailException;
import auth.service.FindIdService;
import auth.service.FindPwdService;
import mvc.command.CommandHandler;

public class FindPwdController implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/account/findPwd.jsp";
	private FindPwdService findPwdService = new FindPwdService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String name = trim(req.getParameter("name"));
		String id = trim(req.getParameter("id"));
		String email = trim(req.getParameter("email"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(name == null || name.isEmpty())
			errors.put("name", Boolean.TRUE);
		if(id == null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if(email == null || email.isEmpty())
			errors.put("email", Boolean.TRUE);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			findPwdService.findMember(name, id, email);
			req.setAttribute("isExist", Boolean.TRUE);
			return FORM_VIEW;
		} catch(FindIdFailException e) {
			errors.put("cantFind", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
