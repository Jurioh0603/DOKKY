package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.model.Board;
import board.model.BoardDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class PostListService {

	private BoardDao boardDao = new BoardDao();
	private int size = 10;
	
	public void deleteBoard(String board, String deleteList) {
		Connection conn = null;
		try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
			String[] deleteBno = deleteList.split(",");
			for(String bno : deleteBno) {
				boardDao.delete(conn, board, bno);
			}
			
			conn.commit();
		} catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	public PostPage getBoardPage(int pageNum, String board, String userId) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = boardDao.selectSearchCountById(conn, board, userId);
			List<Board> postList = boardDao.selectSearchById(conn, board, (pageNum - 1) * size + 1, pageNum * size, userId);
			return new PostPage(total, pageNum, size, postList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PostPage searchBoardPage(int pageNum, String board, String search, String userId) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = boardDao.selectSearchCountById(conn, board, search, userId);
			int totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			if(totalPages < pageNum)
				pageNum = totalPages;
			List<Board> postList = boardDao.selectSearchById(conn, board, search, userId, (pageNum - 1) * size + 1, pageNum * size);
			return new PostPage(total, pageNum, size, postList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
