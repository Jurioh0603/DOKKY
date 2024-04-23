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
		String search = req.getParameter("search");
		String sort = req.getParameter("sort");
		
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		if(sort == null) {
			sort = "bno";
		}
		
		CommunityPage communityPage = null;
		if(search == null)
			communityPage = listService.getCommunityPage(pageNo, sort);
		else
			communityPage = listService.getSearchCommunityPage(pageNo, search, sort);
		
		req.setAttribute("communityPage", communityPage);
		req.setAttribute("search", search);
		req.setAttribute("sort", sort);
		return "/view/board/community/communitySelect.jsp";
	}
}
