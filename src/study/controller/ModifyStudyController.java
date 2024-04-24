package study.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import study.service.ModifyRequest;
import study.service.ModifyStudyService;
import study.service.PermissionDeniedException;
import study.service.ReadStudyService;
import study.service.StudyData;
import study.service.StudyNotFoundException;

public class ModifyStudyController implements CommandHandler{
	private static final String FORM_VIEW = "/view/board/study/modifyForm.jsp";
	
	
	private ReadStudyService readService = new ReadStudyService();
	private ModifyStudyService modifyService = new ModifyStudyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException{
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			StudyData studyData = readService.getStudy(no, false);
			User authUser = (User)req.getSession().getAttribute("authUser");
			if(!canModify(authUser, studyData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(),no,
					studyData.getStudy().getTitle(),
					studyData.getContent());
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (StudyNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User authUser, StudyData studyData) {
		String writerId = studyData.getStudy().getMemId();
		return authUser.getId().equals(writerId);
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
		  throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, 
				req.getParameter("title"), 
				req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/study/read.do?no="+noVal;
		}catch(StudyNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			 //에러페이지 설정
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/view/errorPage/404Page.jsp");
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
