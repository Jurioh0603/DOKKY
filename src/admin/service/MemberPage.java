package admin.service;

import java.util.List;

import member.model.Member;

public class MemberPage {
	
	private int total;
	private int currentPage;
	private List<Member> memberList;
	private int totalPages;
	private int startPage;
	private int endPage;
	private int size;
	
	public MemberPage(int total, int currentPage, int size, List<Member> memberList) {
		this.total = total;
		this.currentPage = currentPage;
		this.memberList = memberList;
		this.size = size;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if(endPage > totalPages) endPage = totalPages;
		}
	}
	
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoMembers() {
		return total == 0;
	}
	
	public boolean hasMembers() {
		return total > size;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<Member> getMemberList() {
		return memberList;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
