package study.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import study.dao.ScontentDao;
import study.dao.SreplyDao;
import study.dao.StudyDao;
import study.model.Scontent;
import study.model.Sreply;
import study.model.Study;

public class ReadStudyService {
	
	private StudyDao studyDao = new StudyDao();
	private ScontentDao scontentDao = new ScontentDao();
	private SreplyDao replyDao = null;
	
	public StudyData getStudy(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Study study = studyDao.selectById(conn, bno);
			if(study == null) {
				throw new StudyNotFoundException();
			}
			Scontent scontent = scontentDao.selectById(conn, bno);
			
			replyDao = new SreplyDao();
			
			List<Sreply> replyList = replyDao.getRepliesByBno(bno);
			
			if(scontent == null) {
				throw new ScontentNotFoundException();
			}
			if(increaseHit) {
				studyDao.increaseHit(conn, bno);
			}
			return new StudyData(study, scontent, replyList);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
