package qna.dao;

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

public class QcontentDao {
    
    public Qcontent insert(Connection conn, Qcontent qcontent) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // SQL ������ �غ��մϴ�.
            String sql = "INSERT INTO qcontent (bno, content) VALUES (?, ?)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, qcontent.getBno());
            pstmt.setString(2, qcontent.getContent());

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                return qcontent;
            } else {
            	 return null; 
            }
        } finally {
            JdbcUtil.close(pstmt);
            
        }
    }
}
