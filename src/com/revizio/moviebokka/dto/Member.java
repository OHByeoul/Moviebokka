package com.revizio.moviebokka.dto;

import java.util.Date;

public class Member {
   private int mem_id;
   private String mem_nick;
   private String mem_email;
   private String mem_pass;
   private int mem_auth;
   private String mem_pic;
   private Date mem_regdate;
   private String mem_ip;
   
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
   public int getMem_auth() {
      return mem_auth;
   }
   public void setMem_auth(int mem_auth) {
      this.mem_auth = mem_auth;
   }
   public String getMem_pic() {
      return mem_pic;
   }
   public void setMem_pic(String mem_pic) {
      this.mem_pic = mem_pic;
   }
   public Date getMem_regdate() {
      return mem_regdate;
   }
   public void setMem_regdate(Date mem_regdate) {
      this.mem_regdate = mem_regdate;
   }
   public String getMem_ip() {
      return mem_ip;
   }
   public void setMem_ip(String mem_ip) {
      this.mem_ip = mem_ip;
   }
}