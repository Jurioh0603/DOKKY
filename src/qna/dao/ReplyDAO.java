package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import member.model.Member;
import qna.model.ReplyDTO;

public class ReplyDAO {
    private Connection conn;

    // 생성자에서 Connection 초기화
    public ReplyDAO() throws SQLException {
        try {
            conn = ConnectionProvider.getConnection();
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
            throw e;
        }
    }
    // 댓글 추가 메서드
    public void addReply(ReplyDTO replyDTO) throws SQLException {
    	
        String sql = "INSERT INTO QREPLY (RNO, BNO, MEMID, RCONTENT, RDATE) VALUES (qreply_seq.nextval, ?, ?, ?, SYSDATE)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, replyDTO.getBno());
            pstmt.setString(2, replyDTO.getMemid());
            pstmt.setString(3, replyDTO.getRcontent());
            pstmt.executeUpdate();
            
            
        }
    }

    // 특정 글에 대한 댓글 조회 메서드
    public List<ReplyDTO> getRepliesByBno(int bno) throws SQLException {
        String sql = "SELECT * FROM QREPLY WHERE BNO = ?";
        List<ReplyDTO> replies = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bno);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ReplyDTO replyDTO = new ReplyDTO();
                    replyDTO.setRno(rs.getInt("RNO"));
                    replyDTO.setBno(rs.getInt("BNO"));
                    replyDTO.setMemid(rs.getString("MEMID"));
                    replyDTO.setRcontent(rs.getString("RCONTENT"));
                    replyDTO.setDate(rs.getDate("RDATE"));
                    replies.add(replyDTO);
                }
            }
        }
        return replies;
    }
    

    // 댓글 수정 메서드
    public void updateReply(ReplyDTO replyDTO) throws SQLException {
        String sql = "UPDATE QREPLY SET RCONTENT = ? WHERE RNO = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, replyDTO.getRcontent());
            pstmt.setInt(2, replyDTO.getRno());
            pstmt.executeUpdate();
        }
    }

    // 댓글 삭제 메서드
    public void deleteReply(int rno) throws SQLException {
        String sql = "DELETE FROM QREPLY WHERE RNO = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rno);
            pstmt.executeUpdate();
        }
    }
}
