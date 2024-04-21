package lunch.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lunch.service.LcontentNotFoundException;
import lunch.service.LunchData;
import lunch.service.LunchNotFoundException;
import lunch.service.ReadLunchService;
import mvc.command.CommandHandler;

public class ReadLunchController implements CommandHandler{
	
	private ReadLunchService readService = new ReadLunchService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
		throws Exception{
		String noVal = req.getParameter("no");
		int bno = Integer.parseInt(noVal);
		try {
			LunchData lunchData = readService.getLunch(bno, true);
			req.setAttribute("lunchData", lunchData);
			return "/view/board/lunch/lunchDetail.jsp";
		}catch (LunchNotFoundException | LcontentNotFoundException e) {
		    req.getServletContext().log("no lunch", e);
		    
		    //에러페이지 설정
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/view/errorPage/deleteBoardPage.jsp");
		    dispatcher.forward(req, res);
		    return null;
		}

	}
}
