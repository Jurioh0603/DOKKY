package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import qna.dao.QreplyDao;
import qna.model.Qreply;

public class QreplyService {
    
	private QreplyDao replyDAO = new QreplyDao();

    public void addReply(Qreply reply) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {        
			replyDAO.addReply(conn, reply);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public void removeReply(int rno) throws SQLException {
    	try (Connection conn = ConnectionProvider.getConnection()) { 
    		replyDAO.deleteReply(conn, rno);
    	} catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }    
    
    public void updateReply(int rno, String rcontent) throws SQLException {
    	try (Connection conn = ConnectionProvider.getConnection()) { 
    		replyDAO.updateReply(conn, rno, rcontent);
    	} catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }
}
