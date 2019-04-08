package com.revizio.moviebokka.dto;

public class Member1 {
	private int mem_id;
	private String mem_email;
	private String mem_pass;
	private String mem_hash_pass;
	private int mem_auth;
	
	public Member1() {
		// TODO Auto-generated constructor stub
	}
	
	public Member1(String mem_email, String mem_pass, String mem_hash_pass, int mem_auth) {
		super();
		this.mem_email = mem_email;
		this.mem_pass = mem_pass;
		this.mem_hash_pass = mem_hash_pass;
		this.mem_auth = mem_auth;
	}
	
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_hash_pass() {
		return mem_hash_pass;
	}
	public void setMem_hash_pass(String mem_hash_pass) {
		this.mem_hash_pass = mem_hash_pass;
	}
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
}
