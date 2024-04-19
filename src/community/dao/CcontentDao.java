package community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import community.model.Ccontent;

public class CcontentDao {
	
	//p.656 조회관련메서드 selectById()
	public Ccontent selectById(Connection conn, int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM Ccontent WHERE bno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Ccontent scontent = null;
			if(rs.next()) {
				scontent = new Ccontent(
						rs.getInt("bno"),rs.getString("content"));
			}
			return scontent;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Ccontent insert(Connection conn, Ccontent ccontent) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO ccontent (bno, content) VALUES (?, ?)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, ccontent.getBno());
            pstmt.setString(2, ccontent.getContent());

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                return ccontent;
            } else {
            	 return null; 
            }
        } finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
            
    	}
	}
	
	
}
