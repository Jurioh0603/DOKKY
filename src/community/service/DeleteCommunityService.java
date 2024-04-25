package community.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import community.dao.CcontentDao;
import community.dao.CommunityDao;

public class DeleteCommunityService {
    
    private CommunityDao communityDao = new CommunityDao();
    private CcontentDao ccontentDao = new CcontentDao();
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            int communityNo = communityDao.delete(conn, deleteReq.getCommunityNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            int ccontentNo = ccontentDao.delete(conn, deleteReq.getCommunityNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            
            communityDao.delete(conn, communityNo); // Community 삭제
            ccontentDao.delete(conn, ccontentNo); // Ccontent 삭제
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}