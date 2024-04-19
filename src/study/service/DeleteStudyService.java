package study.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import study.dao.ScontentDao;
import study.dao.StudyDao;

public class DeleteStudyService {
    
    private StudyDao studyDao = new StudyDao();
    private ScontentDao scontentDao = new ScontentDao();
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            int studyNo = studyDao.delete(conn, deleteReq.getStudyNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            int scontentNo = scontentDao.delete(conn, deleteReq.getStudyNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            
            studyDao.delete(conn, studyNo); // Study 삭제
            scontentDao.delete(conn, scontentNo); // Scontent 삭제
            
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}