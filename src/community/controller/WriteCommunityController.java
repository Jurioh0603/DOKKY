package community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import auth.service.User;
import community.service.WriteCommunityService;
import community.service.WriteRequest;
import mvc.command.CommandHandler;


public class WriteCommunityController implements CommandHandler {
	private static final String FORM_VIEW = "/view/board/community/communityWrite.jsp";
	private WriteCommunityService writeService = new WriteCommunityService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	//get 방식 요청 -> 글 작성 폼
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	//post 방식 요청 -> 글 작성 로직 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newBno = writeService.write(writeReq);
		res.sendRedirect("/community/read.do?no=" + newBno);
		return null;
	}

	//작성자와 입력 받은 글 제목, 글 내용을 하나의 WriteRequest에 저장 
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
	    String memberId = user.getId();
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    return new WriteRequest(memberId, title, content);
	}

}
