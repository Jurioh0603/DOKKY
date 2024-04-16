package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.BoardPage;
import admin.service.ListBoardService;
import mvc.command.CommandHandler;

public class ListBoardController implements CommandHandler {
	
	private ListBoardService listBoardService = new ListBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String board = req.getParameter("board");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		BoardPage boardPage = listBoardService.getBoardPage(pageNo, board);
		req.setAttribute("boardPage", boardPage);
		req.setAttribute("board", board);
		return "/view/admin/boardList.jsp";
	}
}
