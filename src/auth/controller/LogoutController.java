package auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutController implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//세션 종료
		HttpSession session = req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		//로그아웃 후 메인 페이지 이동
		res.sendRedirect("/main.do");
		return null;
	}
}
