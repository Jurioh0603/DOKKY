package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, userId);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			if(!member.matchMempw(curPwd)) {
				throw new InvalidPasswordException();
			}
			member.changePassword(newPwd);
			memberDao.updatePwd(conn, member);
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
