package com.revizio.moviebokka.dto;

public class UserRecommand {
	private int rev_id;
	private String mem_email;
	private int recom_status;
	private int unrecom_status;
	
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public int getRecom_status() {
		return recom_status;
	}
	public void setRecom_status(int recom_status) {
		this.recom_status = recom_status;
	}
	public int getUnrecom_status() {
		return unrecom_status;
	}
	public void setUnrecom_status(int unrecom_status) {
		this.unrecom_status = unrecom_status;
	}
}
