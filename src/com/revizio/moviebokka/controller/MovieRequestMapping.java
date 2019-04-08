package com.revizio.moviebokka.controller;

import java.io.IOException;
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
import com.revizio.moviebokka.service.UserService;

public class MovieRequestMapping implements RequestDispatcher {
   private MovieService movieService;
   private UserService userService;
   private HttpSession session;
   
   public MovieRequestMapping() {
      movieService = new MovieService();
      userService = new UserService();
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
    	 int movieCode = Integer.parseInt(request.getParameter("movieCode"));
         String title = request.getParameter("title");
         String content = request.getParameter("content");
         int memId = Integer.parseInt(request.getParameter("memId"));
         String nick = request.getParameter("nick");
         String ip = userService.getClientIP(request);
         Review review = new Review(title,content,memId,nick,movieCode,ip);
//         review.setRev_title(title);
//         review.setRev_content(content);
//         review.setMem_nick(nick);
//         review.setM_code(movieCode);
//         review.setMem_id(memId);
//         review.setRev_ip(ip);
         // recommand랑 unrecommand는 서비스에서 받고 
         // todo : setatt mem, review,
         Review getDetailReview = movieService.createReview(review);
         request.setAttribute("reviewDetail", getDetailReview);
         System.out.println("last innn "+movieCode+" "+title+" "+content+" "+ip+" "+nick);
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
 
         List<Review> reviews = movieService.getReviewList(movieCode);
         System.out.println("reviews : "+reviews.get(0).getMem_id()+" "+reviews.get(0).getRev_content());
         request.setAttribute("reviews", reviews);
         request.setAttribute("movieInfoForm", getMovieInfoForm);
      } else if(route.equals(Route.GET_MAIN.getRoute())) {
         List<GetMovieInfoForm> movieInfoFormList = new ArrayList<>();
         movieInfoFormList = movieService.getMovieDetailInfoList();
               
         request.setAttribute("movieInfoFormList", movieInfoFormList);
      }
   }
}