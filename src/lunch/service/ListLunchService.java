package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import community.model.Community;
import community.service.CommunityPage;
import lunch.dao.LunchDao;
import lunch.model.Lunch;
import jdbc.connection.ConnectionProvider;

public class ListLunchService {

	private LunchDao lunchDao = new LunchDao();
	private int size = 8;
	
	public LunchPage getLunchPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = lunchDao.selectCount(conn);
			List<ListRequest> lunchList = lunchDao.select(conn, (pageNum - 1) * size + 1, pageNum * size);
			return new LunchPage(total, pageNum, size, lunchList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public LunchPage getSearchLunchPage(int pageNum, String search, String sort) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = lunchDao.selectSearchCount(conn, search);
			List<ListRequest> lunchList = lunchDao.selectSearch(conn, search, sort, (pageNum - 1) * size + 1, pageNum * size);
			return new LunchPage(total, pageNum, size, lunchList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
