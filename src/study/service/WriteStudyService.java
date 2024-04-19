package study.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import study.dao.ScontentDao;
import study.dao.StudyDao;
import study.model.Study;
import study.model.Scontent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteStudyService {
	
	private StudyDao StudyDao = new StudyDao();
	private ScontentDao ScontentDao = new ScontentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Study study = toStudy(req);
			Study savedStudy = StudyDao.insert(conn, study);
			if(savedStudy == null) {
				throw new RuntimeException("fail to insert study");
			}
			Scontent scontent = new Scontent(savedStudy.getBno(),
					req.getContent());
			Scontent savedContent = ScontentDao.insert(conn, scontent);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert study_content");
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
	
	private Study toStudy(WriteRequest req) {
		Date now = new Date();
		return new Study(0, req.getMemId(), req.getTitle(), now, 0);
	}
}
