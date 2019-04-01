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
			System.out.println("inininin");
			
			System.out.println(json);
			System.out.println("first");
			
			request.setAttribute("paging", json);
			request.setAttribute("boards", boards);
		} else if(route.equals(Route.PAGING.getRoute())) {
			String currentIndex = request.getParameter("startNum");
			List pagingBoards = boardService.getPagingPage(currentIndex);
			String json = boardService.setPaging();
			System.out.println("paging inin");
			System.out.println(json);
			System.out.println("second");
			
			request.setAttribute("paging", json);
			request.setAttribute("boards", pagingBoards);
		} else if(route.equals(Route.MOVE_NEXT.getRoute())) {
			//todo next를 눌렀을때 서버 페이징의 값들을 변경시켜준다
			//board도 해당값으로 변경시킨다.
			//그거를 제이슨으로 뿌려준다
			String currentIndex = request.getParameter("startIndex");
			boardService.setCurrentIndex(currentIndex);
			List pagingBoards = boardService.getPagingPage(currentIndex);
			String json = boardService.setPaging();
			
			System.out.println("third");
			request.setAttribute("paging", json);
			request.setAttribute("boards", pagingBoards);
		}
	}

}
