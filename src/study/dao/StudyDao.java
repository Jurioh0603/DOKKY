package study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import study.model.Study;
import study.service.StudyList;

public class StudyDao {
	
	   //게시글 리스트 함수 p.647
	   private Study convertStudy(ResultSet rs) throws SQLException {
		   return new Study(rs.getInt("bno"),
				   			rs.getString("memId"),
				   			rs.getString("title"),
				   			rs.getDate("regDate"),
				   			rs.getInt("hit"));
	   }
	   
	   //게시판 글읽기(조회) 기능 구현. p655
	   public Study selectById(Connection conn, int no)throws SQLException{
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try {
			   pstmt = conn.prepareStatement(
					   "SELECT * FROM STUDY WHERE bno = ?");
			   pstmt.setInt(1,  no);
			   rs = pstmt.executeQuery();
			   Study study = null;
			   if(rs.next()) {
				   study = convertStudy(rs); //p648. convertStudy() 게시글 목록 조회 기능구현에서 생성한 메서드
			   }
			   return study;
		   }finally {
			   JdbcUtil.close(rs);
			   JdbcUtil.close(pstmt);
		   }
	   }
	   
	   //게시글 조회수 증가
	   public void increaseHit(Connection conn, int no)throws SQLException{
		   PreparedStatement pstmt = null;
		   try {
			   pstmt = conn.prepareStatement("UPDATE study SET hit = hit+1 WHERE bno = ?");
			   pstmt.setInt(1, no);
			   pstmt.executeUpdate();
		   } finally {
			   JdbcUtil.close(pstmt);
		   }
	   }
	
	public Study insert(Connection conn, Study study) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO study (bno, memid, title, regdate, hit) VALUES (study_seq.nextval, ?, ?, ?, 0)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, study.getMemId());
            pstmt.setString(2, study.getTitle());
            pstmt.setDate(3, new java.sql.Date(study.getRegDate().getTime()));

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                String sqlGetLastBno = "SELECT study_seq.CURRVAL FROM DUAL";
                pstmt = conn.prepareStatement(sqlGetLastBno);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    int newBno = rs.getInt(1);
                    return new Study(newBno, study.getMemId(), study.getTitle(), study.getRegDate(), 0);
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
			   pstmt = conn.prepareStatement("update study set title = ? where bno = ?");
			   pstmt.setString(1, title);
			   pstmt.setInt(2, no);
			   return pstmt.executeUpdate();
		   } finally {
			   JdbcUtil.close(pstmt);
	       }
	   }
	   
	   //게시글 삭제 
		public int delete(Connection conn, int studyNo) throws SQLException {
			PreparedStatement pstmt = null;
			   
			try {
				pstmt = conn.prepareStatement("delete from study where bno = ?");
				pstmt.setInt(1, studyNo);
				return pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);
			}
		}
        
	 //글목록
    public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select count(*) from study");
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
		} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
		}
	}
    
	public List<StudyList> select(Connection conn, int startRow, int endRow) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from (select C.*, Rownum Rnum from (SELECT A.bno, A.title, A.regdate, A.hit, A.memid, NVL(B.cnt, 0) as replyCount "
						+ "FROM study A LEFT OUTER JOIN (SELECT bno, COUNT(rno) AS cnt FROM sreply GROUP BY bno) B "
						+ "ON A.bno = B.bno GROUP BY A.bno, A.title, A.regdate, A.hit, A.memid, B.cnt order by bno desc) C) "
						+ "where Rnum >= ? and Rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				List<StudyList> result = new ArrayList<>();
				while(rs.next()) {
					result.add(convertStudyList(rs));
				}
				return result;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
	}
	//검색기능
	public int selectSearchCount(Connection conn, String search) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT COUNT(*) FROM study A JOIN scontent B " 
	        		+ "ON A.bno = B.bno and (title LIKE '%' || ? || '%' OR content LIKE '%' || ? || '%') ";
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
	
	public List<StudyList> selectSearch(Connection conn, String search, String sort, int startRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				String sql = "select * from (select D.*, Rownum Rnum "
						+ "from (SELECT A.bno, A.title, A.regdate, A.hit, A.memid, NVL(B.cnt, 0) as replyCount, C.content "
						+ "FROM study A LEFT OUTER JOIN (SELECT bno, COUNT(rno) AS cnt FROM sreply GROUP BY bno) B "
						+ "ON A.bno = B.bno JOIN scontent C ON A.bno = C.bno and "
						+ "(A.title like '%' || ? || '%' or C.content like '%' || ? || '%') "
						+ "GROUP BY A.bno, A.title, A.regdate, A.hit, A.memid, B.cnt, C.content "
						+ "order by " + sort + " desc, A.bno desc) D) "
						+ "where Rnum >= ? and Rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				rs = pstmt.executeQuery();
				List<StudyList> result = new ArrayList<>();
				while(rs.next()) {
						result.add(convertStudyList(rs));
				}
				return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

    private StudyList convertStudyList(ResultSet rs) throws SQLException {
	    return new StudyList(rs.getInt("bno"),
			   			 rs.getString("memId"),
			   			 rs.getString("title"),
			   			 rs.getDate("regDate"),
			   			 rs.getInt("hit"),
			   			 rs.getInt("replyCount"));
    }
   
  
}
