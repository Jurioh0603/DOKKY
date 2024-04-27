package study.service;

import java.util.List;

public class StudyPage {
	private int total;
	private int currentPage;
	private List<StudyList> studyList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public StudyPage(int total, int currentPage, int size, List<StudyList> studyList) {
		this.total = total;
		this.currentPage = currentPage;
		this.studyList = studyList;
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
	
	public List<StudyList> getStudyList() {
		return studyList;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
