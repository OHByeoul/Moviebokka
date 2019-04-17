package com.revizio.moviebokka.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dao.CommentDAO;
import com.revizio.moviebokka.dto.ReviewComment;

public class CommentService {
	private CommentDAO commentDAO;
	
	public CommentService() {
		commentDAO = CommentDAO.getInstance();
	}

	public void createReviewComment(ReviewComment reviewComment, String input, String ip) {
		reviewComment.setCom_content(input);
		reviewComment.setCom_ip(ip);
		reviewComment.setCom_regdate(getNowDate());
		boolean result = commentDAO.createReviewComment(reviewComment);
		if(!result) {
			System.out.println("createReviewCOmment fail");
		}
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

}
