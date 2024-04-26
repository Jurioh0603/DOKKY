package qna.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.QnaContentNotFoundException;
import qna.service.QnaData;
import qna.service.QnaNotFoundException;
import qna.service.ReadQnaService;

public class ReadQnaController implements CommandHandler{
	
	private ReadQnaService readService = new ReadQnaService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    // 게시글 번호 가져오기
	    String noVal = req.getParameter("no");
	    int bno = Integer.parseInt(noVal);
	    
	    try {
	        // 댓글 내용을 포함한 게시글 정보 가져오기
	        QnaData qnaData = readService.getQna(bno, true);
	        req.setAttribute("qnaData", qnaData);
	        
	        // 수정된 댓글의 정보를 ReplyHandler로 전달하기 위해 댓글 번호와 게시글 번호를 Request 속성에 추가
	        req.setAttribute("rno", req.getParameter("rno")); // 수정할 댓글의 번호
	        req.setAttribute("bno", bno); // 게시글 번호
	        
	        return "/view/board/qna/qnaDetail.jsp";
	    } catch (QnaNotFoundException | QnaContentNotFoundException e) {
	        req.getServletContext().log("no qna", e);
	        res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
	    }
	}

	}
	
