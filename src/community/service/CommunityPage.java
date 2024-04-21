package community.service;

import java.util.List;

import community.model.Community;

public class CommunityPage {
	private int total;
	private int currentPage;
	private List<Community> communityList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public CommunityPage(int total, int currentPage, int size, List<Community> communityList) {
		this.total = total;
		this.currentPage = currentPage;
		this.communityList = communityList;
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
	
	public boolean hasNoContents() {
		return total == 0;
	}
	
	public boolean hasContents() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<Community> getCommunityList() {
		return communityList;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
