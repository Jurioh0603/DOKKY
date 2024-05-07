package community.service;

import java.sql.Connection;
import java.sql.SQLException;

import community.dao.CreplyDao;
import community.model.Creply;
import jdbc.connection.ConnectionProvider;

public class CreplyService {
    
	private CreplyDao replyDAO = new CreplyDao();

	//댓글 작성
    public void addReply(Creply reply) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {        
			replyDAO.addReply(conn, reply);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    //댓글 삭제
    public void removeReply(int rno) throws SQLException {
    	try (Connection conn = ConnectionProvider.getConnection()) { 
    		replyDAO.deleteReply(conn, rno);
    	} catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }    
    
    //댓글 수정
    public void updateReply(int rno, String rcontent) throws SQLException {
    	try (Connection conn = ConnectionProvider.getConnection()) { 
    		replyDAO.updateReply(conn, rno, rcontent);
    	} catch(SQLException e) {
			throw new RuntimeException(e);
		}
    }
}
