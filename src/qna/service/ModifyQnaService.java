package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;
import qna.model.Qna;

public class ModifyQnaService {
	
	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Qna qna = qnaDao.selectById(conn, modReq.getQnaNumber());
			if(qna == null) {
				throw new QnaNotFoundException();
			}
			if(!canModify(modReq.getMemId(), qna)) {
				throw new PermissionDeniedException();
			}
			qnaDao.update(conn, modReq.getQnaNumber(), modReq.getTitle());
			qcontentDao.update(conn, modReq.getQnaNumber(), modReq.getContent());
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String modifyingMemId, Qna qna) {
		return qna.getMemId().equals(modifyingMemId);
	}
}
