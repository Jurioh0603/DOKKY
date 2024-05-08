package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class PostListService {

	private BoardDao boardDao = new BoardDao();
	private int size = 10;
	
	//게시글 삭제
	public void deleteBoard(String board, String deleteList) {
		Connection conn = null;
		try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            
			//deleteList: 삭제할 게시글의 번호를 담은 문자열, 각 번호는 ,로 구분되어 있음
			//String 배열로 변환
			String[] deleteBno = deleteList.split(",");
			//배열의 요소(게시글 번호)를 하나씩 가져와 삭제
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
	
	//전체 게시글 목록 조회
	public PostPage getBoardPage(int pageNum, String board, String userId) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = boardDao.selectSearchCountById(conn, board, userId);
			List<Board> postList = boardDao.selectSearchById(conn, board, (pageNum - 1) * size + 1, pageNum * size, userId);
			return new PostPage(total, pageNum, size, postList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//검색한 게시글 목록 조회
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
