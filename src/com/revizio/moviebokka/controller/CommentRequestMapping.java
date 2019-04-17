package com.revizio.moviebokka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.ReviewComment;
import com.revizio.moviebokka.service.CommentService;
import com.revizio.moviebokka.service.UserService;

public class CommentRequestMapping implements RequestDispatcher {
	private CommentService commentService; 
	private UserService userService;
	private HttpSession session;
   
   public CommentRequestMapping() {
	  commentService = new CommentService();
	  userService = new UserService();
   }
   
   @Override
   public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
	  if(route.equals(Route.CREATE_COMMENT.getRoute())){
		  String input = request.getParameter("input");
		  String group = request.getParameter("group");
		  String depth = request.getParameter("depth");
		  String order = request.getParameter("order");
		  String revId = request.getParameter("revId");
		  int id = Integer.parseInt(revId);
		  
		  session = request.getSession();
    	  Member member =  (Member) session.getAttribute("user");
		  
    	  ReviewComment reviewComment = new ReviewComment(input,group,depth,order,member.getMem_id(),member.getMem_nick(),id);
    	  String ip = userService.getClientIP(request);
    	  commentService.createReviewComment(reviewComment, input, ip);
	  }
   }
}