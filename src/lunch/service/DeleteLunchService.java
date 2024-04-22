package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import lunch.dao.ImageDao;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;

public class DeleteLunchService {
    
    private LunchDao lunchDao = new LunchDao();
    private LcontentDao lcontentDao = new LcontentDao();
    private ImageDao imageDao = new ImageDao();
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            int bno = deleteReq.getLunchNumber();
            
            imageDao.delete(conn, bno); // Study 삭제
            lunchDao.delete(conn, bno); // Study 삭제
            lcontentDao.delete(conn, bno); // Scontent 삭제
            
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}