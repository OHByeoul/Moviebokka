package com.revizio.moviebokka.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.dao.BoardDAO;
import com.revizio.moviebokka.dto.Board;

public class BoardService {
	private BoardDAO bd;
	
	public BoardService() {
		this.bd = BoardDAO.getInstance();
	}
	
	public int getTotal(int type, String search, String keyword) throws SQLException {
		int totCnt = 0;
		try {
			totCnt = bd.getTotalCnt(type, search, keyword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return totCnt;
	}
	
	public List<Board> getList(int startRow, int endRow, int type, String search, String keyword) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		try {
			list = bd.list(startRow, endRow, type, search, keyword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	
	public int insert(Board board) throws SQLException {
		int result = 0;
		try {
			result = bd.insert(board);
		} catch (Exception e) {}
		
		return result;
	}
	
	public Board select(int brd_id) throws SQLException {
		Board board = null;
		try { 
			board = bd.select(brd_id);
		} catch (Exception e) {}
		
		return board;
	}
	
	public void updateCount(int brd_id) throws SQLException {  // 이거 고쳐야함 조회수 안올라감
		try {
			bd.updateCount(brd_id); 
		} catch (Exception e) {}
		
		return;
	}
	
	public int delete(int brd_id, int mem_id) throws SQLException {
		int result = 0;
		try {
			result = bd.delete(brd_id, mem_id);
		} catch (Exception e) {}
		return result;
	}
	public int update(Board board, int mem_id) throws SQLException {
		int result = 0;
		try {
			result = bd.update(board, mem_id);
		} catch (Exception e) {}
		return result;
	}




	
	
	  /////////////////////////////////////
	   public int getMyBoardCount(int mem_id) {
	      // TODO Auto-generated method stub
	      return bd.myBoardCount(mem_id);
	   }



	   public List<Board> getMyBoardList(int mem_id, int startNum, int startRow, int endRow) {
	      List<Board> list = bd.myBoardList(mem_id, startNum, startRow, endRow);
	      return list;
	   }



	   public int getMyReviewCount(int mem_id) {
	      return bd.myReviewCount(mem_id);
	   }



	   public List<Board> getMyReviewList(int mem_id, int startNum, int startRow, int endRow) {
	      List<Board> list = bd.myReviewList(mem_id, startNum, startRow, endRow);
	      System.out.println();
	      return list;
	   }
	

}
