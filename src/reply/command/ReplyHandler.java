package reply.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.WriteQnaService;
import reply.model.Qreply;
import reply.service.ReplyService;
import reply.service.ReplyServiceImpl;
import java.sql.Timestamp;

    
    
    public class ReplyHandler implements CommandHandler {
        private ReplyService replyService;

        public ReplyHandler() {
            replyService = new ReplyServiceImpl();
        }

        @Override
        public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String command = request.getParameter("command");

            if ("addReply".equals(command)) {
                addReply(request, response);
            } else if ("removeReply".equals(command)) {
                removeReply(request, response);
            } else if ("getRepliesByBno".equals(command)) {
                getRepliesByBno(request, response);
            }
            return null;
        }

        public void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long bno = Long.parseLong(request.getParameter("bno"));
            String memid = request.getParameter("memid");
            String rcontent = request.getParameter("rcontent");
            Long parentRno = Long.parseLong(request.getParameter("parentRno"));

            Qreply reply = new Qreply();
            reply.setBno(bno);
            reply.setMemid(memid);
            reply.setRcontent(rcontent);
            reply.setParentRno(parentRno);
            
            LocalDateTime localDateTime = LocalDateTime.now();
            reply.setCreatedAt(localDateTime);

            replyService.addReply(reply);

            // 추가한 댓글을 포함한 댓글 목록을 다시 로드
            getRepliesByBno(request, response);
        }

        public void getRepliesByBno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long bno = Long.parseLong(request.getParameter("bno"));
            List<Qreply> replies = replyService.getRepliesByBno(bno);

            // 댓글 목록을 HTML 형식으로 변환하여 응답으로 전송
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            for (Qreply reply : replies) {
                out.println("<div class=\"reply\">");
                out.println("<p>" + reply.getMemid() + "</p>");
                out.println("<p>" + reply.getRcontent() + "</p>");
                out.println("</div>");
            }
        }

        public void removeReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long rno = Long.parseLong(request.getParameter("rno"));
            replyService.removeReply(rno);

            // 댓글 삭제 후 목록을 다시 로드
            getRepliesByBno(request, response);
        }
    }