package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jdbc.connection.ConnectionProvider;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;
import lunch.model.Lcontent;
import lunch.model.Lunch;
import lunch.dao.ReplyDAO;
import lunch.model.ReplyDTO;

public class ReadLunchService {
	
	private LunchDao lunchDao = new LunchDao();
	private LcontentDao LcontentDao = new LcontentDao();
	private ReplyDAO replyDao = null;
	
	public LunchData getLunch(int bno, boolean increaseHit) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Lunch lunch = lunchDao.selectById(conn, bno);
			if(lunch == null) {
				throw new LunchNotFoundException();
			}
			Lcontent lcontent = LcontentDao.selectById(conn, bno);
			
			replyDao = new ReplyDAO();
			
			List<ReplyDTO> replyList = replyDao.getRepliesByBno(bno);
			
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
