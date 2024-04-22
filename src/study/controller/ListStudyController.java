package study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.service.ListStudyService;
import study.service.StudyPage;
import mvc.command.CommandHandler;

public class ListStudyController implements CommandHandler{
	private ListStudyService listService = new ListStudyService();
		
		@Override
		public String process(HttpServletRequest req, HttpServletResponse res)
				throws Exception {
			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			if(pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			StudyPage studyPage = listService.getStudyPage(pageNo);
			req.setAttribute("studyPage", studyPage);
			return "/view/board/study/studySelect.jsp";
		}
}
