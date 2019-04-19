package com.revizio.moviebokka.dto;

import java.sql.Date;

public class Review {
	private int rev_id;
	private String rev_title;
	private String rev_content;
	private int rev_recommand;
	private int rev_unrecommand;
	private Date rev_regdate;
	private String rev_ip;
	private int rev_view;
	private int m_code;
	private int mem_id;
	private String mem_nick;
	private String rev_del;
	
	

	public Review() {
	}
	
	public Review(String title, String content, int memId, String nick, int movieCode, String ip) {
		this.rev_title = title;
		this.rev_content = content;
		this.mem_id = memId;
		this.mem_nick = nick;
		this.m_code = movieCode;
		this.rev_ip = ip;
		this.rev_recommand = 0;
		this.rev_unrecommand = 0;
		this.rev_view = 0;
	}
	
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	public String getRev_title() {
		return rev_title;
	}
	public void setRev_title(String rev_title) {
		this.rev_title = rev_title;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
	public int getRev_recommand() {
		return rev_recommand;
	}
	public void setRev_recommand(int rev_recommand) {
		this.rev_recommand = rev_recommand;
	}
	public int getRev_unrecommand() {
		return rev_unrecommand;
	}
	public void setRev_unrecommand(int rev_unrecommand) {
		this.rev_unrecommand = rev_unrecommand;
	}
	public Date getRev_regdate() {
		return rev_regdate;
	}
	public void setRev_regdate(Date rev_regdate) {
		this.rev_regdate = rev_regdate;
	}
	public String getRev_ip() {
		return rev_ip;
	}
	public void setRev_ip(String rev_ip) {
		this.rev_ip = rev_ip;
	}
	public int getRev_view() {
		return rev_view;
	}
	public void setRev_view(int rev_view) {
		this.rev_view = rev_view;
	}
	public int getM_code() {
		return m_code;
	}
	public void setM_code(int m_code) {
		this.m_code = m_code;
	}
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getRev_del() {
		return rev_del;
	}

	public void setRev_del(String rev_del) {
		this.rev_del = rev_del;
	}
}

