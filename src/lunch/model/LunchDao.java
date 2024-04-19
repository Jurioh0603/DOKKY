package lunch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class LunchDao {
    //글쓰기
    public Lunch insert(Connection conn, Lunch lunch) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "INSERT INTO LUNCH (bno, memid, title, regdate, hit) VALUES (qna_seq.nextval, ?, ?, ?, 0)";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, lunch.getMemid());
            pstmt.setString(2, lunch.getTitle());
            pstmt.setDate(3, new java.sql.Date(lunch.getRegdate().getTime()));

            int insertedCount = pstmt.executeUpdate();
            
            if (insertedCount > 0) {
                String sqlGetLastBno = "SELECT LUNCH_SEQ.CURRVAL FROM DUAL";
                pstmt = conn.prepareStatement(sqlGetLastBno);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    int newBno = rs.getInt(1);
                    return new Lunch(newBno, lunch.getTitle(), lunch.getRegdate(), 0, lunch.getMemid());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            
        }
        
        return null; 
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
	private LunchRequest convertLunch(ResultSet rs) throws SQLException {
		return new LunchRequest(rs.getInt(1),
				rs.getString(2),
				rs.getDate(3), 
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7));
	}
	


    
    //글보기
    
    //글수정
    
    //글삭제
}