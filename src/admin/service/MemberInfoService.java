package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.model.MemberDao;

public class MemberInfoService {

	private MemberDao memberDao = new MemberDao();
	
	public void updateGrade(String id, int grade) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			memberDao.updateGrade(conn, id, grade);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
