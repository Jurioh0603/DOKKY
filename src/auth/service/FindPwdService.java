package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.model.Member;
import member.model.MemberDao;

public class FindPwdService {

	private MemberDao memberDao = new MemberDao();
	
	public String findMember(String name, String id, String email) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.findMember(conn, name, id, email);
			if(member == null) {
				throw new FindPwdFailException();
			}
			return member.getMemid();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
