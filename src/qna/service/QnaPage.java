package qna.service;

import java.util.List;

//게시글 목록을 제공하는 서비스 클래스
public class QnaPage {
	
	private int total;
	private int currentPage;
	private List<QnaList> qnaList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public QnaPage(int total, int currentPage, int size, List<QnaList> qnaList) {
		this.total = total;
		this.currentPage = currentPage;
		this.qnaList = qnaList;
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

	public List<QnaList> getQnaList() {
		return qnaList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
}
