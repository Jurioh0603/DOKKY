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
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processList(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processUpdate(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	//get 방식 요청 -> 회원 목록
	private String processList(HttpServletRequest req, HttpServletResponse res) {
		String searchId = req.getParameter("searchId");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		if (searchId == null || searchId == "") {
			//전체 회원 목록 조회
			MemberPage memberPage = memberListService.getMemberPage(pageNo);
			req.setAttribute("memberPage", memberPage);
			return "/view/admin/memberList.jsp";
		} else {
			//검색한 회원 정보 조회
			MemberPage memberPage = memberListService.searchMemberPage(pageNo, searchId);
			req.setAttribute("searchId", searchId);
			req.setAttribute("memberPage", memberPage);
			return "/view/admin/memberList.jsp";
		}
	}

	//post 방식 요청 -> 회원 등급 수정
	private String processUpdate(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String currentPage = req.getParameter("currentPage");
		String searchId = req.getParameter("searchId");
		String id = trim(req.getParameter("id"));
		int grade = Integer.parseInt(req.getParameter("grade"));

		try {
			memberListService.updateGrade(id, grade);
			res.sendRedirect(LIST_VIEW + "?pageNo=" + currentPage + "&searchId=" + searchId);
			return null;
		} catch (Exception e) {
			return LIST_VIEW;
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
