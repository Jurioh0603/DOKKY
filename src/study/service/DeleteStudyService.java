package study.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import study.dao.ScontentDao;
import study.dao.StudyDao;
import study.dao.SreplyDao;

public class DeleteStudyService {
    
    private StudyDao studyDao = new StudyDao();
    private ScontentDao scontentDao = new ScontentDao();
    private SreplyDao replyDao = new SreplyDao();
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            int bno = deleteReq.getStudyNumber();
            
            replyDao.delete(conn, bno); // Sreply 삭제
            studyDao.delete(conn, bno); // Study 삭제
            scontentDao.delete(conn, bno); // Scontent 삭제
            
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}