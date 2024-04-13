package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.model.MemberDao;

public class FindPwdService {

	private MemberDao memberDao = new MemberDao();
	
	public void findMember(String name, String id, String email) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Boolean isExist = memberDao.findMember(conn, name, id, email);
			if(isExist == false) {
				throw new FindPwdFailException();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
