package study.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import study.model.Study;
import jdbc.connection.ConnectionProvider;
import study.dao.StudyDao;

public class ListStudyService {

    private StudyDao studyDao = new StudyDao();
    private int size = 10;

    public StudyPage getStudyPage(int pageNum, String sort) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            int total = studyDao.selectCount(conn);
            List<Study> studyList = studyDao.select(conn, sort, (pageNum - 1) * size + 1, pageNum * size);
            return new StudyPage(total, pageNum, size, studyList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public StudyPage getSearchStudyPage(int pageNum, String search, String sort) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = studyDao.selectSearchCount(conn, search);
			List<Study> studyList = null;
			if(sort.equals("replyCount"))
					studyList = studyDao.selectSearchReplyCount(conn, search, (pageNum - 1) * size + 1, pageNum * size);
			else
					studyList = studyDao.selectSearch(conn, search, sort, (pageNum - 1) * size + 1, pageNum * size);
			return new StudyPage(total, pageNum, size, studyList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
