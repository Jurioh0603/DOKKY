package lunch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lunch.service.LunchPage;
import lunch.service.ListLunchService;
import mvc.command.CommandHandler;

public class ListLunchController implements CommandHandler {

	private ListLunchService listService = new ListLunchService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		LunchPage lunchPage = listService.getLunchPage(pageNo);
		req.setAttribute("lunchPage", lunchPage);
		return "/view/board/lunch/lunchSelect.jsp";
	}
}
