package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.service.MovieService;
import com.revizio.moviebokka.service.ReviewService;
import com.revizio.moviebokka.util.JsonMaker;

public class SearchRequestMapping implements RequestDispatcher {
   private MovieService movieService;
   private ReviewService reviewService;
   
   public SearchRequestMapping() {
      movieService = new MovieService();
      reviewService = new ReviewService();
   }
   
   @Override
   public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
	   if(route.equals(Route.SEARCH_DETAIL_INFO.getRoute())) {
		   
	   } else if(route.equals(Route.GET_SEARCH_ALL.getRoute())){
		   String search  = request.getParameter("search");
		   String startNum = request.getParameter("startNum");
		   String endNum = request.getParameter("endNum");
		   List<GetMovieInfoForm> movieInfoFormList = movieService.getSearchedMovieList(search,startNum,endNum);
	       List<Review> reviewLists = reviewService.getSearchedReviewList(search,startNum, endNum);
	       
	       for(GetMovieInfoForm g : movieInfoFormList) {
	    	   System.out.println(g.getM_code());
	    	   System.out.println(g.getM_title());
	       }
	       
    	  request.setAttribute("search", search);
    	  request.setAttribute("movieInfoFormList", movieInfoFormList);
    	  request.setAttribute("reviewLists", reviewLists);
	    // 커뮤결과
	   } else if(route.equals(Route.GET_MOVIE_MORE.getRoute())) {
		   String search = request.getParameter("search");
		   String startNum = request.getParameter("startNum");
		   String endNum = request.getParameter("endNum");
		   
		   List<GetMovieInfoForm> movieInfoFormList = new ArrayList<>();
	         movieInfoFormList = movieService.getSearchedMovieList(search,startNum,endNum);
	         for(GetMovieInfoForm m : movieInfoFormList) {
	            System.out.println(m.getM_code());
	            System.out.println(m.getM_title());
	         }
	         System.out.println("done");
	         
	         JsonMaker jsonMaker = new JsonMaker();
	         String toJson = jsonMaker.convertObjectToJson(movieInfoFormList);
	         System.out.println(toJson);
	         request.setAttribute("movieInfoFormList", movieInfoFormList);
	         response.setContentType("application/json");
	         response.setCharacterEncoding("UTF-8");
	         try {
	            response.getWriter().write(toJson);
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	   } else if(route.equals(Route.GET_REVIEW_MORE.getRoute())){
	    	  String search  = request.getParameter("search");
	    	  String startNum = request.getParameter("startNum");
	    	  String endNum = request.getParameter("endNum");
	    	  
	    	  List<Review> reviewLists = reviewService.getSearchedReviewList(search,startNum, endNum);
	    	  
	    	  JsonMaker jsonMaker = new JsonMaker();
	    	  String toJson = jsonMaker.convertObjectToJson(reviewLists);
	    	  System.out.println(toJson);
	    	  request.setAttribute("reviewLists", reviewLists);
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