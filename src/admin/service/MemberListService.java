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
	
	//전체 회원 목록 조회
	public MemberPage getMemberPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = memberDao.selectCount(conn);
			List<Member> memberList = memberDao.select(conn, (pageNum - 1) * size + 1, pageNum * size);
			return new MemberPage(total, pageNum, size, memberList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//검색한 회원 정보 조회
	public MemberPage searchMemberPage(int pageNum, String searchId) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			//회원이 존재하면 total은 1, 존재하지 않으면 0
			int total = memberDao.selectSearchCount(conn, searchId);
			int totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			//검색 로직 처리 후 회원 목록은 무조건 1페이지
			if(totalPages < pageNum)
				pageNum = 1;
			List<Member> memberList = memberDao.selectSearch(conn, (pageNum - 1) * size + 1, pageNum * size, searchId);
			return new MemberPage(total, pageNum, size, memberList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//회원 등급 수정
	public void updateGrade(String id, int grade) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			memberDao.updateGrade(conn, id, grade);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
