package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.omg.CORBA.BAD_INV_ORDER;

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dao.BoardDAO;
import com.revizio.moviebokka.dto.Board;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.service.BoardService;
import com.revizio.moviebokka.service.ReviewService;
import com.revizio.moviebokka.service.UserService;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;



public class BoardRequestMapping<ActionForward> implements RequestDispatcher{
    private BoardService boardService;
    private UserService userService;
    private ReviewService reviewService;
    private List<Board> list;
    
    public BoardRequestMapping() {
        boardService = new BoardService();
        userService = new UserService();
        reviewService = new ReviewService();
    }
    
    @Override
    public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        if(route.equals(Route.BOARD_LIST.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null;
            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
            }
            try {
                String search = null;
                String keyword = null;
                String type = request.getParameter("type");
                if(request.getParameter("search") != null) {
                    search = request.getParameter("search");
                    keyword = request.getParameter("keyword");
                }

                int totCnt = boardService.getTotal(Integer.parseInt(type), search, keyword);
                String pageNum = request.getParameter("pageNum");
                if (pageNum == null || pageNum.equals("")) { pageNum = "1"; }
                int currentPage = Integer.parseInt(pageNum);
                int pageSize = Constants.PAGESIZE, blockSize = Constants.BLOCKSIZE;
                int startRow = (currentPage - 1) * pageSize + 1;
                int endRow = startRow + pageSize - 1;
                int startNum = totCnt - startRow + 1;
                list = (List<Board>)boardService.getList(startRow, endRow, Integer.parseInt(type), search, keyword);
                int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
                int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
                int endPage = startPage + blockSize - 1;
                if (endPage > pageCnt) endPage = pageCnt;
                
                request.setAttribute("totCnt", totCnt);
                request.setAttribute("pageNum", pageNum);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("startRow", startRow);
                request.setAttribute("endRow", endRow);
                request.setAttribute("startNum", startNum);
                request.setAttribute("list", list);
                request.setAttribute("pageCnt", pageCnt);
                request.setAttribute("startPage", startPage);
                request.setAttribute("endPage", endPage);
                request.setAttribute("type", type);
                if(session.getAttribute("user") != null) {
                    request.setAttribute("mem_id", userInfo.getMem_id());
                }
                if(request.getParameter("search") != null) {
                    request.setAttribute("search", search);
                    request.setAttribute("keyword", keyword);
                }
            } catch (Exception e) {}
            
        } else if(route.equals(Route.BOARD_VIEW.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null;
            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
            }
            int brd_id = Integer.parseInt(request.getParameter("brd_id"));
            boolean isView = false;

            try {
                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    for(int i = 0; i < cookies.length; i++) {
                        if(cookies[i].getName().equals("board-" + brd_id)) {
                            isView = true;
                            break;
                        }
                    }
                }
            } catch (Exception e) {}
            
