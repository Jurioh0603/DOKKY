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
			Ccontent ccontent = null;
			if(rs.next()) {
				ccontent = new Ccontent(
						rs.getInt("bno"),rs.getString("content"));
			}
			return ccontent;
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
	
	//update메서드 p.665
	public int update(Connection conn, int no, String content) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update Ccontent set content = ? where bno = ?");
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//delete메서드 구현시도
	public int delete(Connection conn, int ccontentNo) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from Ccontent where bno = ?");
			pstmt.setInt(1, ccontentNo);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}
