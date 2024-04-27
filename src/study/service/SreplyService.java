package study.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import study.dao.SreplyDao;
import study.model.Sreply;

public class SreplyService {
    
	private SreplyDao replyDAO = new SreplyDao();

	public void addReply(Sreply reply) throws SQLException {
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
