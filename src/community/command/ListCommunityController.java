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
		String search = req.getParameter("search");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		CommunityPage communityPage = null;
		if(search == null)
			communityPage = listService.getCommunityPage(pageNo);
		else
			communityPage = listService.getSearchCommunityPage(pageNo, search);
		req.setAttribute("communityPage", communityPage);
		req.setAttribute("search", search);
		return "/view/board/community/communitySelect.jsp";
	}
}
