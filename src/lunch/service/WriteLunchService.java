package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import lunch.model.Lcontent;
import lunch.model.LcontentDao;
import lunch.model.Lunch;
import lunch.model.LunchDao;

public class WriteLunchService {
	
	private LunchDao lunchDao = new LunchDao();
	private LcontentDao lcontentDao = new LcontentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Lunch lunch = toLunch(req);
			Lunch savedLunch = lunchDao.insert(conn, lunch);
			if(savedLunch == null) {
				throw new RuntimeException("fail to insert lunch");
			}
			Lcontent lcontent = new Lcontent(savedLunch.getBno(),
					req.getContent());
			Lcontent savedContent = lcontentDao.insert(conn, lcontent);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert lunch_content");
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
	
	private Lunch toLunch(WriteRequest req) {
		Date now = new Date();
		return new Lunch(0, req.getTitle(), (java.sql.Date) now, 0, req.getMemId());
	}
}