package com.revizio.moviebokka.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dao.MovieDAO;
import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.MovieInfo;
import com.revizio.moviebokka.dto.Review;
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
      String genre = getMovieGenre(movieCode);
      subInfo.put("genre", genre);
      
      if (!isExist(movieCode)) {
         String story = getMovieStory(movieCode);
         movie.setM_story(story);
         movieDAO.InsertMovieInfo(movie);
         for (Map.Entry<String, String> each : subInfo.entrySet()) {
            String key = each.getKey();
            if (key.equals(Constants.DIRECTOR)) {
               String value = each.getValue();
               splitSubInfo(movieCode, key, value);
            } else if (key.equals(Constants.ACTOR)) {
               String value = each.getValue();
               splitSubInfo(movieCode, key, value);
            } else if (key.equals(Constants.GENRE)) {
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
   
   private String getMovieGenre(int movieCode) {
      crawling.createDocument(Crawling.URL+Crawling.URL_POST+movieCode);
      Elements elements = crawling.createElements(Crawling.TARGET2);
      for(Element e : elements) {
         System.out.println(e);
      }
      String text = crawling.htmlTotext(elements.first().toString());
      text = text.replace(",", "|");
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

   public List<GetMovieInfoForm> getMovieDetailInfoList() {
      List<GetMovieInfoForm> movieInfoes = new ArrayList<>();
      movieInfoes = movieDAO.getMovieDetailInfoList();
      for(GetMovieInfoForm form : movieInfoes) {
         String title = crawling.htmlTotext(form.getM_title());
         form.setM_title(title);
      }
      return movieInfoes;
   }

   public GetMovieInfoForm getSelectedMovieDetail(int movieCode) {
      return movieDAO.getMovieDetailInfo(movieCode);
   }

	public Review createReview(Review review) {
		Review getReview = new Review();
		review.setRev_regdate(getNowDate());
		boolean result = movieDAO.createReview(review);
		int revId = movieDAO.getReviewId();
		if(result && revId != 0) {
			getReview = movieDAO.getReviewDetailInfo(revId);
			System.out.println("review : "+getReview.getMem_id()+" "+getReview.getRev_content());
		} 
		return getReview;
	}

	private Date getNowDate() {
		SimpleDateFormat format = new SimpleDateFormat(Constants.DATEFORMAT);
		
		java.util.Date date = new java.util.Date();
		String formatedDate = format.format(date);
        System.out.println("date "+date);
        System.out.println("formatedDate "+formatedDate);
		Date nowDate = Date.valueOf(formatedDate);
		System.out.println(nowDate);
		return nowDate;
	}

	public List<Review> getReviewList(int movieCode) {
		return movieDAO.getReviewList(movieCode);
	}

	public Review getSelectedReviewDetail(String revId) {
		int id = Integer.parseInt(revId); 
		return movieDAO.getReviewDetailInfo(id);
	}

	public boolean deleteSelectedReview(String revId) {
		int id = Integer.parseInt(revId); 
		return movieDAO.deleteReview(id); 
	}

	public Review updateSelectedReview(String revId, String title, String content) {
		int id = Integer.parseInt(revId);
		boolean result = movieDAO.updateSelectedReview(id,title,content);
		  
		return movieDAO.getReviewDetailInfo(id);
	}

	public List<GetMovieInfoForm> getSearchedMovieList(String search) {
		List<GetMovieInfoForm> movieInfoes = new ArrayList<>();
	      movieInfoes = movieDAO.getSearchedMovieList(search);
	      for(GetMovieInfoForm form : movieInfoes) {
	         String title = crawling.htmlTotext(form.getM_title());
	         form.setM_title(title);
	      }
		return movieInfoes;
	}
}