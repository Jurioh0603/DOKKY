package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.model.MemberDao;

public class FindIdService {

	private MemberDao memberDao = new MemberDao();
	
	public String findId(String name, String email) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			String memid = memberDao.findId(conn, name, email);
			if(memid == null) {
				throw new FindIdFailException();
			}
			return memid;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
