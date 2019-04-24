package com.revizio.moviebokka.service;

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
		boardDAO = BoardDAO.getInstance();
		initPaging();
	}


	public List<Board> getBoards(String startNum, String endNum) {
		return boardDAO.getBoard(startNum,endNum);
	}

	public List getPagingPage(String currentIndex) {
		int index = Integer.parseInt(currentIndex);
		int startNum = boardPaging.getPagingSize()*(index-1==0?0:(index-1))+1;
		int endNum = startNum+boardPaging.getPagingSize()-1;
		System.out.println(index+" "+startNum+" "+endNum);
		return boardDAO.getPaingPage(startNum, endNum);		
	}

	public void initPaging() {
		System.out.println("initsetting");
		boardPaging = new Paging(Constants.PAGING_SIZE, Constants.PAGE_INDEX_SIZE, 
				Constants.INIT_CURRENT_PAGE,Constants.INIT_START_INDEX,Constants.INIT_END_INDEX);	
	}

	public void setMovePrev(String currentIndex) {
		int index = Integer.parseInt(currentIndex);
		boardPaging.setCurrentPage(boardPaging.getCurrentPage()-1);
		int startIndex = (boardPaging.getCurrentPage()-1)*boardPaging.getPageIndexSize()+1;
		int endIndex = boardPaging.getCurrentPage()*boardPaging.getPageIndexSize();
		System.out.println("movePrev "+startIndex+" "+endIndex);
		boardPaging.setStartIndex(startIndex);
		boardPaging.setEndIndex(endIndex);
	}
	
	public void setMoveNext(String currentIndex) {
		int index = Integer.parseInt(currentIndex);
		boardPaging.setCurrentPage(boardPaging.getCurrentPage()+1);
		int startIndex = (boardPaging.getCurrentPage()-1)*boardPaging.getPageIndexSize()+1;
		int endIndex = boardPaging.getCurrentPage()*boardPaging.getPageIndexSize();
		System.out.println("moveNext "+startIndex+" "+endIndex);
		boardPaging.setStartIndex(startIndex);
		boardPaging.setEndIndex(endIndex);
	}
	
	public String setPaging() {
		int totalCnt = boardDAO.getTotalDataCnt();
		boardPaging.setTotalRowData(totalCnt);
		JsonMaker jsonMaker = new JsonMaker();
		String json = jsonMaker.convertObjectToJson(boardPaging);
		return json;
	}
	
	  /////////////////////////////////////
	   public int getMyBoardCount(int mem_id) {
	      // TODO Auto-generated method stub
	      return boardDAO.myBoardCount(mem_id);
	   }



	   public List<Board> getMyBoardList(int mem_id, int startNum, int startRow, int endRow) {
	      List<Board> list = boardDAO.myBoardList(mem_id, startNum, startRow, endRow);
	      return list;
	   }



	   public int getMyReviewCount(int mem_id) {
	      return boardDAO.myReviewCount(mem_id);
	   }



	   public List<Board> getMyReviewList(int mem_id, int startNum, int startRow, int endRow) {
	      List<Board> list = boardDAO.myReviewList(mem_id, startNum, startRow, endRow);
	      System.out.println();
	      return list;
	   }
	

}
