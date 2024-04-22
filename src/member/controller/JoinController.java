package member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinController implements CommandHandler {

	private static final String FORM_VIEW = "view/account/join.jsp";
	private JoinService joinService = new JoinService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		JoinRequest joinReq = new JoinRequest();
		joinReq.setMemid(req.getParameter("id"));
		joinReq.setMempw(req.getParameter("password"));
		joinReq.setVerifyMemPw(req.getParameter("verifyPassword"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setEmail(req.getParameter("email"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		joinReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			joinService.join(joinReq);
			req.setAttribute("joinSuccess", "회원가입성공");
			return "/login.do";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}
