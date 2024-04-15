package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.Member;

public class MemberDao {
		
	public Member selectById(Connection conn, String memId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from member where memId = ?");
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("memid"), rs.getString("mempw"), rs.getString("name"), rs.getString("email"),rs.getInt("grade"), toDate(rs.getTimestamp("regdate")));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
}
