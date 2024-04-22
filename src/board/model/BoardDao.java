package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class BoardDao {

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
	
	private Board convertBoard(ResultSet rs) throws SQLException {
		return new Board(rs.getInt(1),
				rs.getString(2),
				rs.getDate(3), 
				rs.getInt(4),
				rs.getString(5));
	}
	
	public void delete(Connection conn, String board, String bno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String bbsSql = "delete from " + board + " where bno=?";
			pstmt = conn.prepareStatement(bbsSql);
			pstmt.setInt(1, Integer.parseInt(bno));
			pstmt.executeUpdate();
			
			String contentSql = "delete from " + board.charAt(0) + "content where bno=?";
			pstmt = conn.prepareStatement(contentSql);
			pstmt.setInt(1, Integer.parseInt(bno));
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
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
}
