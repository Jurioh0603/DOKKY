package study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;
import study.model.Study;

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
	   try(PreparedStatement pstmt =
			   conn.prepareStatement(
					   "UPDATE study SET hit = hit+1" +
					   "WHERE bno = ?")){
		   pstmt.setInt(1, no);
		   pstmt.executeUpdate();
	   }
   }
   
   //게시글 제목 수정 기능 p.665
   public int update(Connection conn, int no, String title)throws SQLException{
	   try(PreparedStatement pstmt = 
			   conn.prepareStatement(
					   "update study set title = ? " + 
					   "where bno = ?")) {
		   pstmt.setString(1, title);
		   pstmt.setInt(2, no);
		   return pstmt.executeUpdate();
	   }
   }
   
   
}
