package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.model.Board;
import board.model.BoardDao;
import jdbc.connection.ConnectionProvider;

public class BoardListService {

	private BoardDao boardDao = new BoardDao();
	private int size = 10;
	
	public void deleteBoard(String board, String deleteList) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			String[] deleteBno = deleteList.split(",");
			for(String bno : deleteBno) {
				boardDao.delete(conn, board, bno);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public BoardPage getBoardPage(int pageNum, String board) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = boardDao.selectCount(conn, board);
			List<Board> boardList = boardDao.select(conn, board, (pageNum - 1) * size + 1, pageNum * size);
			return new BoardPage(total, pageNum, size, boardList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public BoardPage searchBoardPage(int pageNum, String board, String searchId, String searchItem) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			if(searchItem.equals("writer")) {
				int total = boardDao.selectSearchCountById(conn, board, searchId);
				int totalPages = total / size;
				if(total % size > 0) {
					totalPages++;
				}
				if(totalPages < pageNum)
					pageNum = totalPages;
				List<Board> boardList = boardDao.selectSearchById(conn, board, (pageNum - 1) * size + 1, pageNum * size, searchId);
				return new BoardPage(total, pageNum, size, boardList);
			} else if(searchItem.equals("title")) {
				int total = boardDao.selectSearchCountByTitle(conn, board, searchId);
				int totalPages = total / size;
				if(total % size > 0) {
					totalPages++;
				}
				if(totalPages < pageNum)
					pageNum = totalPages;
				List<Board> boardList = boardDao.selectSearchByTitle(conn, board, (pageNum - 1) * size + 1, pageNum * size, searchId);
				return new BoardPage(total, pageNum, size, boardList);
			} else {
				return null;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
