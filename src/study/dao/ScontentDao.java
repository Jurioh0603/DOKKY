package study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import study.model.Scontent;

public class ScontentDao {
	
	//p.656 조회관련메서드 selectById()
	public Scontent selectById(Connection conn, int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM Scontent WHERE bno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Scontent scontent = null;
			if(rs.next()) {
				scontent = new Scontent(
						rs.getInt("bno"),rs.getString("content"));
			}
			return scontent;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//update메서드 p.665
	public int update(Connection conn, int no, String content) throws SQLException {
		try (PreparedStatement pstmt =
				conn.prepareStatement(
					"update Scontent set content = ? "+ 
					"where bno = ?")){
		pstmt.setString(1, content);
		pstmt.setInt(2, no);
		return pstmt.executeUpdate();
		}
	}
	
	//delete메서드 구현시도
	public int delete(Connection conn, int scontentNo) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from Scontent " +  
						"where bno = ?")) {
			pstmt.setInt(1, scontentNo);
			return pstmt.executeUpdate();
		}
	}
	
	
}
