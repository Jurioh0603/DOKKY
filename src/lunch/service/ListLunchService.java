package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
}
