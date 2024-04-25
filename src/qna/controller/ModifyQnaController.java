package qna.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import qna.service.ModifyQnaService;
import qna.service.ModifyRequest;
import qna.service.PermissionDeniedException;
import qna.service.QnaData;
import qna.service.QnaNotFoundException;
import qna.service.ReadQnaService;

public class ModifyQnaController implements CommandHandler {
	private static final String FORM_VIEW = "/view/board/qna/modifyForm.jsp";
	
	private ReadQnaService readService = new ReadQnaService();
	private ModifyQnaService modifyService = new ModifyQnaService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no =Integer.parseInt(noVal);
			QnaData qnaData = readService.getQna(no, false);
			User authUser = (User)req.getSession().getAttribute("authUser");
			if(!canModify(authUser, qnaData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(),no,
					qnaData.getQna().getTitle(),
					qnaData.getContent());
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}catch (QnaNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User authUser, QnaData qnaData) {
		String writerId = qnaData.getQna().getMemId();
		return authUser.getId().equals(writerId);
	}
		
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/qna/read.do?no="+noVal;
		}catch (QnaNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			//ErrorPage 설정
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/errorPage/deleteBoardPage.jsp");
		    dispatcher.forward(req, res);
			return null;
		}catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			
			 //에러페이지 설정
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/view/errorPage/invalidAccessPage.jsp");
		    dispatcher.forward(req, res);
			return null;
		}
	}
}
