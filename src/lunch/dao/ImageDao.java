package lunch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import lunch.model.Image;

public class ImageDao {

	public Image insert(Connection conn, Image image) throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            String sql = "INSERT INTO Image (filename, filerealname, bno) VALUES (?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, image.getFileName());
            pstmt.setString(2, image.getFileRealName());
            pstmt.setInt(3, image.getBno());

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                return image;
            } else {
            	 return null; 
            }
        } finally {
            JdbcUtil.close(pstmt);
            
        	}
       }
	
	//p.656 조회관련메서드 selectById()
	public Image selectById(Connection conn, int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM image WHERE bno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Image image = null;
			if(rs.next()) {
				image = new Image(
						rs.getString("filename"),rs.getString("filerealname"),rs.getInt("bno"));
			}
			return image;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//update메서드 p.665
	public int update(Connection conn, String filename, String filerealname, int no) throws SQLException {
		try (PreparedStatement pstmt =
				conn.prepareStatement(
					"update image set (filename,filerealname) = (?, ?)"+ 
					"where bno = ?")){
		pstmt.setString(1, filename);
		pstmt.setString(1, filerealname);
		pstmt.setInt(2, no);
		return pstmt.executeUpdate();
		}
	}
	
	//delete메서드 구현시도
	public int delete(Connection conn, int imageNo) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from image " +  
						"where bno = ?")) {
			pstmt.setInt(1, imageNo);
			return pstmt.executeUpdate();
		}
	}
}
