package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;
import qna.dao.ReplyDao;

public class DeleteQnaService {
	
	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	private ReplyDao replyDao;
	
	public void delete(DeleteRequest deleteReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			replyDao = new ReplyDao();
			replyDao.delete(deleteReq.getQnaNumber());
			qnaDao.delete(conn, deleteReq.getQnaNumber());
			qcontentDao.delete(conn, deleteReq.getQnaNumber());
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
