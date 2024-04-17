package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.BoardListService;
import admin.service.BoardPage;
import mvc.command.CommandHandler;

public class BoardListController implements CommandHandler {
	
	private BoardListService boardListService = new BoardListService();
	String board = null;
	String pageNoVal = null;
	int pageNo = 1;
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processList(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processDelete(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processList(HttpServletRequest req, HttpServletResponse res) {
		getInfo(req, res);
		
		BoardPage boardPage = boardListService.getBoardPage(pageNo, board);
		
		req.setAttribute("boardPage", boardPage);
		req.setAttribute("board", board);
		return "/view/admin/boardList.jsp";
	}
	
	private String processDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String deleteList = req.getParameter("deleteList");
		getInfo(req, res);
		
		boardListService.deleteBoard(board, deleteList);
		
		res.sendRedirect("/admin/boardList.do?board=" + board + "&pageNo=" + pageNo);
		return null;
	}
	
	private void getInfo(HttpServletRequest req, HttpServletResponse res) {
		board = req.getParameter("board");
		pageNoVal = req.getParameter("pageNo");
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
	}
	
}
