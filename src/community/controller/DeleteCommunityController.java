package community.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import community.service.DeleteRequest;
import community.service.DeleteCommunityService;

public class DeleteCommunityController implements CommandHandler {
    
	private static final String FORM_VIEW = "/view/board/community/communityDetail.jsp";
    private DeleteCommunityService deleteService = new DeleteCommunityService();

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

    //post 방식 요청 -> 글 삭제 로직 처리
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
    	String noVal = req.getParameter("no");
        int no = Integer.parseInt(noVal);
        
        //삭제할 글 번호를 저장하고 있는 DeleteRequest
        DeleteRequest deleteReq = new DeleteRequest();
        deleteReq.setCommunityNumber(no);
        
        Map<String, Object> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        try {
            deleteService.delete(deleteReq);
            return "/view/board/community/deleteSuccess.jsp";
        } catch (Exception e) {
            errors.put("deleteFailed", true);
            return "/view/board/community/deleteFail.jsp";
        }
    }

}