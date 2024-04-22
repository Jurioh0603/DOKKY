package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;

public class DeleteLunchService {
    
    private LunchDao lunchDao = new LunchDao();
    private LcontentDao lcontentDao = new LcontentDao();
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            int imageNo = lunchDao.delete(conn, deleteReq.getImageNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            int lunchNo = lunchDao.delete(conn, deleteReq.getLunchNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            int lcontentNo = lcontentDao.delete(conn, deleteReq.getLunchNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            
            
            lunchDao.delete(conn, imageNo); // Study 삭제
            lunchDao.delete(conn, lunchNo); // Study 삭제
            lcontentDao.delete(conn, lcontentNo); // Scontent 삭제
            
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}