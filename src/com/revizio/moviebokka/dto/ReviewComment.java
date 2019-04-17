package com.revizio.moviebokka.dto;

import java.sql.Date;

public class ReviewComment {
	private int com_id;
	private String com_content;
	private Date com_regdate;
	private String com_group;
	private String com_depth;
	private String com_order;
	private String com_ip;
	private String com_data_box;
	private int mem_id;
	private String mem_nick;
	private int rev_id;
	
	
	public ReviewComment() {
		
	}
	
	public ReviewComment(String input, String group, String depth, String order, int mem_id, String mem_nick,int rev_id,String dataBox) {
		this.com_content = input;
		this.com_group = group;
		this.com_depth = depth;
		this.com_order = order;
		this.mem_id = mem_id;
		this.mem_nick = mem_nick;
		this.rev_id = rev_id;
		this.com_data_box = dataBox;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	public Date getCom_regdate() {
		return com_regdate;
	}
	public void setCom_regdate(Date com_regdate) {
		this.com_regdate = com_regdate;
	}
	public String getCom_group() {
		return com_group;
	}
	public void setCom_group(String com_group) {
		this.com_group = com_group;
	}
	public String getCom_depth() {
		return com_depth;
	}
	public void setCom_depth(String com_depth) {
		this.com_depth = com_depth;
	}
	public String getCom_order() {
		return com_order;
	}
	public void setCom_order(String com_order) {
		this.com_order = com_order;
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
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	public String getCom_ip() {
		return com_ip;
	}
	public void setCom_ip(String com_ip) {
		this.com_ip = com_ip;
	}
	
	public String getCom_data_box() {
		return com_data_box;
	}

	public void setCom_data_box(String com_data_box) {
		this.com_data_box = com_data_box;
	}
}
