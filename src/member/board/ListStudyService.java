package member.board;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;

public class ListStudyService {

	private StudyDao studyDao = new StudyDao();
	private int size = 10;
	
	public StudyPage getStudyPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = studyDao.selectCount(conn);
			List<Study> content = studyDao.selectBoard(conn, (pageNum - 1) * size, size);
			return new StudyPage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
