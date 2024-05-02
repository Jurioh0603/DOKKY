
package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import lunch.dao.ImageDao;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;
import lunch.model.Image;
import lunch.model.Lcontent;
import lunch.model.Lunch;

public class WriteLunchService {
	
	private LunchDao LunchDao = new LunchDao();
	private LcontentDao LcontentDao = new LcontentDao();
	private ImageDao ImageDao = new ImageDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Lunch lunch = toLunch(req);
			Lunch savedLunch = LunchDao.insert(conn, lunch);
			if(savedLunch == null) {
				throw new RuntimeException("fail to insert lunch");
			}
			Lcontent lcontent = new Lcontent(savedLunch.getBno(),
					req.getContent(),req.getFileRealName());
			Lcontent savedContent = LcontentDao.insert(conn, lcontent);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert lunch_content");
			}
			Image image = new Image(req.getFileName(), req.getFileRealName(), 
					savedLunch.getBno());
			Image savedImage = ImageDao.insert(conn, image);
			if(savedImage == null) {
				throw new RuntimeException("fail to insert lunch_Image");
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
		return new Lunch(0, req.getMemId(), req.getTitle(), now, 0);
	}
}
