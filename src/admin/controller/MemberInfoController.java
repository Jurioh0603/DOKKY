package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.MemberInfoService;
import mvc.command.CommandHandler;

public class MemberInfoController implements CommandHandler {
	
	private static final String LIST_VIEW = "/admin/memberList.do";
	private MemberInfoService memberInfoService = new MemberInfoService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String currentPage = req.getParameter("currentPage");
		String id = trim(req.getParameter("id"));
		int grade = Integer.parseInt(req.getParameter("grade"));
		
		try {
			memberInfoService.updateGrade(id, grade);
			res.sendRedirect(LIST_VIEW + "?pageNo=" + currentPage);
			return null;
		} catch(Exception e) {
			return LIST_VIEW;
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
