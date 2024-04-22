package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn, String memid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member where memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memid);
			rs = pstmt.executeQuery();
			Member member = null;
			if(rs.next()) {
				member = new Member(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getDate(6));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public String findId(Connection conn, String name, String email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select memid from member where name=? and email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			String memid = null;
			if(rs.next()) {
				memid = rs.getString(1);
			}
			return memid;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Member findMember(Connection conn, String name, String id, String email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member where name=? and memid=? and email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();
			Member member = null;
			if(rs.next()) {
				member = new Member(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getDate(6));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void updatePwd(Connection conn, String mempw, String memid) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "update member set mempw=? where memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mempw);
			pstmt.setString(2, memid);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from member";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<Member> select(Connection conn, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select A.*, Rownum Rnum from (select * from member order by regdate desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			List<Member> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertMember(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Member convertMember(ResultSet rs) throws SQLException {
		return new Member(rs.getString(1),
				rs.getString(2),
				rs.getString(3), 
				rs.getString(4),
				rs.getInt(5),
				rs.getDate(6));
	}
	
	public void updateGrade(Connection conn, String id, int grade) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "update member set grade=? where memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grade);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectSearchCount(Connection conn, String searchId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from member where memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
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
	
	public List<Member> selectSearch(Connection conn, int startRow, int endRow, String searchId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select A.*, Rownum Rnum from (select * from member where memid=? order by regdate desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			List<Member> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertMember(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
    public void joinMember(Connection conn, Member mem) throws SQLException {
    	PreparedStatement pstmt = null;
    	try{
    		String sql = "insert into member values (?,?,?,?,?,?)";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, mem.getMemid());
    		pstmt.setString(2, mem.getMempw());
    		pstmt.setString(3, mem.getName());
    		pstmt.setString(4, mem.getEmail());
    		pstmt.setInt(5, mem.getGrade());
    		pstmt.setTimestamp(6, new Timestamp(mem.getRegdate().getTime()));
    		pstmt.executeUpdate();
    	}finally {
    		JdbcUtil.close(pstmt);
    	}
    }
    
    public void updatePwd(Connection conn, Member member) throws SQLException{
    	PreparedStatement pstmt = null;
    	try{
    		String sql = "update member set mempw = ? where memid = ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, member.getMempw());
    		pstmt.setString(2, member.getMemid());
    		pstmt.executeUpdate();
    	}finally {
    		JdbcUtil.close(pstmt);
    	}
    }
}
