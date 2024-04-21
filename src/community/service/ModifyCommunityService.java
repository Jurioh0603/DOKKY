package community.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import community.dao.CcontentDao;
import community.dao.CommunityDao;
import community.model.Community;

public class ModifyCommunityService {
	
	private CommunityDao communityDao = new CommunityDao();
	private CcontentDao contentDao = new CcontentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Community community = communityDao.selectById(conn,
					modReq.getCommunityNumber());
			if (community == null) {
				throw new CommunityNotFoundException();
			}
			if(!canModify(modReq.getMemId(),community)) {
				throw new PermissionDeniedException();
			}
			communityDao.update(conn, 
					modReq.getCommunityNumber(), modReq.getTitle());
			contentDao.update(conn,
					modReq.getCommunityNumber(), modReq.getContent());
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
	
	private boolean canModify(String modfyingMemId, Community community) {
		return community.getMemId().equals(modfyingMemId);
	}

}
