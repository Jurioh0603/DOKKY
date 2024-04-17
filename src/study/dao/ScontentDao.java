package study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import study.model.Scontent;

public class ScontentDao {
	
	/*public Scontent insert(Connection conn, Scontent scontent) throws SQLException{
        PreparedStatement pstmt = null;
        try {
        	pstmt = conn.prepareStatement(
        			"INSERT INTO scontent (bno, content) VALUES(?,?)"
        			);
        	pstmt.setInt(1,  scontent.getBno());
        	pstmt.setString(2, scontent.getContent());
        	
        	int insertedCount = pstmt.executeUpdate();
        	
        	if(insertedCount > 0) {
        		return scontent;
        	}else {
        		return null;
        	}
        } finally {
        	JdbcUtil.close(pstmt);
        }
        
       }*/
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
	
	
}
