package qna.controller;

import mvc.command.CommandHandler;
import qna.model.Qreply;
import qna.service.QreplyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;

public class QreplyController implements CommandHandler {

    private QreplyService replyService = new QreplyService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String command = req.getParameter("command");

        if ("addReply".equals(command)) {
            return addReply(req, res);
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
            
            Qreply reply = new Qreply();
            reply.setBno(bno);
            reply.setMemid(memid);
            reply.setRcontent(rcontent);

            replyService.addReply(reply);
            
            res.sendRedirect("/qna/read.do?no=" + bno);
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

            res.sendRedirect("/qna/read.do?no=" + bno);
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
            
            res.sendRedirect("/qna/read.do?no=" + bno);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "/view/board/errorPage/notFoundPage.jsp";
        }
    }
}
