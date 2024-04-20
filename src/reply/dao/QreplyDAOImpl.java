package reply.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import jdbc.connection.ConnectionProvider;
import reply.model.Qreply;


public class QreplyDAOImpl implements QreplyDAO {

    @Override
    public void insertReply(Qreply reply) {
        String sql = "INSERT INTO QREFLY (RNO, BNO, MEMID, RCONTENT, PARENT_RNO) VALUES (QREFLY_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, reply.getBno());
            pstmt.setString(2, reply.getMemid());
            pstmt.setString(3, reply.getRcontent());
            pstmt.setLong(4, reply.getParentRno());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Qreply> selectRepliesByBno(Long bno) {
        List<Qreply> replies = new ArrayList<>();
        String sql = "SELECT * FROM QREFLY WHERE BNO = ? ORDER BY created_at DESC";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, bno);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Qreply reply = new Qreply();
                    reply.setRno(rs.getLong("RNO"));
                    reply.setBno(rs.getLong("BNO"));
                    reply.setMemid(rs.getString("MEMID"));
                    reply.setRcontent(rs.getString("RCONTENT"));
                    reply.setParentRno(rs.getLong("PARENT_RNO"));
                    reply.setCreatedAt(LocalDateTime.parse("created_at"));
                    replies.add(reply);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return replies;
    }

    @Override
    public void deleteReply(Long rno) {
        String sql = "DELETE FROM QREFLY WHERE RNO = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, rno);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}