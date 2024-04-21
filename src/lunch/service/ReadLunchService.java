package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;
import lunch.model.Lcontent;
import lunch.model.Lunch;

public class ReadLunchService {
	
	private LunchDao lunchDao = new LunchDao();
	private LcontentDao LcontentDao = new LcontentDao();
	
	public LunchData getLunch(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Lunch lunch = lunchDao.selectById(conn, bno);
			if(lunch == null) {
				throw new LunchNotFoundException();
			}
			Lcontent lcontent = LcontentDao.selectById(conn, bno);
			if(lcontent == null) {
				throw new LcontentNotFoundException();
			}
			if(increaseHit) {
				lunchDao.increaseHit(conn, bno);
			}
			return new LunchData(lunch, lcontent);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
