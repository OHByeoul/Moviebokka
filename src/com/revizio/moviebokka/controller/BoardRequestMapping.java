package com.revizio.moviebokka.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.dto.Board;
import com.revizio.moviebokka.service.BoardService;

public class BoardRequestMapping implements RequestDispatcher{
	private BoardService boardService;
	private List<Board> boards;
	
	public BoardRequestMapping() {
		boardService = new BoardService();
		boards = new ArrayList<>();
	}
	
	@Override
	public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
		if(route.equals(Route.GET_FREEBOARD_LIST.getRoute())) {
			String startNum = request.getParameter("startNum");
			String endNum = request.getParameter("endNum");
			String json = boardService.setPaging();
			boardService.initPaging();
			boards = boardService.getBoards(startNum,endNum);
			
			request.setAttribute("paging", json);
			request.setAttribute("boards", boards);
		} else if(route.equals(Route.PAGING.getRoute())) {
			String currentIndex = request.getParameter("startNum");
			List pagingBoards = boardService.getPagingPage(currentIndex);
			String json = boardService.setPaging();
			
			request.setAttribute("paging", json);
			request.setAttribute("boards", pagingBoards);
		} else if(route.equals(Route.MOVE_PREV.getRoute())) {
			String currentIndex = request.getParameter("startIndex");
			boardService.setMovePrev(currentIndex);
			List pagingBoards = boardService.getPagingPage(currentIndex);
			String json = boardService.setPaging();
			
			request.setAttribute("paging", json);
			request.setAttribute("boards", pagingBoards);
		} else if(route.equals(Route.MOVE_NEXT.getRoute())){
			String currentIndex = request.getParameter("startIndex");
			boardService.setMoveNext(currentIndex);
			List pagingBoards = boardService.getPagingPage(currentIndex);
			String json = boardService.setPaging();
			
			request.setAttribute("paging", json);
			request.setAttribute("boards", pagingBoards);
		}
	}

}
