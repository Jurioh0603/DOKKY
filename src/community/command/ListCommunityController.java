package community.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.service.CommunityPage;
import community.service.ListCommunityService;
import mvc.command.CommandHandler;

public class ListCommunityController implements CommandHandler {

	private ListCommunityService listService = new ListCommunityService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		CommunityPage communityPage = listService.getCommunityPage(pageNo);
		req.setAttribute("communityPage", communityPage);
		return "/view/board/community/communitySelect.jsp";
	}
}
