package study.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import study.dao.StudyDao;

public class ListStudyService {

    private StudyDao studyDao = new StudyDao();
    private int size = 10;

    public StudyPage getStudyPage(int pageNum) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            int total = studyDao.selectCount(conn);
            List<StudyList> studyList = studyDao.select(conn, (pageNum - 1) * size + 1, pageNum * size);
            return new StudyPage(total, pageNum, size, studyList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public StudyPage getSearchStudyPage(int pageNum, String search, String sort) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = studyDao.selectSearchCount(conn, search);
			List<StudyList> studyList = studyDao.selectSearch(conn, search, sort, (pageNum - 1) * size + 1, pageNum * size);
			return new StudyPage(total, pageNum, size, studyList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
