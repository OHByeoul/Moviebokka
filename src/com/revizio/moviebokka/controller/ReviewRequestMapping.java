package com.revizio.moviebokka.controller;

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
			int movieCode = Integer.parseInt(request.getParameter("movieCode"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int memId = Integer.parseInt(request.getParameter("memId"));
			String nick = request.getParameter("nick");
			String ip = userService.getClientIP(request);
			Review review = new Review(title,content,memId,nick,movieCode,ip);
			
			Review getDetailReview = reviewService.createReview(review);
			request.setAttribute("reviewDetail", getDetailReview);
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
    	  session.setAttribute("session", member);
    	  
    	  request.setAttribute("gCnt", gCnt);
    	  request.setAttribute("reviewComments", reviewComments);
    	  request.setAttribute("reviewDetail", selectedReview);
    	  request.setAttribute("recommand", userRecommand);
      } else if(route.equals(Route.RECOMMAND_REVIEW.getRoute())) {
    	  String revId = request.getParameter("revId");
    	  String status = request.getParameter("status"); 
    	  System.out.println(status);
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
    	  int movieCode = Integer.parseInt(request.getParameter("movieCode"));
    	  String revId = request.getParameter("revId");
    	  reviewService.deleteSelectedReview(revId);
          GetMovieInfoForm getMovieInfoForm = movieService.getSelectedMovieDetail(movieCode);
  
          List<Review> reviews = reviewService.getReviewList(movieCode);
          request.setAttribute("reviews", reviews);
          request.setAttribute("movieInfoForm", getMovieInfoForm);
      } 
   }
}