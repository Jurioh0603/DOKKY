package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import qna.dao.QnaDao;
import qna.model.Qna;

public class ListQnaService {
	
	 private QnaDao qnaDao = new QnaDao();
	 private int size = 10;
	    
	public QnaPage getQnaPage(int pageNum, String sort) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            int total = qnaDao.selectCount(conn);
            List<Qna> qnaList = qnaDao.select(conn, sort, (pageNum - 1) * size + 1, pageNum * size);
            return new QnaPage(total, pageNum, size, qnaList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public QnaPage getSearchQnaPage(int pageNum, String search, String sort) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = qnaDao.selectSearchCount(conn, search);
			List<Qna> qnaList = null;
			if(sort.equals("replyCount"))
					qnaList = qnaDao.selectSearchReplyCount(conn, search, (pageNum - 1) * size + 1, pageNum * size);
			else
					qnaList = qnaDao.selectSearch(conn, search, sort, (pageNum - 1) * size + 1, pageNum * size);
			return new QnaPage(total, pageNum, size, qnaList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
