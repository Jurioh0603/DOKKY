package lunch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lunch.service.ListLunchService;
import lunch.service.LunchPage;
import mvc.command.CommandHandler;

public class ListLunchController implements CommandHandler {
	
	private ListLunchService listLunchService = new ListLunchService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		LunchPage lunchPage = listLunchService.getLunchPage(pageNo);
		req.setAttribute("lunchPage", lunchPage);
		return "/view/board/lunch/lunchSelect.jsp";
	}
}