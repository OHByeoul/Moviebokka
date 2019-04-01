package com.revizio.moviebokka.service;

import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.revizio.moviebokka.constant.*;
import com.revizio.moviebokka.dao.MovieDAO;
import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.MovieInfo;
import com.revizio.moviebokka.serviceimpl.MovieServiceImpl;
import com.revizio.moviebokka.util.Crawling;
import com.revizio.moviebokka.util.MovieInfoRequest;


public class MovieService implements MovieServiceImpl{
	private MovieDAO movieDAO;
	private Crawling crawling;
	private MovieInfoRequest movieInfoRequest;
	private GetMovieInfoForm getMovieInfoForm;

	public MovieService() {
		movieDAO = MovieDAO.getInstance();
		crawling = new Crawling();
		movieInfoRequest = new MovieInfoRequest();
		getMovieInfoForm = new GetMovieInfoForm();
	}

	public String getMovieAPIInfo(String movieName) {
		return movieInfoRequest.getRequestURL(movieName);
	}

	public GetMovieInfoForm getMovieDetailInfo(MovieInfo movie, Map<String, String> subInfo) {
		int movieCode = movie.getM_code();

		if (!isExist(movieCode)) {
			String story = getMovieStory(movieCode);
			movie.setM_story(story);
			movieDAO.InsertMovieInfo(movie);
			for (Map.Entry<String, String> each : subInfo.entrySet()) {
				String key = each.getKey();
				if (key.equals(Constant.DIRECTOR)) {
					String value = each.getValue();
					splitSubInfo(movieCode, key, value);
				} else if (key.equals(Constant.ACTOR)) {
					String value = each.getValue();
					splitSubInfo(movieCode, key, value);
				}
			}
		}
		getMovieInfoForm = movieDAO.getMovieDetailInfo(movieCode);
		return getMovieInfoForm;
	}

	private String getMovieStory(int movieCode) {
		crawling.createDocument(Crawling.URL+Crawling.URL_POST+movieCode);
		Elements elements = crawling.createElements(Crawling.TARGET);
		for(Element e : elements) {
			System.out.println(e);
		}
		String text = crawling.htmlTotext(elements.toString());
		return text;		
	}

	private boolean isExist(int movieCode) {
		return movieDAO.isExist(movieCode);
	}

	private void splitSubInfo(int movieCode, String key, String value) {
		String[] temp;
		if (value.contains("|")) {
			temp = value.split("\\|");
			for (int i = 0; i < temp.length; i++) {
				movieDAO.InsertSubInfo(movieCode, key, temp[i]);
			}
		} else {
			movieDAO.InsertSubInfo(movieCode, key, value);
		}
	}
}