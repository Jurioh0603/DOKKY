package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;

public class DeleteQnaService {
	
	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	
	public void delete(DeleteRequest deleteReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int qnaNo = qnaDao.delete(conn, deleteReq.getQnaNumber());
			int qcontentNo = qcontentDao.delete(conn, deleteReq.getQnaNumber());
			
			qnaDao.delete(conn, qnaNo);
			qcontentDao.delete(conn, qcontentNo);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
