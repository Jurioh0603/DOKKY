package community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import community.model.Community;

public class CommunityDao {
	
	   //게시글 리스트 함수 p.647
	   private Community convertCommunity(ResultSet rs) throws SQLException {
		   return new Community(rs.getInt("bno"),
				   			rs.getString("memId"),
				   			rs.getString("title"),
				   			rs.getDate("regDate"),
				   			rs.getInt("hit"));
				   					
	   }
	
	   //게시판 글읽기(조회) 기능 구현. p655
	   public Community selectById(Connection conn, int no)throws SQLException{
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try {
			   pstmt = conn.prepareStatement(
					   "SELECT * FROM community WHERE bno = ?");
			   pstmt.setInt(1,  no);
			   rs = pstmt.executeQuery();
			   Community community = null;
			   if(rs.next()) {
				   community = convertCommunity(rs); //p648. convertCommunity() 게시글 목록 조회 기능구현에서 생성한 메서드
				   //regdate formatting
				   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				   String regDateStr = dateFormat.format(community.getRegDate());
				   Date regDate = null;
				   try {
					   regDate = dateFormat.parse(regDateStr);
				   } catch (ParseException e) {
					   e.printStackTrace();
				   }
				   community.setRegDate(regDate);
			   }
			   return community;
		   }finally {
			   JdbcUtil.close(rs);
			   JdbcUtil.close(pstmt);
		   }
	   }
   
	   //게시글 조회수 증가
	   public void increaseHit(Connection conn, int no) throws SQLException{
		   try(PreparedStatement pstmt =
				   conn.prepareStatement(
						   "UPDATE community SET hit = hit+1" +
						   "WHERE bno = ?")){
			   pstmt.setInt(1, no);
			   pstmt.executeUpdate();
		   }
	   }
	   
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
	   
	   //게시글 제목 수정 기능 p.665
	   public int update(Connection conn, int no, String title) throws SQLException{
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
	   
	   //게시글 삭제 부분 구현시도
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
		
		public int selectCount(Connection conn) throws SQLException {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select count(*) from community");
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		}
		
		public List<Community> select(Connection conn, int startRow, int endRow) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from (select A.*, Rownum Rnum from (select * from community order by bno desc) A)"
						+ "where Rnum >= ? and Rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				List<Community> result = new ArrayList<>();
				while(rs.next()) {
					result.add(convertCommunity(rs));
				}
				return result;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
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
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
		public List<Community> selectSearch(Connection conn, String search, int startRow, int endRow) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from (select C.*, Rownum Rnum from (select * from community A join ccontent B "
						+ "on A.bno = B.bno and (title like '%' || ? || '%' or content like '%' || ? || '%') order by a.bno desc) C) "
						+ "where Rnum >= ? and Rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				rs = pstmt.executeQuery();
				List<Community> result = new ArrayList<>();
				while(rs.next()) {
					result.add(convertCommunity(rs));
				}
				return result;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
}
