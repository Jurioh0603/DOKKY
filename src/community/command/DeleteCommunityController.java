package community.command;

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

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
    	String noVal = req.getParameter("no");
        int no = Integer.parseInt(noVal);
        
        DeleteRequest deleteReq = new DeleteRequest(); // DeleteRequest 생성
        deleteReq.setCommunityNumber(no);
        
        // 오류를 담을 Map 객체
        Map<String, Object> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        try {
            // 글 삭제 서비스 호출
            deleteService.delete(deleteReq);
            // 삭제 성공 시 성공 페이지로 이동
            return "/view/board/community/deleteSuccess.jsp";
        } catch (Exception e) {
            // 삭제 실패 시 실패 페이지로 이동
            errors.put("deleteFailed", true);
            return "/view/errorPage/deleteFail.jsp";
        }
    }

}