package lunch.service;

import java.util.List;

public class LunchPage {
	private int total;
	private int currentPage;
	private List<ListRequest> lunchList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public LunchPage(int total, int currentPage, int size, List<ListRequest> lunchList) {
		this.total = total;
		this.currentPage = currentPage;
		this.lunchList = lunchList;
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
	
	public List<ListRequest> getLunchList() {
		return lunchList;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
