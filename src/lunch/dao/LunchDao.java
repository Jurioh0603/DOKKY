package lunch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import lunch.model.Lunch;
import lunch.service.ListRequest;

public class LunchDao {
    
	 private ListRequest convertListRequest(ResultSet rs) throws SQLException {
		   return new ListRequest(rs.getInt(1),
				   rs.getString(2),
				   rs.getDate(3),
				   rs.getInt(4),
				   rs.getString(5),
				   rs.getString("filerealname"),
				   rs.getInt("replyCount"));
	   }

	private Lunch convertLunch(ResultSet rs) throws SQLException {
		   return new Lunch(rs.getInt("bno"),
				   			rs.getString("memId"),
				   			rs.getString("title"),
				   			rs.getDate("regDate"),
				   			rs.getInt("hit"));
				   					
	}
	
	 //게시판 글읽기(조회) 기능 구현. p655
	   public Lunch selectById(Connection conn, int no)throws SQLException{
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try {
			   pstmt = conn.prepareStatement(
					   "SELECT * FROM LUNCH WHERE bno = ?");
			   pstmt.setInt(1,  no);
			   rs = pstmt.executeQuery();
			   Lunch lunch = null;
			   if(rs.next()) {
				   lunch = convertLunch(rs); //p648. convertStudy() 게시글 목록 조회 기능구현에서 생성한 메서드
			   }
			   return lunch;
		   }finally {
			   JdbcUtil.close(rs);
			   JdbcUtil.close(pstmt);
		   }
	   }
	
	   //게시글 조회수 증가
	   public void increaseHit(Connection conn, int no)throws SQLException{
		   PreparedStatement pstmt = null;
		   try {
			   pstmt = conn.prepareStatement("UPDATE lunch SET hit = hit+1 WHERE bno = ?");
			   pstmt.setInt(1, no);
			   pstmt.executeUpdate();
		   } finally {
			   JdbcUtil.close(pstmt);
		   }
	   }
	   
	 //게시글 읽기
		public Lunch insert(Connection conn, Lunch lunch) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            String sql = "INSERT INTO lunch (bno, memid, title, regdate, hit) VALUES (LUNCH_SEQ.nextval, ?, ?, ?, 0)";
	            
	            pstmt = conn.prepareStatement(sql);

	            pstmt.setString(1, lunch.getMemId());
	            pstmt.setString(2, lunch.getTitle());
	            pstmt.setDate(3, new java.sql.Date(lunch.getRegDate().getTime()));

	            int insertedCount = pstmt.executeUpdate();
	            
	            if (insertedCount > 0) {
	                String sqlGetLastBno = "SELECT LUNCH_SEQ.CURRVAL FROM DUAL";
	                pstmt = conn.prepareStatement(sqlGetLastBno);
	                rs = pstmt.executeQuery();
	                if (rs.next()) {
	                    int newBno = rs.getInt(1);
	                    return new Lunch(newBno, lunch.getMemId(), lunch.getTitle(), lunch.getRegDate(), 0);
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
   public int update(Connection conn, int no, String title)throws SQLException{
	   PreparedStatement pstmt = null;
	   
	   try {
		   pstmt = conn.prepareStatement("update lunch set title = ? where bno = ?");
		   pstmt.setString(1, title);
		   pstmt.setInt(2, no);
		   return pstmt.executeUpdate();
	   } finally {
		   JdbcUtil.close(pstmt);
       }
   }
	   
    //게시글 삭제 부분 구현시도
	public int delete(Connection conn, int lunchNo) throws SQLException {
		PreparedStatement pstmt = null;
		   
		try {
			String lunchSql = "delete from lunch where bno = ?";
			pstmt = conn.prepareStatement(lunchSql);
			pstmt.setInt(1, lunchNo);
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
			rs = stmt.executeQuery("select count(*) from lunch");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<ListRequest> select(Connection conn, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select D.*, Rownum Rnum "
					+ "from (SELECT A.bno, A.title, A.regdate, A.hit, A.memid, NVL(B.cnt, 0) as replyCount, I.filerealname "
					+ "FROM lunch A LEFT OUTER JOIN (SELECT bno, COUNT(rno) AS cnt FROM lreply GROUP BY bno) B "
					+ "ON A.bno = B.bno JOIN image I on A.bno = I.bno "
					+ "GROUP BY A.bno, A.title, A.regdate, A.hit, A.memid, B.cnt, I.filerealname "
					+ "order by bno desc) D) "
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			List<ListRequest> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertListRequest(rs));
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
			String sql = "select count(*) from lunch A join lcontent B "
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
		   
	public List<ListRequest> selectSearch(Connection conn, String search, String sort, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from (select D.*, Rownum Rnum "
					+ "from (SELECT A.bno, A.title, A.regdate, A.hit, A.memid, NVL(B.cnt, 0) as replyCount, C.content, I.filerealname "
					+ "FROM lunch A LEFT OUTER JOIN (SELECT bno, COUNT(rno) AS cnt FROM lreply GROUP BY bno) B "
					+ "ON A.bno = B.bno JOIN lcontent C ON A.bno = C.bno JOIN image I on A.bno = I.bno and "
					+ "(A.title like '%' || ? || '%' or C.content like '%' || ? || '%') "
					+ "GROUP BY A.bno, A.title, A.regdate, A.hit, A.memid, B.cnt, C.content, I.filerealname "
					+ "order by " + sort +" desc, A.bno desc) D) "
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			List<ListRequest> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertListRequest(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
}
	
  