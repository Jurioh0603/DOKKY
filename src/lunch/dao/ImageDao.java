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
