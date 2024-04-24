package lunch.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import auth.service.User;
import lunch.service.ModifyRequest;
import lunch.model.Image;
import lunch.service.LunchData;
import lunch.service.LunchNotFoundException;
import lunch.service.ModifyLunchService;
import lunch.service.PermissionDeniedException;
import lunch.service.ReadLunchService;
import lunch.service.WriteRequest;
import mvc.command.CommandHandler;

public class ModifyLunchController implements CommandHandler{
	private static final String FORM_VIEW = "/view/board/lunch/lunchModifyForm.jsp";
	private static final String SAVE_DIRECTORY = "upload";
	private ReadLunchService readService = new ReadLunchService();
	private ModifyLunchService modifyService = new ModifyLunchService();
	
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
			LunchData lunchData = readService.getLunch(no, false);
			User authUser = (User)req.getSession().getAttribute("authUser");
			if(!canModify(authUser, lunchData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(),no,
					lunchData.getLunch().getTitle(),
					lunchData.getContent());
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (LunchNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User authUser, LunchData lunchData) {
		String writerId = lunchData.getLunch().getMemId();
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
		Image image = createImageRequest(req);
		
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq,image);
			return "/lunch/read.do?no="+noVal;
		}catch(LunchNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
	private Image createImageRequest(HttpServletRequest req) {
		String bno = req.getParameter("no");
		int no = Integer.parseInt(bno);
	    String fileName = req.getParameter("file");
	    String fileRealName = UUID.randomUUID().toString();
	    
	    // 파일 업로드 관련 파라미터 가져오기
	    Part filePart = null;
		try {
			filePart = req.getPart("file");
		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
		}
		
	    if (filePart != null) {
	    	// 파일 저장할 경로 설정
	    	String savePath = req.getServletContext().getRealPath("") + File.separator + SAVE_DIRECTORY;
	    	
	    	// 저장할 디렉토리가 존재하지 않으면 생성
	    	File fileSaveDir = new File(savePath);
	    	if (!fileSaveDir.exists()) {
	    		fileSaveDir.mkdir();
	    	}
	    	
	    	// 파일 저장
	    	String filePath = savePath + File.separator + fileRealName;
	    	
	        // 파일 업로드 처리
	        fileName = filePart.getSubmittedFileName();
	        if (fileName != null && !fileName.isEmpty()) { // 파일 이름이 비어 있지 않을 경우에만 처리

	            try (InputStream input = filePart.getInputStream();
	                 FileOutputStream output = new FileOutputStream(filePath)) {
	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = input.read(buffer)) != -1) {
	                    output.write(buffer, 0, bytesRead);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return new Image(fileName, fileRealName, no);
	}

}
