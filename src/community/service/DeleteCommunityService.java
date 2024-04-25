package community.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import community.dao.CcontentDao;
import community.dao.CommunityDao;
import community.dao.ReplyDAO;

public class DeleteCommunityService {
    
    private CommunityDao communityDao = new CommunityDao();
    private CcontentDao ccontentDao = new CcontentDao();
    private ReplyDAO replyDao;
    
    public void delete(DeleteRequest deleteReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
            replyDao = new ReplyDAO();
            replyDao.delete(deleteReq.getCommunityNumber()); // DeleteRequest로부터 게시글 번호 가져오기
            communityDao.delete(conn, deleteReq.getCommunityNumber()); // Community 삭제
            ccontentDao.delete(conn, deleteReq.getCommunityNumber()); // Ccontent 삭제
            
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}