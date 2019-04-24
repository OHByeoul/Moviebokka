package com.revizio.moviebokka.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dto.Board;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.service.BoardService;
import com.revizio.moviebokka.service.ReviewService;
import com.revizio.moviebokka.service.UserService;

public class BoardRequestMapping implements RequestDispatcher{
	private BoardService boardService;
	private ReviewService reviewService;
	private UserService userService;
	private List<Board> boards;
	
	public BoardRequestMapping() {
		boardService = new BoardService();
		userService = new UserService();
		reviewService = new ReviewService();
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
		}   else if(route.equals(Route.VIEW_MY_CONTENT.getRoute())) {   //내가 작성한 글
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("user");
			String email = member.getMem_email();
			
			Member memberInfo = userService.userInfo(email);
			
			int mem_id = memberInfo.getMem_id();
			
			int brdTotalCount = boardService.getMyBoardCount(mem_id);
			String brdPageNum = request.getParameter("brdPageNum");
			
			if(brdPageNum == null || brdPageNum.equals("")) {
				brdPageNum = "1";
			}
			
			int brdCurrentPage = Integer.parseInt(brdPageNum);
			int brdPageSize = 5, brdBlockSize = 5;
			int brdStartRow = (brdCurrentPage - 1) * brdPageSize + 1;
			int brdEndRow = brdStartRow + brdPageSize - 1;
			int brdStartNum = brdTotalCount - brdStartRow + 1;
			List<Board> brdList = boardService.getMyBoardList(mem_id, brdStartNum, brdStartRow, brdEndRow);
			int brdPageCnt = (int)Math.ceil((double)brdTotalCount/brdPageSize);
			int brdStartPage = (int)(brdCurrentPage - 1) / brdBlockSize * brdBlockSize + 1;
			int brdEndPage = brdStartPage + brdBlockSize -1;
			
			if(brdEndPage > brdPageCnt)
				brdEndPage = brdPageCnt;
			
			request.setAttribute("brdTotalCount", brdTotalCount);
			request.setAttribute("brdPageNum", brdPageNum);
			request.setAttribute("brdCurrentPage", brdCurrentPage);
			request.setAttribute("brdPageSize", brdPageSize);
			request.setAttribute("brdStartRow", brdStartRow);
			request.setAttribute("brdEndRow", brdEndRow);
			request.setAttribute("brdStartNum", brdStartNum);
			request.setAttribute("brdList", brdList);
			request.setAttribute("brdPageCnt", brdPageCnt);
			request.setAttribute("brdBlockSize", brdBlockSize);
			request.setAttribute("brdPageCnt", brdPageCnt);
			request.setAttribute("brdStartPage", brdStartPage);
			request.setAttribute("brdEndPage", brdEndPage);
			
			// 리뷰 정보 뽑아오는곳
			int reviewTotalCount = boardService.getMyReviewCount(mem_id);
			String reviewPageNum = request.getParameter("reviewPageNum");
			
			if(reviewPageNum == null || reviewPageNum.equals("")) {
				reviewPageNum = "1";
			}
			
			int reviewCurrentPage = Integer.parseInt(reviewPageNum);
			int reviewPageSize = 5, reviewBlockSize = 5;
			int reviewStartRow = (reviewCurrentPage - 1) * reviewPageSize + 1;
			int reviewEndRow = reviewStartRow + reviewPageSize - 1;
			int reviewStartNum = reviewTotalCount - reviewStartRow + 1;
			//List<Board> reviewList = boardService.getMyReviewList(mem_id, reviewStartNum, reviewStartRow, reviewEndRow);
			List<Review> reviews = reviewService.getMyReviewList(mem_id, reviewStartNum, reviewStartRow, reviewEndRow);
			int reviewPageCnt = (int)Math.ceil((double)reviewTotalCount/reviewPageSize);
			int reviewStartPage = (int)(reviewCurrentPage - 1) / reviewBlockSize * reviewBlockSize + 1;
			int reviewEndPage = reviewStartPage + reviewBlockSize -1;
			
			if(reviewEndPage > reviewPageCnt)
				reviewEndPage = reviewPageCnt;
			
			request.setAttribute("reviewTotalCount", reviewTotalCount);
			request.setAttribute("reviewPageNum", reviewPageNum);
			request.setAttribute("reviewCurrentPage", reviewCurrentPage);
			request.setAttribute("reviewPageSize", reviewPageSize);
			request.setAttribute("reviewStartRow", reviewStartRow);
			request.setAttribute("reviewEndRow", reviewEndRow);
			request.setAttribute("reviewStartNum", reviewStartNum);
			request.setAttribute("reviews", reviews);
			request.setAttribute("reviewPageCnt", reviewPageCnt);
			request.setAttribute("reviewBlockSize", reviewBlockSize);
			request.setAttribute("reviewPageCnt", reviewPageCnt);
			request.setAttribute("reviewStartPage", reviewStartPage);
			request.setAttribute("reviewEndPage", reviewEndPage);
		}
 
 }
}
