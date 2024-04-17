package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.MemberListService;
import admin.service.MemberPage;
import mvc.command.CommandHandler;

public class SearchMemberController implements CommandHandler {
	
	private MemberListService memberListService = new MemberListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String searchId = req.getParameter("searchId");
		MemberPage memberPage = memberListService.memberSearchPage(searchId);
		req.setAttribute("memberPage", memberPage);
		return "/view/admin/memberList.jsp";
	}
}
