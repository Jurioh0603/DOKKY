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
import lunch.service.WriteLunchService;
import lunch.service.WriteRequest;
import mvc.command.CommandHandler;


public class WriteLunchController implements CommandHandler {
    private static final String SAVE_DIRECTORY = "upload"; // 파일을 저장할 디렉토리명
	private static final String FORM_VIEW = "/view/board/lunch/lunchWrite.jsp";
	private WriteLunchService writeService = new WriteLunchService();
	
	
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
		res.sendRedirect("/lunch/read.do?no=" + newBno);
		return null;
		}
	
		private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		    String memberId = user.getId(); // 회원 ID 가져오기
		    String title = req.getParameter("title");
		    String content = req.getParameter("content");
		    String filename = req.getParameter("file");
		    String filerealname = UUID.randomUUID().toString();
		    
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
		    	String filePath = savePath + File.separator + filerealname;
		    	
		        // 파일 업로드 처리
		        filename = filePart.getSubmittedFileName();
		        if (filename != null && !filename.isEmpty()) { // 파일 이름이 비어 있지 않을 경우에만 처리

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
		    
		    return new WriteRequest(memberId, title, content, filename, filerealname);
		}

}