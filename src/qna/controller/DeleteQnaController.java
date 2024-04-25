package qna.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.DeleteQnaService;
import qna.service.DeleteRequest;

public class DeleteQnaController implements CommandHandler {
	private static final String FORM_VIEW = "/view/board/qna/qnaDetail.jsp";
	private DeleteQnaService deleteService = new DeleteQnaService();
	
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
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		DeleteRequest deleteReq = new DeleteRequest();
		deleteReq.setQnaNumber(no);
		
		//오류 담을 Map 객체
		Map<String, Object> errors = new HashMap<>();
		req.setAttribute("errors", errors);
	
		try {
			deleteService.delete(deleteReq);
			return "/view/board/qna/deleteSuccess.jsp";
		} catch (Exception e) {
			errors.put("deleteFailed", true);
			return "/view/board/qna/deleteFail.jsp";
		}
	}
}
