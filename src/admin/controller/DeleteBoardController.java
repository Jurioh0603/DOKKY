package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.DeleteBoardService;
import mvc.command.CommandHandler;

public class DeleteBoardController implements CommandHandler {
	
	private DeleteBoardService deleteBoardService = new DeleteBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String deleteList = req.getParameter("deleteList");
		String board = req.getParameter("board");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		deleteBoardService.deleteBoard(board, deleteList);
		res.sendRedirect("/admin/boardList.do?board=" + board + "&pageNo=" + pageNo);
		return null;
	}
}
