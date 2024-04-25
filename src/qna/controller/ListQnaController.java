package qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.ListQnaService;
import qna.service.QnaPage;

public class ListQnaController implements CommandHandler {
	
	private ListQnaService listService = new ListQnaService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String search = req.getParameter("search");
		String pageNoVal = req.getParameter("pageNo");
		String sort = req.getParameter("sort");
		
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		if(sort == null) {
			sort = "bno";
		}
		
		QnaPage qnaPage = null;
		if(search == null)
			qnaPage = listService.getQnaPage(pageNo, sort);
		else
			qnaPage = listService.getSearchQnaPage(pageNo, search, sort);
		
		req.setAttribute("qnaPage", qnaPage);
		req.setAttribute("search", search);
		req.setAttribute("sort", sort);
		return "/view/board/qna/qnaSelect.jsp";
	}
}
