package community.controller;

import mvc.command.CommandHandler;
import community.model.Creply;
import community.service.CreplyService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auth.service.User;

public class CreplyController implements CommandHandler {

	private CreplyService replyService = new CreplyService();;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 전달받은 command 분석하여 요청에 맞는 메서드 수행
		String command = req.getParameter("command");

		if ("addReply".equals(command)) {
			return addReply(req, res);
		} else if ("removeReply".equals(command)) {
			return removeReply(req, res);
		} else if ("updateReply".equals(command)) {
			return updateReply(req, res);
		} else {
			return "/view/board/errorPage/notFoundPage.jsp";
		}
	}

	//댓글 작성
	private String addReply(HttpServletRequest req, HttpServletResponse res) {
		try {
			User user = (User) req.getSession().getAttribute("authUser");
			String memid = null;
			if (user != null) {
				memid = user.getId();
			}
			req.setAttribute("memid", memid);
			int bno = Integer.parseInt(req.getParameter("no"));
			String rcontent = req.getParameter("rcontent");

			//작성한 댓글의 정보를 하나의 Creply 객체에 저장
			Creply reply = new Creply();
			reply.setBno(bno);
			reply.setMemid(memid);
			reply.setRcontent(rcontent);

			replyService.addReply(reply);

			res.sendRedirect("/community/read.do?no=" + bno);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "/view/board/errorPage/notFoundPage.jsp";
		}
	}

	//댓글 삭제
	private String removeReply(HttpServletRequest req, HttpServletResponse res) {
		try {
			User user = (User) req.getSession().getAttribute("authUser");
			String memid = null;
			if (user != null) {
				memid = user.getId();
			}
			req.setAttribute("memid", memid);
			int rno = Integer.parseInt(req.getParameter("rno"));
			int bno = Integer.parseInt(req.getParameter("bno"));
			
			replyService.removeReply(rno);

			res.sendRedirect("/community/read.do?no=" + bno);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "/view/board/errorPage/notFoundPage.jsp";
		}
	}

	//댓글 수정
	private String updateReply(HttpServletRequest req, HttpServletResponse res) {
		try {
			User user = (User) req.getSession().getAttribute("authUser");
			String memid = null;
			if (user != null) {
				memid = user.getId();
			}
			req.setAttribute("memid", memid);
			int bno = Integer.parseInt(req.getParameter("bno"));
			int rno = Integer.parseInt(req.getParameter("rno"));
			String rcontent = req.getParameter("rcontent");

			replyService.updateReply(rno, rcontent);

			res.sendRedirect("/community/read.do?no=" + bno);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "/view/board/errorPage/notFoundPage.jsp";
		}
	}
}
