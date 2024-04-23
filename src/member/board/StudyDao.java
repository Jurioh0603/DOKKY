package member.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;

public class StudyDao {
	
	public int selectCount(Connection conn) throws SQLException{ 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(*) from study";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Study> selectBoard(Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//String sql = "select * from "+ boardName + " where memid = ? and rownum <= 10 order by bno desc";
			//String sql = "select * from study order by bno desc";
			String sql = "select * from (select * from study order by bno desc) where rownum <= ? and rownum > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, size);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			List<Study> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertStudy(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Study convertStudy(ResultSet rs) throws SQLException{
		return new Study(rs.getInt("bno"), new Writer(rs.getString("writer_memid")),rs.getString("title"),toDate(rs.getTimestamp("regdate")),rs.getInt("hit"));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
}
