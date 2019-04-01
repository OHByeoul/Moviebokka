package com.revizio.moviebokka.serviceimpl;

import java.util.Map;

import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.MovieInfo;

public interface MovieServiceImpl {
	String getMovieAPIInfo(String movieName);
	GetMovieInfoForm getMovieDetailInfo(MovieInfo movie, Map<String, String> subInfo);
	
}
