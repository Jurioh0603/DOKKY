package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.ListMemberService;
import admin.service.MemberPage;
import mvc.command.CommandHandler;

public class ListMemberController implements CommandHandler {
	
	private ListMemberService listMemberService = new ListMemberService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		MemberPage memberPage = listMemberService.getMemberPage(pageNo);
		req.setAttribute("memberPage", memberPage);
		return "/view/admin/memberList.jsp";
	}
}
