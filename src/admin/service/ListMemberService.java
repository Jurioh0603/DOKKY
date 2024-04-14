package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import member.model.Member;
import member.model.MemberDao;

public class ListMemberService {

	private MemberDao memberDao = new MemberDao();
	private int size = 10;
	
	public MemberPage getMemberPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = memberDao.selectCount(conn);
			List<Member> memberList = memberDao.select(conn, (pageNum - 1) * size, size);
			return new MemberPage(total, pageNum, size, memberList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
