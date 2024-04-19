package lunch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import qna.model.Qcontent;
import jdbc.JdbcUtil;
import oracle.sql.DATE;

public class LcontentDao {
    
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
}