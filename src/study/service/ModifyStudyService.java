package study.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import study.dao.ScontentDao;
import study.dao.StudyDao;
import study.model.Study;

public class ModifyStudyService {
	
	private StudyDao studyDao = new StudyDao();
	private ScontentDao contentDao = new ScontentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Study study = studyDao.selectById(conn,
					modReq.getStudyNumber());
			if (study == null) {
				throw new StudyNotFoundException();
			}
			if(!canModify(modReq.getMemId(),study)) {
				throw new PermissionDeniedException();
			}
			studyDao.update(conn, 
					modReq.getStudyNumber(), modReq.getTitle());
			contentDao.update(conn,
					modReq.getStudyNumber(), modReq.getContent());
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String modfyingMemId, Study study) {
		return study.getMemId().equals(modfyingMemId);
	}

}
