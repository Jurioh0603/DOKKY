package study.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import study.service.ReadStudyService;
import study.service.ScontentNotFoundException;
import study.service.StudyData;
import study.service.StudyNotFoundException;

public class ReadStudyController implements CommandHandler{
	
	private ReadStudyService readService = new ReadStudyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
		throws Exception{
		String noVal = req.getParameter("no");
		int bno = Integer.parseInt(noVal);
		try {
			StudyData studyData = readService.getStudy(bno, true);
			req.setAttribute("studyData", studyData);
			return "/view/board/study/studyDetail.jsp";
		}catch (StudyNotFoundException | ScontentNotFoundException e) {
		    req.getServletContext().log("no study", e);
		    res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

	}
}
