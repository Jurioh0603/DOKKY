package community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import community.dao.CcontentDao;
import community.dao.CommunityDao;
import jdbc.connection.ConnectionProvider;
import community.model.ReplyDTO;
import community.dao.ReplyDAO;
import community.model.Ccontent;
import community.model.Community;

public class ReadCommunityService {
	
	private CommunityDao communityDao = new CommunityDao();
	private CcontentDao ccontentDao = new CcontentDao();
	private ReplyDAO replyDao = null;
	
	public CommunityData getCommunity(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Community community = communityDao.selectById(conn, bno);
			if(community == null) {
				throw new CommunityNotFoundException();
			}
			Ccontent ccontent = ccontentDao.selectById(conn, bno);
			
			replyDao = new ReplyDAO();
			
			List<ReplyDTO> replyList = replyDao.getRepliesByBno(bno);
			
			if(ccontent == null) {
				throw new CcontentNotFoundException();
			}
			if(increaseHit) {
				communityDao.increaseHit(conn, bno);
			}
			return new CommunityData(community, ccontent, replyList);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
