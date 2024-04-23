package member.board;

import java.util.List;

public class StudyPage {
	private int total;
	private int currentPage;
	private List<Study> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public StudyPage(int total, int currentPage, int size, List<Study> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else {
			totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal == 0) startPage -=5;
			
			endPage = startPage + 4;
			if(endPage > totalPages)endPage = totalPages;
		}
	}
	
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoStudy() {
		return total == 0;
	}
	
	public boolean hasStudy() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<Study> getContent(){
		return content;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
