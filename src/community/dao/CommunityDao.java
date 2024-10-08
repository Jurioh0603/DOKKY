package community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import community.model.Community;
import community.service.CommunityList;

public class CommunityDao {

	//ResultSet에 담긴 정보를 Community 객체로 변환
	private Community convertCommunity(ResultSet rs) throws SQLException {
		return new Community(rs.getInt("bno"), 
				rs.getString("memId"), 
				rs.getString("title"), 
				rs.getDate("regDate"),
				rs.getInt("hit"));
	}

	//게시글 정보 조회
	public Community selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM community WHERE bno = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Community community = null;
			if (rs.next()) {
				community = convertCommunity(rs);
			}
			return community;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//게시글 조회수 증가
	public void increaseHit(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("UPDATE community SET hit = hit+1 WHERE bno = ?");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	//글 작성(삽입)
	public Community insert(Connection conn, Community community) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO community (bno, memid, title, regdate, hit) VALUES (community_seq.nextval, ?, ?, ?, 0)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, community.getMemId());
			pstmt.setString(2, community.getTitle());
			pstmt.setDate(3, new java.sql.Date(community.getRegDate().getTime()));

			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				String sqlGetLastBno = "SELECT community_seq.CURRVAL FROM DUAL";
				pstmt = conn.prepareStatement(sqlGetLastBno);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int newBno = rs.getInt(1);
					return new Community(newBno, community.getMemId(), community.getTitle(), community.getRegDate(), 0);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}

	//글 수정
	public int update(Connection conn, int no, String title) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("update community set title = ? where bno = ?");
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	//글 삭제
	public int delete(Connection conn, int communityNo) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("delete from community where bno = ?");
			pstmt.setInt(1, communityNo);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	//전체 게시글 수
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from community");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	//전체 게시글 목록 조회 with 페이지네이션, 댓글 수를 알기 위해 댓글 테이블과 join
	public List<CommunityList> select(Connection conn, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select C.*, Rownum Rnum from (SELECT A.bno, A.title, A.regdate, A.hit, A.memid, NVL(B.cnt, 0) as replyCount "
					+ "FROM community A LEFT OUTER JOIN (SELECT bno, COUNT(rno) AS cnt FROM creply GROUP BY bno) B "
					+ "ON A.bno = B.bno GROUP BY A.bno, A.title, A.regdate, A.hit, A.memid, B.cnt order by bno desc) C) "
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			List<CommunityList> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertCommunityList(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//검색한 게시글 수
	public int selectSearchCount(Connection conn, String search) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from community A join ccontent B "
					+ "on A.bno = B.bno and (title like '%' || ? || '%' or content like '%' || ? || '%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//검색과 정렬을 한 게시글 목록 조회 with 페이지네이션
	//글 내용과 댓글 수를 알아야 하기 때문에 3개의 테이블을 join
	public List<CommunityList> selectSearch(Connection conn, String search, String sort, int startRow, int endRow)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select D.*, Rownum Rnum "
					+ "from (SELECT A.bno, A.title, A.regdate, A.hit, A.memid, NVL(B.cnt, 0) as replyCount, C.content "
					+ "FROM community A LEFT OUTER JOIN (SELECT bno, COUNT(rno) AS cnt FROM creply GROUP BY bno) B "
					+ "ON A.bno = B.bno JOIN ccontent C ON A.bno = C.bno and "
					+ "(A.title like '%' || ? || '%' or C.content like '%' || ? || '%') "
					+ "GROUP BY A.bno, A.title, A.regdate, A.hit, A.memid, B.cnt, C.content " + "order by " + sort
					+ " desc, bno desc) D) " + "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			List<CommunityList> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertCommunityList(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//ResultSet에 담긴 정보를 CommunityList 객체로 변환
	private CommunityList convertCommunityList(ResultSet rs) throws SQLException {
		return new CommunityList(rs.getInt("bno"), 
				rs.getString("memId"), 
				rs.getString("title"), 
				rs.getDate("regDate"),
				rs.getInt("hit"), 
				rs.getInt("replyCount"));
	}

}
