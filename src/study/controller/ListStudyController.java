package study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.service.ListStudyService;
import study.service.StudyPage;
import mvc.command.CommandHandler;

public class ListStudyController implements CommandHandler{
	private ListStudyService listService = new ListStudyService();
		
		@Override
		public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
			String search = req.getParameter("search");
			String pageNoVal = req.getParameter("pageNo");
			String sort = req.getParameter("sort");
			
			int pageNo = 1;
			if(pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			if(sort == null) {
				sort = "bno";
			}
			
			StudyPage studyPage = null;
			if(search == null)
				studyPage = listService.getStudyPage(pageNo);
			else
				studyPage = listService.getSearchStudyPage(pageNo, search, sort);
			req.setAttribute("studyPage", studyPage);
			req.setAttribute("search", search);
			req.setAttribute("sort", sort);
			return "/view/board/study/studySelect.jsp";
		}
}
