package member.service;

import java.util.List;

import board.model.Board;

//페이지 정보와 페이지에 보여질 게시글 목록을 담는 PostPage
public class PostPage {
	
	private int total;
	private int currentPage;
	private List<Board> postList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public PostPage(int total, int currentPage, int size, List<Board> postList) {
		this.total = total;
		this.currentPage = currentPage;
		this.postList = postList;
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
	
	public List<Board> getPostList() {
		return postList;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
