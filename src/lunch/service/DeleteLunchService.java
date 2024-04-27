package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import lunch.dao.ImageDao;
import lunch.dao.LcontentDao;
import lunch.dao.LreplyDao;
import lunch.dao.LunchDao;

public class DeleteLunchService {
    
    private LunchDao lunchDao = new LunchDao();
    private LcontentDao lcontentDao = new LcontentDao();
    private ImageDao imageDao = new ImageDao();
    private LreplyDao replyDao = new LreplyDao();
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            int bno = deleteReq.getLunchNumber();
            
            replyDao.delete(conn, bno);
            imageDao.delete(conn, bno);
            lunchDao.delete(conn, bno);
            lcontentDao.delete(conn, bno);
            
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}