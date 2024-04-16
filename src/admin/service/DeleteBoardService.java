package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.model.BoardDao;
import jdbc.connection.ConnectionProvider;

public class DeleteBoardService {

	private BoardDao boardDao = new BoardDao();
	
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
}
