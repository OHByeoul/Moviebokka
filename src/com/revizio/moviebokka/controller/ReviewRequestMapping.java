package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.dto.ReviewComment;
import com.revizio.moviebokka.dto.UserRecommand;
import com.revizio.moviebokka.service.CommentService;
import com.revizio.moviebokka.service.MovieService;
import com.revizio.moviebokka.service.ReviewService;
import com.revizio.moviebokka.service.UserService;
import com.revizio.moviebokka.util.JsonMaker;

public class ReviewRequestMapping implements RequestDispatcher {
   private ReviewService reviewService;
   private MovieService movieService;
   private UserService userService;
   private CommentService commentService;
   private HttpSession session;
   
   
   public ReviewRequestMapping() {
      movieService = new MovieService();
      userService = new UserService();
      reviewService = new ReviewService();
      commentService = new CommentService();
   }
   
   @Override
   public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
	  if(route.equals(Route.REVIEW_FORM.getRoute())) {
    	int movieCode = Integer.parseInt(request.getParameter("movieCode"));		
        session = request.getSession();
        Member member =  (Member) session.getAttribute("user");
        
        request.setAttribute("movieCode", movieCode);
        request.setAttribute("member", member);
      } else if(route.equals(Route.CREATE_REVIEW.getRoute())) {
    	  try {
			request.setCharacterEncoding("UTF-8");
			String needToParseParam1 = request.getParameter("movieCode");
			String needToParseParam2 = request.getParameter("memId");
			int movieCode = 0;
			int memId = 0;
			if(needToParseParam1 != null && needToParseParam2 != null) {
				movieCode = Integer.parseInt(needToParseParam1);
				memId = Integer.parseInt(needToParseParam2);
			}
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String nick = request.getParameter("nick");
			String ip = userService.getClientIP(request);
			if(needToParseParam1 != null && needToParseParam2 != null) {
			Review review = new Review(title,content,memId,nick,movieCode,ip);
			
			Review getDetailReview = reviewService.createReview(review);
			request.setAttribute("reviewDetail", getDetailReview);
					
			} 
			else {
				Review getRecentReview = reviewService.getRecentCreatedReview();
				String revId = String.valueOf(getRecentReview.getRev_id());
				List<ReviewComment> reviewComments = commentService.getReviewCommentById(revId);
				request.setAttribute("reviewDetail", getRecentReview);
				request.setAttribute("reviewComments", reviewComments);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
      } else if(route.equals(Route.GET_REVIEW_INFO.getRoute())) {
    	  String revId = request.getParameter("revId");
    	  Review selectedReview = reviewService.getSelectedReviewDetail(revId);
    	  session = request.getSession();
    	  Member member =  (Member) session.getAttribute("user");
    	  UserRecommand userRecommand = new UserRecommand();
    	  if(member != null) {
    		  userRecommand = reviewService.getUserRecommandStatus(revId,member.getMem_email());
    	  }
    	  List<ReviewComment> reviewComments = commentService.getReviewCommentById(revId);
    	  int gCnt = commentService.getMaxGroupId();
    	  
    	  request.setAttribute("gCnt", gCnt);
    	  request.setAttribute("reviewComments", reviewComments);
    	  request.setAttribute("reviewDetail", selectedReview);
    	  request.setAttribute("recommand", userRecommand);
      } else if(route.equals(Route.RECOMMAND_REVIEW.getRoute())) {
    	  String revId = request.getParameter("revId");
    	  String status = request.getParameter("status");
    	  
    	  reviewService.updateRecommand(revId,status);
      } else if(route.equals(Route.UNRECOMMAND_REVIEW.getRoute())) {
    	  String revId = request.getParameter("revId");
    	  String status = request.getParameter("status"); 
    	  reviewService.updateUnrecommand(revId,status);
    	  
      } else if(route.equals(Route.CHECK_USER_RECOMMAND.getRoute())) {
    	  String revId = request.getParameter("revId"); 
    	  String status = request.getParameter("status");
    	  String unStatus = request.getParameter("unStatus");
    	  Member member =  (Member) session.getAttribute("user");
    	  String email = "";
    	  if(member != null) {
    		  email = member.getMem_email();
    	  }
    	  reviewService.checkUserRecommand(revId, email,status,unStatus);
      } else if(route.equals(Route.UPDATE_REVIEW_FORM.getRoute())) {
    	  String revId = request.getParameter("revId");
    	  Review selectedReview = reviewService.getSelectedReviewDetail(revId);
    	  Member member =  (Member) session.getAttribute("user");
    	  session.setAttribute("session", member);
    	  request.setAttribute("review", selectedReview);
      } else if(route.equals(Route.UPDATE_REVIEW_PAGE.getRoute())) {
    	  try {
			request.setCharacterEncoding("UTF-8");
			String revId = request.getParameter("revId");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Review selectedReview = reviewService.updateSelectedReview(revId,title,content);
			

			Member member =  (Member) session.getAttribute("user");
			session.setAttribute("session", member);
			request.setAttribute("reviewDetail", selectedReview);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
      } else if(route.equals(Route.DELETE_REVIEW_PAGE.getRoute())) {
    	  String revId = request.getParameter("revId");
    	  int movieCode = Integer.parseInt(request.getParameter("movieCode"));
  
    	 // session = request.getSession();
    	  if(revId != null) {
    		  reviewService.deleteSelectedReview(revId);
    	  }
    	  GetMovieInfoForm getMovieInfoForm = movieService.getSelectedMovieDetail(movieCode);
    	  List<Review> reviews = reviewService.getReviewList(movieCode);
    	  
    	  request.setAttribute("movieInfoForm", getMovieInfoForm);
    	  request.setAttribute("reviews", reviews);
      } else if(route.equals(Route.GET_REVIEWlIST_MORE.getRoute())) {
    	  String movieCode = request.getParameter("movieCode");
    	  String startNum = request.getParameter("startNum");
		  String endNum = request.getParameter("endNum");
    	  
    	  List<Review> reviews = reviewService.getReviewListMore(movieCode,startNum,endNum);
    	  request.setAttribute("start", startNum);
	      request.setAttribute("end", endNum);
	      JsonMaker jsonMaker = new JsonMaker();
	         String toJson = jsonMaker.convertObjectToJson(reviews);
	         
	         response.setContentType("application/json");
	         response.setCharacterEncoding("UTF-8");
	         try {
	            response.getWriter().write(toJson);
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
      }
   }
}