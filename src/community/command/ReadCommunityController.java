package community.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.service.CcontentNotFoundException;
import community.service.CommunityData;
import community.service.CommunityNotFoundException;
import community.service.ReadCommunityService;
import mvc.command.CommandHandler;

public class ReadCommunityController implements CommandHandler{
	
	private ReadCommunityService readService = new ReadCommunityService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		String noVal = req.getParameter("no");
		int bno = Integer.parseInt(noVal);
		try {
			CommunityData communityData = readService.getCommunity(bno, true);
			req.setAttribute("communityData", communityData);
			return "/view/board/community/communityDetail.jsp";
		}catch (CommunityNotFoundException | CcontentNotFoundException e) {
			req.getServletContext().log("no community",e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
