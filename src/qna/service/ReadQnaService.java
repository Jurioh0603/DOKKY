package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;
import qna.model.Qcontent;
import qna.model.Qna;
import qna.dao.QreplyDao;
import qna.model.Qreply;

public class ReadQnaService {

	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	private QreplyDao replyDao = new QreplyDao();
	
	public QnaData getQna(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Qna qna = qnaDao.selectById(conn, bno);
			if(qna == null) {
				throw new QnaNotFoundException();
			}
			Qcontent qcontent = qcontentDao.selectById(conn, bno);
			
			List<Qreply> replyList = replyDao.getRepliesByBno(conn, bno);
			
			if(qcontent == null) {
				throw new QnaContentNotFoundException();
			}
			if(increaseHit) {
				qnaDao.increaseHit(conn, bno);
			}
			return new QnaData(qna, qcontent, replyList);
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