            if(!isView) {
                boardService.updateCount(brd_id);
                Cookie cookie = new Cookie("board-" + brd_id, "1");
                cookie.setMaxAge(30 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            Board board = boardService.select(brd_id);
            request.setAttribute("board", board);
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("pageNum", request.getParameter("pageNum"));
            if(session.getAttribute("user") != null) {
                request.setAttribute("mem_id", userInfo.getMem_id());
            }
            if(request.getParameter("search") != null) {
                request.setAttribute("search", request.getParameter("search"));
                request.setAttribute("keyword", request.getParameter("keyword"));
            }
        } else if(route.equals(Route.BOARD_UPDATE.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null;
            int acl = 0;
            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
                acl = 1;
            }

            int brd_id = Integer.parseInt(request.getParameter("brd_id"));  // 내용을 받아옴
            Board board = boardService.select(brd_id);
            request.setAttribute("acl", acl);
            request.setAttribute("brd_id", request.getParameter("brd_id"));
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("pageNum", request.getParameter("pageNum"));
            if(session.getAttribute("user") != null) {
                request.setAttribute("mem_id", userInfo.getMem_id());
            }
            request.setAttribute("board", board);
            
            if(request.getParameter("search") != null) {
                request.setAttribute("search", request.getParameter("search"));
                request.setAttribute("keyword", request.getParameter("keyword"));
            }
        } else if(route.equals(Route.BOARD_UPDATEPRO.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null;
            int acl = 0;
            int result = 0;

            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
                acl = 1;
            }

            try { 
                String pageNum = request.getParameter("pageNum");
                int brd_id = Integer.parseInt(request.getParameter("brd_id"));
                Board board = new Board();
                board.setBrd_id(brd_id);
                board.setMem_nick(userInfo.getMem_nick());
                board.setBrd_title(request.getParameter("brd_title"));           
                board.setBrd_content(request.getParameter("brd_content"));
                board.setBrd_ip(request.getRemoteAddr());
                BoardDAO bd = BoardDAO.getInstance();
                if(acl > 0) {
                    result = bd.update(board, userInfo.getMem_id());
                }
                request.setAttribute("acl", acl);
                request.setAttribute("result", result);
                request.setAttribute("brd_id", brd_id);
                request.setAttribute("pageNum", pageNum);
                request.setAttribute("type", request.getParameter("type"));
                if(request.getParameter("search") != null) {
                    request.setAttribute("search", request.getParameter("search"));
                    request.setAttribute("keyword", request.getParameter("keyword"));
                }
            } catch(Exception e) { 
                System.out.println(e.getMessage()); 
            }
        } else if(route.equals(Route.BOARD_DELETE.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null; 
            int acl = 0;
            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
                acl = 1;
            }

            int result = 0;
            int brd_id = Integer.parseInt(request.getParameter("brd_id"));
            String type = request.getParameter("type");
            String pageNum = request.getParameter("pageNum");
            String search = null;
            String keyword = null;
            if(request.getParameter("search") != null) {
                search = request.getParameter("search");
                keyword = request.getParameter("keyword");
            }

            try {
            	BoardDAO bd = BoardDAO.getInstance();
                if(acl > 0) {
                    result = bd.delete(brd_id, userInfo.getMem_id());
                }
                request.setAttribute("brd_id", brd_id);
                request.setAttribute("type", type);
                request.setAttribute("pageNum", pageNum);
                request.setAttribute("result", result);
                request.setAttribute("acl", acl);

                if(search != null) {
                    request.setAttribute("search", search);
                    request.setAttribute("keyword", keyword);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else if(route.equals(Route.BOARD_INSERT.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null;
            int acl = 0;

            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
                acl = 1;
            }

            try {
                int brd_id = 0;
                int brd_ref = 0;
                int brd_level = 0;
                int brd_step = 0;
                String pageNum = request.getParameter("pageNum");
                String type = request.getParameter("type");
                if (pageNum == null) pageNum = "1";

                // 댓글 처리용
                if (request.getParameter("brd_id") != null && acl > 0) {
                    brd_id = Integer.parseInt(request.getParameter("brd_id"));
                    BoardDAO bd = BoardDAO.getInstance();
                    Board board = bd.select(brd_id);
                    brd_ref = board.getBrd_ref();
                    brd_level = board.getBrd_level();
                    brd_step = board.getBrd_step();
                }
                request.setAttribute("acl", acl);
                request.setAttribute("brd_id", brd_id);
                request.setAttribute("brd_ref", brd_ref);
                request.setAttribute("brd_level", brd_level);
                request.setAttribute("brd_step", brd_step);
                request.setAttribute("pageNum", pageNum);
                if(session.getAttribute("user") != null) {
                    request.setAttribute("mem_nick", userInfo.getMem_nick());
                    request.setAttribute("mem_id", userInfo.getMem_id());
                }
                request.setAttribute("type", type);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } else if(route.equals(Route.BOARD_INSERTPRO.getRoute())) {
            HttpSession session = request.getSession();
            Member userInfo = null;
            int acl = 0;
            int result = 0;

            if(session.getAttribute("user") != null) {
                userInfo = (Member)session.getAttribute("user");
                acl = 1;
            }

            try {
                String pageNum = request.getParameter("pageNum");
                String type = request.getParameter("type");
                
                Board board = new Board();
                board.setBrd_id(Integer.parseInt(request.getParameter("brd_id")));
                board.setBrd_type(Integer.parseInt(type));
                board.setMem_id(userInfo.getMem_id());
                board.setMem_nick(userInfo.getMem_nick());
                board.setBrd_title(request.getParameter("brd_title"));    
                board.setBrd_content(request.getParameter("brd_content"));
                board.setBrd_ref(Integer.parseInt(request.getParameter("brd_ref")));
                board.setBrd_step(Integer.parseInt(request.getParameter("brd_step")));
                board.setBrd_level(Integer.parseInt(request.getParameter("brd_level")));
                board.setBrd_ip(request.getRemoteAddr());
                
                if(acl > 0) {
                    result = boardService.insert(board);
                }
                request.setAttribute("acl", acl);
                request.setAttribute("brd_id", board.getBrd_id());
                request.setAttribute("result", result);
                request.setAttribute("pageNum", pageNum);
                request.setAttribute("type", type);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else if(route.equals(Route.VIEW_MY_CONTENT.getRoute())) {   //내가 작성한 글
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
