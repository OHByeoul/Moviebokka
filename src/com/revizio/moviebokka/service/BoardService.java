package com.revizio.moviebokka.service;

import java.sql.SQLException;
import java.util.List;

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dao.BoardDAO;
import com.revizio.moviebokka.dto.Board;
import com.revizio.moviebokka.dto.Paging;
import com.revizio.moviebokka.util.JsonMaker;



public class BoardService {
	private BoardDAO boardDAO;
	private Paging boardPaging;
	
	public BoardService() {
		try {
			boardDAO = new BoardDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boardPaging = new Paging(Constants.PAGING_SIZE, Constants.PAGE_INDEX_SIZE, 
				Constants.INIT_CURRENT_PAGE,Constants.INIT_START_INDEX,Constants.INIT_END_INDEX);
	}
	
	public String setPaging() {
		int totalCnt = boardDAO.getTotalDataCnt();
		boardPaging.setTotalRowData(totalCnt);
		JsonMaker jsonMaker = new JsonMaker();
		String json = jsonMaker.convertObjectToJson(boardPaging);
		return json;
	}
	
	public List getAllBoards() {
		List boards = boardDAO.getAllBoard();
		return boards;
	}

	public List<Board> getBoards(String startNum, String endNum) {
		return boardDAO.getBoard(startNum,endNum);
	}

	public List getPagingPage(String currentIndex) {
		int index = Integer.parseInt(currentIndex);
		int startNum = boardPaging.getPagingSize()*(index-1==0?0:(index-1))+1;
		int endNum = startNum+boardPaging.getPagingSize()-1;
		System.out.println(index+" "+startNum+" "+endNum);
//		boardPaging.setStartIndex(startIndex);
//		boardPaging.setEndIndex(endIndex);
		return boardDAO.getPaingPage(startNum, endNum);
//		startIndex = pagingSize*보낸 파라미터값
//		endIndex = startIndex+pagingSize
		
	}

	public Board getDetailBoard(String id) {
		return boardDAO.getDetailBoardById(id);
	}

}
