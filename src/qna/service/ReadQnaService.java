package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import qna.dao.QcontentDao;
import qna.dao.QnaDao;
import qna.model.Qcontent;
import qna.model.Qna;
import reply.dao.ReplyDAO;
import reply.model.ReplyDTO;

public class ReadQnaService {

	private QnaDao qnaDao = new QnaDao();
	private QcontentDao qcontentDao = new QcontentDao();
	private ReplyDAO replyDao = null;
	
	public QnaData getQna(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Qna qna = qnaDao.selectById(conn, bno);
			if(qna == null) {
				throw new QnaNotFoundException();
			}
			Qcontent qcontent = qcontentDao.selectById(conn, bno);
			replyDao = new ReplyDAO();
			
			List<ReplyDTO> replyList = replyDao.getRepliesByBno(bno);
			
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
