package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.PostListService;
import member.service.PostPage;
import mvc.command.CommandHandler;

public class PostListController implements CommandHandler {
	
	private PostListService postListService = new PostListService();
	String board = null;
	String pageNoVal = null;
	int pageNo = 1;
	String search = null;
	String userId = null;
	
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
		if(search == null || search == "") {
			PostPage postPage = postListService.getBoardPage(pageNo, board, userId);
			
			req.setAttribute("postPage", postPage);
			req.setAttribute("board", board);
			return "/view/member/myPostList.jsp";
		} else {
			PostPage postPage = postListService.searchBoardPage(pageNo, board, search, userId);
			
			req.setAttribute("search", search);			
			req.setAttribute("postPage", postPage);
			req.setAttribute("board", board);
			return "/view/member/myPostList.jsp";
		}
	}
	
	private String processDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String deleteList = req.getParameter("deleteList");
		getInfo(req, res);
		
		postListService.deleteBoard(board, deleteList);
		
		res.sendRedirect("/postList.do?board=" + board + "&pageNo=" + pageNo + "&search=" + search);
		return null;
	}
	
	private void getInfo(HttpServletRequest req, HttpServletResponse res) {
		board = req.getParameter("board");
		pageNoVal = req.getParameter("pageNo");
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		search = req.getParameter("search");
		userId = ((User)req.getSession().getAttribute("authUser")).getId();
	}
	
}
