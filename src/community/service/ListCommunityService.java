package community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import community.dao.CommunityDao;
import community.model.Community;
import jdbc.connection.ConnectionProvider;

public class ListCommunityService {

	private CommunityDao communityDao = new CommunityDao();
	private int size = 10;
	
	public CommunityPage getCommunityPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = communityDao.selectCount(conn);
			List<Community> communityList = communityDao.select(conn, (pageNum - 1) * size + 1, pageNum * size);
			return new CommunityPage(total, pageNum, size, communityList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public CommunityPage getSearchCommunityPage(int pageNum, String search) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = communityDao.selectSearchCount(conn, search);
			List<Community> communityList = communityDao.selectSearch(conn, search, (pageNum - 1) * size + 1, pageNum * size);
			return new CommunityPage(total, pageNum, size, communityList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
