package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;
import qna.dao.QreplyDao;

public class DeleteQnaService {
	
	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	private QreplyDao replyDao = new QreplyDao();;
	
	public void delete(DeleteRequest deleteReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int bno = deleteReq.getQnaNumber();
			
			replyDao.delete(conn, bno);
			qnaDao.delete(conn, bno);
			qcontentDao.delete(conn, bno);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
