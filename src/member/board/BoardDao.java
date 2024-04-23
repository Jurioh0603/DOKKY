package member.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;

public class BoardDao {
	
	public int selectCount(Connection conn, String boardName) throws SQLException{ 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(*) from "+ boardName;
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
	
	public List<Board> selectBoard(Connection conn, String boardName, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from "+ boardName + " where memid = ? and rownum <= 10 order by bno desc";
			pstmt = conn.prepareStatement(sql);
		}
	}
}s 
