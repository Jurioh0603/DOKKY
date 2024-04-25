package study.controller;

import mvc.command.CommandHandler;
import study.model.Sreply;
import study.service.SreplyService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auth.service.User;
import java.util.List;

public class SreplyController implements CommandHandler {

    private SreplyService replyService;

    public SreplyController() {
        replyService = new SreplyService();
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String command = req.getParameter("command");

        if ("addReply".equals(command)) {
            return addReply(req, res);
        } else if ("getReplies".equals(command)) {
            return getReplies(req, res);
        } else if ("removeReply".equals(command)) {
            return removeReply(req, res);
        } else if ("updateReply".equals(command)) {
            return updateReply(req, res);
        } else {
            return "/view/board/errorPage/notFoundPage.jsp";
        }
    }

    private String addReply(HttpServletRequest req, HttpServletResponse res) {
        try {
            User user = (User)req.getSession().getAttribute("authUser");
            String memid = null;
            if(user != null) {
               memid = user.getId();
            }
            req.setAttribute("memid", memid);
            int bno = Integer.parseInt(req.getParameter("no"));
            String rcontent = req.getParameter("rcontent");
            
            Sreply reply = new Sreply();
            reply.setBno(bno);
            reply.setMemid(memid);
            reply.setRcontent(rcontent);

            replyService.addReply(reply);
            
            List<Sreply> replies = replyService.getRepliesByBno(bno);
            req.setAttribute("replies", replies);
            
            res.sendRedirect("/study/read.do?no=" + bno);
            return null;
            } catch (Exception e) {
            e.printStackTrace();
            return "/view/board/errorPage/notFoundPage.jsp";
        }
    }

    private String getReplies(HttpServletRequest req, HttpServletResponse res) {
        try {
            int bno = Integer.parseInt(req.getParameter("no"));

            List<Sreply> replies = replyService.getRepliesByBno(bno);

            req.setAttribute("replies", replies);

            res.sendRedirect("/study/read.do?no=" + bno);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "/view/board/errorPage/notFoundPage.jsp";
        }
    }

    private String removeReply(HttpServletRequest req, HttpServletResponse res) {
        try {
        	User user = (User)req.getSession().getAttribute("authUser");
            String memid = null;
            if(user != null) {
               memid = user.getId();
            }
            req.setAttribute("memid", memid);
            int rno = Integer.parseInt(req.getParameter("rno"));
            int bno = Integer.parseInt(req.getParameter("bno"));
            replyService.removeReply(rno);

            res.sendRedirect("/study/read.do?no=" + bno);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "/view/board/errorPage/notFoundPage.jsp";
        }
    }

    private String updateReply(HttpServletRequest req, HttpServletResponse res) {
        try {
        	User user = (User)req.getSession().getAttribute("authUser");
            String memid = null;
            if(user != null) {
               memid = user.getId();
            }
            req.setAttribute("memid", memid);
        	int bno = Integer.parseInt(req.getParameter("bno"));
            int rno = Integer.parseInt(req.getParameter("rno"));
            String rcontent = req.getParameter("rcontent");

            // 수정된 댓글을 업데이트
            replyService.updateReply(rno, rcontent);
            
            res.sendRedirect("/study/read.do?no=" + bno);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "/view/board/errorPage/notFoundPage.jsp";
        }
    }
}
