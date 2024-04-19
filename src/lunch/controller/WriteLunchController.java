package lunch.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import lunch.service.WriteLunchService;
import lunch.service.WriteRequest;
import mvc.command.CommandHandler;


public class WriteLunchController implements CommandHandler {
	private static final String FORM_VIEW = "/view/board/lunch/lunchWrite.jsp";
	private WriteLunchService writeService = new WriteLunchService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("GET")) {
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
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newBno = writeService.write(writeReq);
		req.setAttribute("newBno", newBno);
		
		return "/view/board/lunch/lunchSelect.jsp";
		}
	
		private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		    String memberId = user.getId(); // 회원 ID 가져오기
		    String title = req.getParameter("title");
		    String content = req.getParameter("content");
		    return new WriteRequest(memberId, title, content);
		}

}