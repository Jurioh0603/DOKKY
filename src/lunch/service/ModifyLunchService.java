package lunch.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import lunch.dao.ImageDao;
import lunch.dao.LcontentDao;
import lunch.dao.LunchDao;
import lunch.model.Image;
import lunch.model.Lunch;

public class ModifyLunchService {
	
	private LunchDao lunchDao = new LunchDao();
	private LcontentDao contentDao = new LcontentDao();
	private ImageDao imageDao = new ImageDao();
	
	public void modify(ModifyRequest modReq, Image image) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Lunch lunch = lunchDao.selectById(conn,
					modReq.getLunchNumber());
			if (lunch == null) {
				throw new LunchNotFoundException();
			}
			if(!canModify(modReq.getMemId(),lunch)) {
				throw new PermissionDeniedException();
			}
			
			int bno = modReq.getLunchNumber();
			
			imageDao.delete(conn, bno);
			imageDao.insert(conn, image);
			lunchDao.update(conn, 
					modReq.getLunchNumber(), modReq.getTitle());
			contentDao.update(conn,
					modReq.getLunchNumber(), modReq.getContent());
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String modfyingMemId, Lunch lunch) {
		return lunch.getMemId().equals(modfyingMemId);
	}

}
