package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.model.Member;
import member.model.MemberDao;

public class LoginService {

	private MemberDao memberDao = new MemberDao();
	
	public User login(String id, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, id);
			if(member == null) {
				throw new LoginFailException();
			}
			if(!member.getMempw().equals(password)) {
				throw new LoginFailException();
			}
			return new User(member.getMemid(), member.getName(), member.getGrade());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
