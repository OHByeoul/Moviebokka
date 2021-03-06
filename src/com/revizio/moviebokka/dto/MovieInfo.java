package com.revizio.moviebokka.dto;

public class MovieInfo {
	private int m_code;
	private String m_title;
	private String m_img;
	private String m_userRating;
	private String m_story;
	private String m_pub_date;
	private String m_cnt;

	public MovieInfo() {
	}
	
	public MovieInfo(int code, String title, String img, String userRating,String pubDate) {
		this.m_code = code;
		this.m_title = title;
		this.m_img = img;
		this.m_userRating = userRating;
		this.m_pub_date = pubDate;
		this.m_cnt = "0";
	}
	
	public int getM_code() {
		return m_code;
	}
	
	public void setM_code(int m_code) {
		this.m_code = m_code;
	}
	
	public String getM_title() {
		return m_title;
	}
	
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	
	public String getM_img() {
		return m_img;
	}
	
	public void setM_img(String m_img) {
		this.m_img = m_img;
	}
	
	public String getM_userRating() {
		return m_userRating;
	}
	
	public void setM_userRating(String m_userRating) {
		this.m_userRating = m_userRating;
	}
	
	public String getM_story() {
		return m_story;
	}
	
	public void setM_story(String m_story) {
		this.m_story = m_story;
	}
	
	public String getM_pub_date() {
		return m_pub_date;
	}

	public void setM_pub_date(String m_pub_date) {
		this.m_pub_date = m_pub_date;
	}
	
	public String getM_cnt() {
		return m_cnt;
	}

	public void setM_cnt(String m_cnt) {
		this.m_cnt = m_cnt;
	}
}
