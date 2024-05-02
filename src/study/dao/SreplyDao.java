package study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import study.model.Sreply;

public class SreplyDao {

	// 댓글 추가 메서드
    public void addReply(Connection conn, Sreply replyDTO) throws SQLException {
    	
        String sql = "INSERT INTO SREPLY (RNO, BNO, MEMID, RCONTENT, RDATE) VALUES (sreply_seq.nextval, ?, ?, ?, SYSDATE)";
        PreparedStatement pstmt = null;
        try {
        	pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, replyDTO.getBno());
            pstmt.setString(2, replyDTO.getMemid());
            pstmt.setString(3, replyDTO.getRcontent());
            pstmt.executeUpdate();
        } finally {
        	JdbcUtil.close(pstmt);
        }
    }

    // 특정 글에 대한 댓글 조회 메서드
    public List<Sreply> getRepliesByBno(Connection conn, int bno) throws SQLException {
        String sql = "SELECT * FROM SREPLY WHERE BNO = ?";
        List<Sreply> replies = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
        	pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bno);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Sreply replyDTO = new Sreply();
                    replyDTO.setRno(rs.getInt("RNO"));
                    replyDTO.setBno(rs.getInt("BNO"));
                    replyDTO.setMemid(rs.getString("MEMID"));
                    replyDTO.setRcontent(rs.getString("RCONTENT"));
                    replyDTO.setDate(rs.getDate("RDATE"));
                    replies.add(replyDTO);
                }
            }
        } finally {
        	JdbcUtil.close(pstmt);
        }
        return replies;
    }
    

    // 댓글 수정 메서드
    public void updateReply(Connection conn, int rno, String rcontent) throws SQLException {
        String sql = "UPDATE SREPLY SET RCONTENT = ? WHERE RNO = ?";
        PreparedStatement pstmt = null;
        try {
        	pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rcontent);
            pstmt.setInt(2, rno);
            pstmt.executeUpdate();
        } finally {
        	JdbcUtil.close(pstmt);
        }
    }

    // 댓글 삭제 메서드
    public void deleteReply(Connection conn, int rno) throws SQLException {
        String sql = "DELETE FROM SREPLY WHERE RNO = ?";
        PreparedStatement pstmt = null;
        try {
        	pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rno);
            pstmt.executeUpdate();
        } finally {
        	JdbcUtil.close(pstmt);
        }
    }
    
    // 게시글 삭제시 사용하는 메서드
    public void delete(Connection conn, int bno) throws SQLException {
        String sql = "DELETE FROM SREPLY WHERE BNO = ?";
        PreparedStatement pstmt = null;
        try {
        	pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bno);
            pstmt.executeUpdate();
        } finally {
        	JdbcUtil.close(pstmt);
        }
    }
}
