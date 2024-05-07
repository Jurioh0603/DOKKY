package auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginController implements CommandHandler {

	private static final String FORM_VIEW = "/view/account/login.jsp";
	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	//get 방식 요청 -> 로그인 폼
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		//클라이언트가 현재 요청을 보낸 이전 페이지의 URL을 세션에 저장
		String uri = req.getHeader("Referer");
		if (uri != null) {
			req.getSession().setAttribute("prevPage", uri);
		}
		return FORM_VIEW;
	}

	//post 방식 요청 -> 로그인 로직 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = trim(req.getParameter("id"));
		String password = req.getParameter("password");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (id == null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if (password == null || password.isEmpty())
			errors.put("password", Boolean.TRUE);

		//작성되지 않은 내용이 있다면 로그인 폼으로 이동
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			User user = loginService.login(id, password);
			//로그인 정보 -> 세션 저장
			req.getSession().setAttribute("authUser", user);

			//로그인 후 이전 페이지로 이동하기 위해 세션에 저장해 둔 prevPage
			String prevPage = (String) req.getSession().getAttribute("prevPage");
			req.getSession().removeAttribute("prevPage");
			//단, 아이디/비밀번호 찾기나 회원가입 이후 로그인 페이지에 접근했다면 메인 페이지로 이동
			if (prevPage == null || prevPage.contains("findId") || prevPage.contains("changePwd")
					|| prevPage.contains("join")) {
				res.sendRedirect("/main.do");
			} else {
				res.sendRedirect(prevPage);
			}
			return null;
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
