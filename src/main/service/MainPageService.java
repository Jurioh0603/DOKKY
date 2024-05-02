package main.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.connection.ConnectionProvider;

public class MainPageService {

	private BoardDao boardDao = new BoardDao();
	
	public Map<String, List<Board>> getBoardList() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Map<String, List<Board>> boardList = new HashMap<>();
			
			List<Board> qna = boardDao.select(conn, "qna", 1, 5);
			List<Board> community = boardDao.select(conn, "community", 1, 5);
			List<Board> study = boardDao.select(conn, "study", 1, 5);
			List<Board> lunch = boardDao.select(conn, "lunch", 1, 5);
			
			boardList.put("qna", qna);
			boardList.put("community", community);
			boardList.put("study", study);
			boardList.put("lunch", lunch);
			
			return boardList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
