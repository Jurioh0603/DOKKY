package main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import main.service.MainPageService;
import mvc.command.CommandHandler;

public class MainPageController implements CommandHandler {
	
	private static final String MAIN_VIEW = "/view/board/main/mainPage.jsp";
	private MainPageService mainPageService = new MainPageService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, List<Board>> boardList = mainPageService.getBoardList();
		req.setAttribute("boardList", boardList);
		return MAIN_VIEW;
	}
}
