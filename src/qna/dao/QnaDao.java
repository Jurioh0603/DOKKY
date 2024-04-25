package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import qna.model.Qna;
import member.model.Member;
import jdbc.JdbcUtil;
import oracle.sql.DATE;

public class QnaDao {
    
    public Qna insert(Connection conn, Qna qna) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO qna (bno, memid, title, regdate, hit) VALUES (qna_seq.nextval, ?, ?, ?, 0)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, qna.getMemId());
            pstmt.setString(2, qna.getTitle());
            pstmt.setDate(3, new java.sql.Date(qna.getRegDate().getTime()));

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                String sqlGetLastBno = "SELECT qna_seq.CURRVAL FROM DUAL";
                pstmt = conn.prepareStatement(sqlGetLastBno);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    int newBno = rs.getInt(1);
                    return new Qna(newBno, qna.getMemId(), qna.getTitle(), qna.getRegDate(), 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            
        }
        
        return null; 
    }

    public List<Qna> select(Connection conn, int startRow, int endRow) throws SQLException {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		String sql = "SELECT A.*, Rownum Rnum from (SELECT * FROM QNA ORDER BY REGDATE DESC) A ) "
    				+ "WHERE Rnum >= ? and Rnum <= ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, startRow);
    		pstmt.setInt(2, endRow);
    		rs = pstmt.executeQuery();
    		List<Qna> result = new ArrayList<>();
    		while(rs.next()) {
    			result.add(convertQna(rs));
    		}
    		return result;
    	} finally {
    		JdbcUtil.close(rs);
    		JdbcUtil.close(pstmt);
    	}
    }
    
    //글 목록
    public int selectCount(Connection conn) throws SQLException {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    			String sql = "SELECT COUNT(*) FROM QNA";
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			if(rs.next()) {
    				return rs.getInt(1);
    			}
    			return 0;
    	} finally {
    		JdbcUtil.close(rs);
    		JdbcUtil.close(pstmt);
    	}
    }
    
    //게시글 리스트 함수	
    private Qna convertQna(ResultSet rs) throws SQLException {
    		return new Qna(rs.getInt("bno"),
    						rs.getString("memid"),
    						rs.getString("title"),
    						rs.getDate("regDate"),
    						rs.getInt("hit"));
    }
    
    //게시글 조회
    public Qna selectById(Connection conn, int no) throws SQLException {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = conn.prepareStatement("SELECT * FROM QNA WHERE bno = ?");
    		pstmt.setInt(1, no);
    		rs = pstmt.executeQuery();
    		Qna qna = null;
    		if(rs.next()) {
    			qna = convertQna(rs);
    		}
    		return qna;
    	}finally {
    		JdbcUtil.close(rs);
    		JdbcUtil.close(pstmt);
    	}
    }
    
    public void increaseHit(Connection conn, int no) throws SQLException {
    	try (PreparedStatement pstmt = conn.prepareStatement("UPDATE qna SET hit = hit + 1" + "WHERE bno = ?")) {
    		pstmt.setInt(1, no);
    		pstmt.executeUpdate();
    	}
    }
    
    public int update(Connection conn, int no, String title) throws SQLException {
    	try (PreparedStatement pstmt = conn.prepareStatement("UPDATE qna SET title = ? " + "WHERE bno = ?")) {
    		pstmt.setString(1, title);
    		pstmt.setInt(2, no);
    		return pstmt.executeUpdate();
    	}
    }
    
    public int delete(Connection conn, int qnaNo) throws SQLException {
    	try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM qna " + "WHERE bno = ? ")) {
    		pstmt.setInt(1, qnaNo);
    		return pstmt.executeUpdate();
    	}
    }
}
