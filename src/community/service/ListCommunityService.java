package community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import community.dao.CommunityDao;
import jdbc.connection.ConnectionProvider;

public class ListCommunityService {

	private CommunityDao communityDao = new CommunityDao();
	private int size = 10;
	
	//전체 글 목록
	public CommunityPage getCommunityPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = communityDao.selectCount(conn);
			List<CommunityList> communityList = communityDao.select(conn, (pageNum - 1) * size + 1, pageNum * size);
			return new CommunityPage(total, pageNum, size, communityList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//검색과 정렬을 한 글 목록
	public CommunityPage getSearchCommunityPage(int pageNum, String search, String sort) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = communityDao.selectSearchCount(conn, search);
			List<CommunityList> communityList = communityDao.selectSearch(conn, search, sort, (pageNum - 1) * size + 1, pageNum * size);
			return new CommunityPage(total, pageNum, size, communityList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
