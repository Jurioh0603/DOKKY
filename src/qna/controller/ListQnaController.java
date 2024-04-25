package qna.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.ListQnaService;
import qna.service.QnaPage;

public class ListQnaController implements CommandHandler {
	
	private ListQnaService listQnaService = new ListQnaService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		QnaPage qnaPage = listQnaService.getQnaPage(pageNo);
		req.setAttribute("qnapage", qnaPage);
		return "/view/board/qna/qnaSelect.jsp";
	}
}
