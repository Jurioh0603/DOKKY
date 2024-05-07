package auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.ChangePwdFailException;
import auth.service.ChangePwdService;
import mvc.command.CommandHandler;

public class ChangePwdController implements CommandHandler {

	private static final String FORM_VIEW = "/view/account/changePwd.jsp";
	private ChangePwdService changePwdService = new ChangePwdService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//get 방식 요청은 허용되지 않음
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return "/view/errorPage/forbiddenPage.jsp";
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	//post 방식 요청 -> 비밀번호 변경 로직 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//새로운 request 객체가 생성되면 저장해 둔 memid값이 사라지기 때문에
		//ChangePwdService 클래스의 memid 필드에 저장
		String memid = (String) req.getAttribute("memid");
		if (memid != null) {
			changePwdService.setMemid(memid);
			req.setAttribute("save", Boolean.TRUE);
		}

		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (password1 == null || password1.isEmpty())
			errors.put("password1", Boolean.TRUE);
		if (password2 == null || password2.isEmpty())
			errors.put("password2", Boolean.TRUE);
		if (password1 != null && password1.contains(" "))
			errors.put("blank", Boolean.TRUE);

		//작성되지 않은 내용이 있거나 변경할 비밀번호에 공백 문자를 포함하고 있다면 비밀번호 변경 폼으로 이동
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			changePwdService.changePwd(password1, password2);
			req.setAttribute("update", Boolean.TRUE);
			return FORM_VIEW;
		} catch (ChangePwdFailException e) {
			errors.put("cantChange", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}