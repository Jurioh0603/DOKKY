package community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import community.dao.CcontentDao;
import community.dao.CommunityDao;
import jdbc.connection.ConnectionProvider;
import community.model.Creply;
import community.dao.CreplyDao;
import community.model.Ccontent;
import community.model.Community;

public class ReadCommunityService {
	
	private CommunityDao communityDao = new CommunityDao();
	private CcontentDao ccontentDao = new CcontentDao();
	private CreplyDao replyDao = new CreplyDao();
	
	public CommunityData getCommunity(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()){
			//게시글 정보 조회
			Community community = communityDao.selectById(conn, bno);
			if(community == null) {
				throw new CommunityNotFoundException();
			}
			//게시글 내용 조회
			Ccontent ccontent = ccontentDao.selectById(conn, bno);
			
			//게시글에 작성된 댓글 조회
			List<Creply> replyList = replyDao.getRepliesByBno(conn, bno);
			
			if(ccontent == null) {
				throw new CcontentNotFoundException();
			}
			//게시글 조회수 증가
			if(increaseHit) {
				communityDao.increaseHit(conn, bno);
			}
			return new CommunityData(community, ccontent, replyList);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
