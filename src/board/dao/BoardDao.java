package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.Board;
import jdbc.JdbcUtil;

public class BoardDao {

	//전체 게시글 수 조회
	public int selectCount(Connection conn, String board) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from " + board;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//전체 게시글 조회 with 페이지네이션
	public List<Board> select(Connection conn, String board, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select A.*, Rownum Rnum from (select * from " + board + " order by bno desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			List<Board> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertBoard(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//ResultSet에 담긴 정보를 Board 객체로 변환
	private Board convertBoard(ResultSet rs) throws SQLException {
		return new Board(rs.getInt(1),
				rs.getString(2),
				rs.getDate(3), 
				rs.getInt(4),
				rs.getString(5));
	}
	
	//게시글 삭제
	public void delete(Connection conn, String board, String bno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//점메추 게시판은 이미지 테이블을 먼저 삭제
			if(board.equals("lunch")) {
				String imageSql = "delete from image where bno=?";
				pstmt = conn.prepareStatement(imageSql);
				pstmt.setInt(1, Integer.parseInt(bno));
				pstmt.executeUpdate();
			}
			
			//댓글 테이블 삭제
			String replySql = "delete from " + board.charAt(0) + "reply where bno=?";
			pstmt = conn.prepareStatement(replySql);
			pstmt.setInt(1, Integer.parseInt(bno));
			pstmt.executeUpdate();
			
			//게시글 정보 테이블 삭제
			String bbsSql = "delete from " + board + " where bno=?";
			pstmt = conn.prepareStatement(bbsSql);
			pstmt.setInt(1, Integer.parseInt(bno));
			pstmt.executeUpdate();
			
			//게시글 내용 테이블 삭제
			String contentSql = "delete from " + board.charAt(0) + "content where bno=?";
			pstmt = conn.prepareStatement(contentSql);
			pstmt.setInt(1, Integer.parseInt(bno));
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//제목에 검색어를 포함하고 있는 글 갯수 조회
	public int selectSearchCountByTitle(Connection conn, String board, String searchId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from " + board + " where title like '%' || ? || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//제목에 검색어를 포함하고 있는 글 조회 with 페이지네이션
	public List<Board> selectSearchByTitle(Connection conn, String board, int startRow, int endRow, String searchId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select A.*, Rownum Rnum from (select * from " + board + " where title like '%' || ? || '%' order by bno desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			List<Board> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertBoard(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//특정 작성자가 작성한 글 갯수 조회
	public int selectSearchCountById(Connection conn, String board, String searchId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from " + board + " where memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//특정 작성자가 작성한 글 조회 with 페이지네이션
	public List<Board> selectSearchById(Connection conn, String board, int startRow, int endRow, String searchId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select A.*, Rownum Rnum from (select * from " + board + " where memid=? order by bno desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			List<Board> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertBoard(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//(마이페이지)작성한 글 중 검색하여 글 갯수 조회
	public int selectSearchCountById(Connection conn, String board, String search, String userId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from " + board + " where memid=? and title like '%' || ? || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//(마이페이지)작성한 글 중 검색하여 글 조회 with 페이지네이션
	public List<Board> selectSearchById(Connection conn, String board, String search, String userId, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select A.*, Rownum Rnum from (select * from " + board + " where memid=? and title like '%' || ? || '%' order by bno desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, search);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			List<Board> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertBoard(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
