package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;

public class ChangePwdService {

	private MemberDao memberDao = new MemberDao();
	private String memid = null;
	
	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}
	
	public void changePwd(String password1, String password2) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			if(!password1.equals(password2)) {
				throw new ChangePwdFailException();
			}
			memberDao.updatePwd(conn, password1, memid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
