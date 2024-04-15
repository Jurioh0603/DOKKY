package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import member.Member;
import member.dao.MemberDao;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;
import qna.model.Qna;
import qna.model.Qcontent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteQnaService {
	
	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Qna qna = toQna(req);
			Qna savedQna = qnaDao.insert(conn, qna);
			if(savedQna == null) {
				throw new RuntimeException("fail to insert qna");
			}
			Qcontent qcontent = new Qcontent(savedQna.getBno(),
					req.getContent());
			Qcontent savedContent = qcontentDao.insert(conn, qcontent);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert qna_content");
			}
			
			conn.commit();
			
			return savedContent.getBno();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Qna toQna(WriteRequest req) {
		Date now = new Date();
		return new Qna(0, req.getMemId(), req.getTitle(), now, 0);
	}
}
