package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.MemberListService;
import admin.service.MemberPage;
import mvc.command.CommandHandler;

public class MemberListController implements CommandHandler {
	
	private static final String LIST_VIEW = "/admin/memberList.do";
	private MemberListService memberListService = new MemberListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processList(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processUpdate(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processList(HttpServletRequest req, HttpServletResponse res) {
		String searchId = req.getParameter("searchId");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		if(searchId == null) {
			MemberPage memberPage = memberListService.getMemberPage(pageNo);
			req.setAttribute("memberPage", memberPage);
			return "/view/admin/memberList.jsp";
		} else {
			MemberPage memberPage = memberListService.searchMemberPage(pageNo, searchId);
			req.setAttribute("searchId", searchId);
			req.setAttribute("memberPage", memberPage);
			return "/view/admin/memberList.jsp";
		}
	}
	
	private String processUpdate(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String currentPage = req.getParameter("currentPage");
		String id = trim(req.getParameter("id"));
		int grade = Integer.parseInt(req.getParameter("grade"));
		
		try {
			memberListService.updateGrade(id, grade);
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
