package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.MovieInfo;
import com.revizio.moviebokka.service.MovieService;

public class MovieRequestMapping implements RequestDispatcher {
	private MovieService movieService;
	
	public MovieRequestMapping() {
		movieService = new MovieService();
	}
	
	@Override
	public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
		if(route.equals(Route.CREATE_REVIEW.getRoute())) {
			String hello = request.getParameter("hi");
			System.out.println("mapping : "+Route.CREATE_REVIEW.getRoute());
			
		} else if(route.equals(Route.GET_MOVIE_INFO.getRoute())) {
			int code = Integer.parseInt(request.getParameter("code"));
			String title = request.getParameter("title");
			String img = request.getParameter("img");
			String userRating = request.getParameter("userRating");			
			MovieInfo movieInfo = new MovieInfo(code,title,img,userRating);
			
			//String genre = request.getParameter("genre");
			//Genre genre = new Genre(code, genre);
			String director = request.getParameter("director");
			String actor = request.getParameter("actor");
			Map<String, String> subInfo = new HashMap<String, String>();
			//subInfo.add(genre);
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
		}
	}

}
