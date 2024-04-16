package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import qna.model.Qna;
import member.model.Member;
import jdbc.JdbcUtil;
import oracle.sql.DATE;

public class QnaDao {
    
    public Qna insert(Connection conn, Qna qna) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // SQL ������ �غ��մϴ�.
            String sql = "INSERT INTO qna (bno, memid, title, regdate, hit) VALUES (seq_qna.nextval, ?, ?, ?, 0)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, qna.getMemId());
            pstmt.setString(2, qna.getTitle());
            pstmt.setDate(3, new java.sql.Date(qna.getRegDate().getTime()));

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                String sqlGetLastBno = "SELECT seq_qna.CURRVAL FROM DUAL";
                pstmt = conn.prepareStatement(sqlGetLastBno);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    int newBno = rs.getInt(1);
                    return new Qna(newBno, qna.getMemId(), qna.getTitle(), qna.getRegDate(), 0);
                    //memId�� Member ��ü�� �޾ұ� ������, ��ü�� ��ȯ�� ���� Member ��ü�� ��ȯ�ϴ� ��
                }
            }
        } catch (SQLException e) {
            // SQLException ó��
            e.printStackTrace();
            throw e; 
        } finally {
            // �ڿ��� �����մϴ�.
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            
        }
        
        return null; 
    }
}
