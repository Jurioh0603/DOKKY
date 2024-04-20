package reply.service;

import reply.dao.QreplyDAO;
import reply.dao.QreplyDAOImpl;
import reply.model.Qreply;

import java.sql.SQLException;
import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private QreplyDAO replyDAO;

    public ReplyServiceImpl() {
        replyDAO = new QreplyDAOImpl();
    }

    @Override
    public void addReply(Qreply reply) {
        replyDAO.insertReply(reply);
    }

    @Override
    public List<Qreply> getRepliesByBno(Long bno) {
        return replyDAO.selectRepliesByBno(bno);
    }

    @Override
    public void removeReply(Long rno) {
        replyDAO.deleteReply(rno);
    }
}