package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import qna.model.Qcontent;
import jdbc.JdbcUtil;
import oracle.sql.DATE;

public class QcontentDao {
    
	//p.656 조회관련메서드 selectById()
		public Qcontent selectById(Connection conn, int no)throws SQLException{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(
						"SELECT * FROM Qcontent WHERE bno = ?");
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				Qcontent qcontent = null;
				if(rs.next()) {
					qcontent = new Qcontent(
							rs.getInt("bno"),rs.getString("content"));
				}
				return qcontent;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
		public Qcontent insert(Connection conn, Qcontent ccontent) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            String sql = "INSERT INTO qcontent (bno, content) VALUES (?, ?)";
	            
	            pstmt = conn.prepareStatement(sql);

	            pstmt.setInt(1, ccontent.getBno());
	            pstmt.setString(2, ccontent.getContent());

	            int insertedCount = pstmt.executeUpdate();
	            
	            if (insertedCount > 0) {
	                return ccontent;
	            } else {
	            	 return null; 
	            }
	        } finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
	            
	    	}
		}
		
		//update메서드 p.665
		public int update(Connection conn, int no, String content) throws SQLException {
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement("update Qcontent set content = ? where bno = ?");
				pstmt.setString(1, content);
				pstmt.setInt(2, no);
				return pstmt.executeUpdate();
			}finally {
				JdbcUtil.close(pstmt);
			}
		}
		
		//delete메서드 구현시도
		public int delete(Connection conn, int qcontentNo) throws SQLException {
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement("delete from Qcontent where bno = ?");
				pstmt.setInt(1, qcontentNo);
				return pstmt.executeUpdate();
			}finally {
				JdbcUtil.close(pstmt);
			}
		}
		
	}
