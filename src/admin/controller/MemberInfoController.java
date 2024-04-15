package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.MemberInfoService;
import mvc.command.CommandHandler;

public class MemberInfoController implements CommandHandler {
	
	private static final String FORM_VIEW = "memberList.do";
	private MemberInfoService memberInfoService = new MemberInfoService();
	
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
		String currentPage = req.getParameter("currentPage");
		String id = trim(req.getParameter("id"));
		int grade = Integer.parseInt(req.getParameter("grade"));
		
		try {
			memberInfoService.updateGrade(id, grade);
			res.sendRedirect("memberList.do?pageNo="+currentPage);
			return null;
		} catch(Exception e) {
			return FORM_VIEW;
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
