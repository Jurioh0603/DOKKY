
package lunch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import lunch.model.Lcontent;

public class LcontentDao {
	
	//게시글 쓰기
	public Lcontent insert(Connection conn, Lcontent lcontent) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO lcontent (bno, content) VALUES (?, ?)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, lcontent.getBno());
            pstmt.setString(2, lcontent.getContent());

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                return lcontent;
            } else {
            	 return null; 
            }
        } finally {
            JdbcUtil.close(pstmt);
            
        	}
       }
	
	//p.656 조회관련메서드 selectById()
	public Lcontent selectById(Connection conn, int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					  "select * "
					+ "from (select L.*, I.filerealname "
					+ "            from Lcontent L join image I "
					+ "            on L.bno=I.bno) "
					+ "where bno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Lcontent lcontent = null;
			if(rs.next()) {
				lcontent = new Lcontent(
						rs.getInt("bno"),rs.getString("content"),rs.getString("filerealname"));
			}
			return lcontent;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//update메서드 p.665
	public int update(Connection conn, int no, String content) throws SQLException {
		try (PreparedStatement pstmt =
				conn.prepareStatement(
					"update Lcontent set content = ? "+ 
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
						"delete from Lcontent " +  
						"where bno = ?")) {
			pstmt.setInt(1, scontentNo);
			return pstmt.executeUpdate();
		}
	}
	
	
}
