package community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import community.dao.CcontentDao;
import community.dao.CommunityDao;
import community.model.Community;
import community.model.Ccontent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteCommunityService {
	
	private CommunityDao communityDao = new CommunityDao();
	private CcontentDao ccontentDao = new CcontentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//DB에 저장하기 위한 Community 객체로 변환
			Community community = toCommunity(req);
			//글 정보 삽입
			Community savedCommunity = communityDao.insert(conn, community);
			if(savedCommunity == null) {
				throw new RuntimeException("fail to insert community");
			}
			//글 내용 삽입
			Ccontent ccontent = new Ccontent(savedCommunity.getBno(),
					req.getContent());
			Ccontent savedContent = ccontentDao.insert(conn, ccontent);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert community_content");
			}
			
			conn.commit();
			
			return savedContent.getBno();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//DB에 저장하기 위한 Community 객체로 변환
	private Community toCommunity(WriteRequest req) {
		Date now = new Date();
		return new Community(0, req.getMemId(), req.getTitle(), now, 0);
	}
}
