package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class MemberListService {

	private MemberDao memberDao = new MemberDao();
	private int size = 10;
	
	public MemberPage getMemberPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = memberDao.selectCount(conn);
			List<Member> memberList = memberDao.select(conn, (pageNum - 1) * size + 1, pageNum * size);
			return new MemberPage(total, pageNum, size, memberList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateGrade(String id, int grade) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			memberDao.updateGrade(conn, id, grade);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public MemberPage searchMemberPage(int pageNum, String searchId) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = memberDao.selectSearchCount(conn, searchId);
			int totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			if(totalPages < pageNum)
				pageNum = 1;
			List<Member> memberList = memberDao.selectSearch(conn, (pageNum - 1) * size + 1, pageNum * size, searchId);
			return new MemberPage(total, pageNum, size, memberList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
