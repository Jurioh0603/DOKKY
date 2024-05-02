package lunch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.service.CommunityPage;
import lunch.service.LunchPage;
import lunch.service.ListLunchService;
import mvc.command.CommandHandler;

public class ListLunchController implements CommandHandler {

	private ListLunchService listService = new ListLunchService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String search = req.getParameter("search");
		String pageNoVal = req.getParameter("pageNo");
		String sort = req.getParameter("sort");
		
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		if(sort == null) {
			sort = "bno";
		}
		
		LunchPage lunchPage = null;
		if(search == null)
			lunchPage = listService.getLunchPage(pageNo);
		else
			lunchPage = listService.getSearchLunchPage(pageNo, search, sort);
		
		req.setAttribute("lunchPage", lunchPage);
		req.setAttribute("search", search);
		req.setAttribute("sort", sort);
		return "/view/board/lunch/lunchSelect.jsp";
	}
}
