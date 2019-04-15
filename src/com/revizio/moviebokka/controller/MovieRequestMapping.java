package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.MovieInfo;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.service.MovieService;
import com.revizio.moviebokka.service.ReviewService;
import com.revizio.moviebokka.service.UserService;
import com.revizio.moviebokka.util.JsonMaker;

public class MovieRequestMapping implements RequestDispatcher {
   private MovieService movieService;
   private ReviewService reviewService;
   private UserService userService;
   private HttpSession session;
   
   public MovieRequestMapping() {
      movieService = new MovieService();
      reviewService = new ReviewService();
      userService = new UserService();
   }
   
   @Override
   public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
	   if(route.equals(Route.SEARCH_DETAIL_INFO.getRoute())) {
		   
	   } else if(route.equals(Route.GET_MOVIE_SEARCH.getRoute())){
		   String search  = request.getParameter("search");
		   String startNum = request.getParameter("startNum");
		   String endNum = request.getParameter("endNum");
		   List<GetMovieInfoForm> movieInfoFormList = new ArrayList<>();
	       movieInfoFormList = movieService.getSearchedMovieList(search,startNum,endNum);
	       
		   request.setAttribute("movieInfoFormList", movieInfoFormList);
		   request.setAttribute("startM", startNum);
	       request.setAttribute("endM", endNum);
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
	         //가져온  movieInfoFormList를 json형식으로 만든다.
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
	   } else if(route.equals(Route.GET_MOVIE_INFO.getRoute())) {
         int code = Integer.parseInt(request.getParameter("code"));
         String title = request.getParameter("title");
         String img = request.getParameter("img");
         String pubDate = request.getParameter("pubDate");
         String userRating = request.getParameter("userRating");      
         MovieInfo movieInfo = new MovieInfo(code,title,img,userRating,pubDate);
         
         String director = request.getParameter("director");
         String actor = request.getParameter("actor");
         Map<String, String> subInfo = new HashMap<String, String>();

         subInfo.put("director", director);
         subInfo.put("actor", actor);
         GetMovieInfoForm movieInfoForm = new GetMovieInfoForm();
         movieInfoForm = movieService.getMovieDetailInfo(movieInfo, subInfo);
         
         request.setAttribute("movieInfoForm", movieInfoForm);
         
      } else if(route.equals(Route.GET_MOVIE_INFOES.getRoute())) {
         String movieName = request.getParameter("movieName");
         String json = movieService.getMovieAPIInfo(movieName);
         
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         try {
            response.getWriter().write(json);
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else if(route.equals(Route.GET_MOVIE_MAIN.getRoute())) {
         List<GetMovieInfoForm> movieInfoFormList = new ArrayList<>();
         movieInfoFormList = movieService.getMovieDetailInfoList();
         for(GetMovieInfoForm m : movieInfoFormList) {
            System.out.println(m.getM_code());
            System.out.println(m.getM_title());
         }
               
         request.setAttribute("movieInfoFormList", movieInfoFormList);
      } else if(route.equals(Route.GET_MOVIE_DETAIL.getRoute())) {
         int movieCode = Integer.parseInt(request.getParameter("movieCode"));
         GetMovieInfoForm getMovieInfoForm = movieService.getSelectedMovieDetail(movieCode);
 
         List<Review> reviews = reviewService.getReviewList(movieCode);
         request.setAttribute("reviews", reviews);
         request.setAttribute("movieInfoForm", getMovieInfoForm);
      } else if(route.equals(Route.GET_MAIN.getRoute())) {
         List<GetMovieInfoForm> movieInfoFormList = new ArrayList<>();
         movieInfoFormList = movieService.getMovieDetailInfoList();
               
         request.setAttribute("movieInfoFormList", movieInfoFormList);
      }
   }
}