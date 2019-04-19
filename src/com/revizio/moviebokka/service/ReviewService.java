package com.revizio.moviebokka.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dao.ReviewDAO;
import com.revizio.moviebokka.dao.UserDAO;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.dto.UserRecommand;

public class ReviewService {
	private ReviewDAO reviewDAO;
	private UserDAO userDAO;
	
	public ReviewService() {
		reviewDAO = ReviewDAO.getInstance();
		userDAO = UserDAO.getInstance();
	}

	public Review createReview(Review review) {
		Review getReview = new Review();
		review.setRev_regdate(getNowDate());
		boolean result = reviewDAO.createReview(review);
		int revId = reviewDAO.getReviewId();
		
		System.out.println("createreview service out");
		System.out.println("result "+result);
		System.out.println("revId "+revId);
		if(result && revId != 0) {
			System.out.println("inninininin");
			getReview = reviewDAO.getReviewDetailInfo(revId);
			System.out.println(getReview.getRev_content());
			
		} 
		return getReview;
	}

	public boolean deleteSelectedReview(String revId) {
		int id = Integer.parseInt(revId);
		return reviewDAO.deleteReview(id, Constants.DELETE_REIVIEW_MSG); 
	}

	public Review updateSelectedReview(String revId, String title, String content) {
		int id = Integer.parseInt(revId);
		boolean result = reviewDAO.updateSelectedReview(id,title,content);
		 if(!result) {
			  //exception
		  }  
		return reviewDAO.getReviewDetailInfo(id);
	}

	public Review getSelectedReviewDetail(String revId) {
		int id = Integer.parseInt(revId); 
		boolean result = reviewDAO.updateViewCnt(id);
		
		if(!result) {
			
		}
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

	public List<Review> getSearchedReviewList(String search, String startNum, String endNum) {
		int start = Integer.parseInt(startNum);
		int end = Integer.parseInt(endNum);
		return reviewDAO.getSearchedReviewList(search,start,end);
	}

	public void updateRecommand(String revId, String status) {
		int id = Integer.parseInt(revId); 
		boolean isReccomand = Boolean.parseBoolean(status);
		System.out.println("isrecom : "+isReccomand);
		boolean result = reviewDAO.updateRecommand(id,isReccomand);
		if(!result) {
			//exception
		}
	}

	public void updateUnrecommand(String revId, String status) {
		int id = Integer.parseInt(revId); 
		boolean isReccomand = Boolean.parseBoolean(status);
		boolean result = reviewDAO.updateUnrecommand(id,isReccomand);
		if(!result) {
			//exception
		}
		
	}

	public boolean checkUserRecommand(String revId,String memEmail, String status, String unStatus) {
		int id = Integer.parseInt(revId);
		boolean isReccomand = Boolean.parseBoolean(status);
		boolean isUnreccomand = Boolean.parseBoolean(unStatus);
		int recom = 0;
		int unrecom = 0;
		if(isReccomand) {
			recom = 1;
		}
		if(isUnreccomand) {
			unrecom = 1;
		}
//		if(!(isReccomand && isUnreccomand)) {
//			if(isExist(id,memEmail)) {
//				System.out.println("delete recom");
//				return reviewDAO.deleteUserRecommand(id, memEmail);
//			}
//		}
		if(!isExist(id,memEmail)) {
			return reviewDAO.createUserRecommand(id, memEmail,recom,unrecom);
		}
		return reviewDAO.updateUserRecommand(id,memEmail,recom,unrecom);
	}

	private boolean isExist(int id, String memEmail) {
		return reviewDAO.checkRecomExist(id,memEmail);
	}

	public UserRecommand getUserRecommandStatus(String revId, String memEmail) {
		int id = Integer.parseInt(revId);
		UserRecommand userRecommand = reviewDAO.getReivewRecomInfo(id, memEmail);
		return userRecommand; 
	}
}
