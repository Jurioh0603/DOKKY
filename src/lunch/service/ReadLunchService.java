
package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jdbc.connection.ConnectionProvider;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;
import lunch.model.Lcontent;
import lunch.model.Lunch;
import lunch.dao.LreplyDao;
import lunch.model.Lreply;

public class ReadLunchService {
	
	private LunchDao lunchDao = new LunchDao();
	private LcontentDao LcontentDao = new LcontentDao();
	private LreplyDao replyDao = new LreplyDao();
	
	public LunchData getLunch(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Lunch lunch = lunchDao.selectById(conn, bno);
			if(lunch == null) {
				throw new LunchNotFoundException();
			}
			Lcontent lcontent = LcontentDao.selectById(conn, bno);
			
			List<Lreply> replyList = replyDao.getRepliesByBno(conn, bno);
			
			if(lcontent == null) {
				throw new LcontentNotFoundException();
			}
			if(increaseHit) {
				lunchDao.increaseHit(conn, bno);
			}
			return new LunchData(lunch, lcontent, replyList);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
