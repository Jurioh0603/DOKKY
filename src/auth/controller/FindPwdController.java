package auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.FindPwdFailException;
import auth.service.FindPwdService;
import mvc.command.CommandHandler;

public class FindPwdController implements CommandHandler {

	private static final String FORM_VIEW = "/view/account/findPwd.jsp";
	private FindPwdService findPwdService = new FindPwdService();

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

	//get 방식 요청 -> 비밀번호 찾기 폼
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	//post 방식 요청 -> 비밀번호 찾기 로직 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String name = trim(req.getParameter("name"));
		String id = trim(req.getParameter("id"));
		String email = trim(req.getParameter("email"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (name == null || name.isEmpty())
			errors.put("name", Boolean.TRUE);
		if (id == null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if (email == null || email.isEmpty())
			errors.put("email", Boolean.TRUE);

		//작성되지 않은 내용이 있다면 비밀번호 찾기 폼으로 이동
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			String memid = findPwdService.findMember(name, id, email);
			//해당 회원의 비밀번호 변경을 위해 memid 값을 저장 -> 비밀번호 변경 페이지 이동
			req.setAttribute("memid", memid);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/changePwd.do");
			dispatcher.forward(req, res);
			return null;
		} catch (FindPwdFailException e) {
			errors.put("cantFind", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
