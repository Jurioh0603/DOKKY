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
	String searchId = null;
	String searchItem = null;
	
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
	
	//get 방식 요청 -> 게시글 목록
	private String processList(HttpServletRequest req, HttpServletResponse res) {
		getInfo(req, res);
		if(searchId == null || searchId == "") {
			//전체 게시글 목록 조회
			BoardPage boardPage = boardListService.getBoardPage(pageNo, board);
			
			req.setAttribute("boardPage", boardPage);
			req.setAttribute("board", board);
			req.setAttribute("searchItem", searchItem);
			return "/view/admin/boardList.jsp";
		} else {
			//검색한 게시글 목록 조회
			BoardPage boardPage = boardListService.searchBoardPage(pageNo, board, searchId, searchItem);
			
			req.setAttribute("searchId", searchId);			
			req.setAttribute("boardPage", boardPage);
			req.setAttribute("board", board);
			req.setAttribute("searchItem", searchItem);
			return "/view/admin/boardList.jsp";
		}
	}
	
	//post 방식 요청 -> 게시글 삭제
	private String processDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String deleteList = req.getParameter("deleteList");
		getInfo(req, res);
		
		boardListService.deleteBoard(board, deleteList);
		
		res.sendRedirect("/admin/boardList.do?board=" + board + "&pageNo=" + pageNo + "&searchId=" + searchId + "&searchItem=" + searchItem);
		return null;
	}
	
	//전달받은 파라미터 값으로 필드 초기화
	private void getInfo(HttpServletRequest req, HttpServletResponse res) {
		board = req.getParameter("board");
		pageNoVal = req.getParameter("pageNo");
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		searchId = req.getParameter("searchId");
		searchItem = req.getParameter("searchItem");
	}
	
}
