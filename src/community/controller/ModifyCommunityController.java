package community.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import community.service.ModifyRequest;
import community.service.ModifyCommunityService;
import community.service.PermissionDeniedException;
import community.service.ReadCommunityService;
import community.service.CommunityData;
import community.service.CommunityNotFoundException;

public class ModifyCommunityController implements CommandHandler{
	private static final String FORM_VIEW = "/view/board/community/communityModifyForm.jsp";
	
	private ReadCommunityService readService = new ReadCommunityService();
	private ModifyCommunityService modifyService = new ModifyCommunityService();
	
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
	
	//get 방식 요청 -> 글 수정 폼
	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException{
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			//수정하고자 하는 글의 data를 읽어옴(조회수 증가X)
			CommunityData communityData = readService.getCommunity(no, false);
			User authUser = (User)req.getSession().getAttribute("authUser");
			if(!canModify(authUser, communityData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			//글 수정 폼에서 글 제목과 내용을 보여주기 위한 modReq
			ModifyRequest modReq = new ModifyRequest(authUser.getId(),no,
					communityData.getCommunity().getTitle(),
					communityData.getContent());
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (CommunityNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	//글 작성자만 글을 수정할 수 있음
	private boolean canModify(User authUser, CommunityData communityData) {
		String writerId = communityData.getCommunity().getMemId();
		return authUser.getId().equals(writerId);
	}
	
	//post 방식 요청 -> 글 수정 로직 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
		  throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		//글을 수정한 내용을 저장하기 위한 modReq
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
			return "/community/read.do?no="+noVal;
		}catch(CommunityNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
