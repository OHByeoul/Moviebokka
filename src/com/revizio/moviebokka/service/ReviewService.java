package com.revizio.moviebokka.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dao.ReviewDAO;
import com.revizio.moviebokka.dto.Review;

public class ReviewService {
	private ReviewDAO reviewDAO;
	
	public ReviewService() {
		reviewDAO = ReviewDAO.getInstance();
	}

	public Review createReview(Review review) {
		Review getReview = new Review();
		review.setRev_regdate(getNowDate());
		boolean result = reviewDAO.createReview(review);
		int revId = reviewDAO.getReviewId();
		if(result && revId != 0) {
			getReview = reviewDAO.getReviewDetailInfo(revId);
		} 
		return getReview;
	}

	public boolean deleteSelectedReview(String revId) {
		int id = Integer.parseInt(revId); 
		return reviewDAO.deleteReview(id); 
	}

	public Review updateSelectedReview(String revId, String title, String content) {
		int id = Integer.parseInt(revId);
		boolean result = reviewDAO.updateSelectedReview(id,title,content);
		  
		return reviewDAO.getReviewDetailInfo(id);
	}

	public Review getSelectedReviewDetail(String revId) {
		int id = Integer.parseInt(revId); 
		return reviewDAO.getReviewDetailInfo(id);
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
		return reviewDAO.getReviewList(movieCode);
	}

	public List<Review> getSearchedReviewList(String search) {
		return reviewDAO.getSearchedReviewList(search);
	}
}
