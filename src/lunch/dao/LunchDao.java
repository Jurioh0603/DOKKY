package lunch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcUtil;
import lunch.model.LunchRequest;

public class LunchDao {
    
    public List<LunchRequest> select(Connection conn, int startRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * "
					   + "from (select A.*, Rownum Rnum "
					   + "      from (select L.*, I.filerealname, I.filename"
					   + "            from lunch L join image I"
					   + "            on L.bno=I.bno"
					   + "            order by L.bno desc) A)"
					   + "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			List<LunchRequest> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertLunch(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
	}

    //글목록
    public int selectCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from lunch";
			pstmt = conn.prepareStatement(sql);
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

   //게시글 리스트 함수 p.647
   private LunchRequest convertLunch(ResultSet rs) throws SQLException {
	   return new LunchRequest(rs.getInt("bno"),
			   			rs.getString("memId"),
			   			rs.getString("title"),
			   			rs.getDate("regDate"),
			   			rs.getInt("hit"),
			   			rs.getString("fileName"),
			   			rs.getString("fileRealName"));
			   					
   }
   
   //게시판 글읽기(조회) 기능 구현. p655
   public LunchRequest selectById(Connection conn, int no)throws SQLException{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try {
		   pstmt = conn.prepareStatement(
				   "SELECT * FROM LUNCH WHERE bno = ?");
		   pstmt.setInt(1,  no);
		   rs = pstmt.executeQuery();
		   LunchRequest lunch = null;
		   if(rs.next()) {
			   lunch = convertLunch(rs); //p648. convertStudy() 게시글 목록 조회 기능구현에서 생성한 메서드
		   }
		   return lunch;
	   }finally {
		   JdbcUtil.close(rs);
		   JdbcUtil.close(pstmt);
	   }
   }
	
}