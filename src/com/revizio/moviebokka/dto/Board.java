package com.revizio.moviebokka.dto;

import java.util.Date;

public class Board {
   private int myListNum;
   private int brd_id;
   private int brd_type;
   private int mem_id;
   private String mem_nick;
   private String brd_title;
   private String brd_content;
   private int brd_ref;
   private int brd_step;
   private int brd_level;
   private String brd_ip;
   private Date brd_regdate;
   private int brd_cnt;
   private int brd_recom;
   private int brd_unrecom;
   
   
   
   public int getMyListNum() {
      return myListNum;
   }
   public void setMyListNum(int myListNum) {
      this.myListNum = myListNum;
   }
   public int getBrd_id() {
      return brd_id;
   }
   public void setBrd_id(int brd_id) {
      this.brd_id = brd_id;
   }
   public int getBrd_type() {
      return brd_type;
   }
   public void setBrd_type(int brd_type) {
      this.brd_type = brd_type;
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
   public String getBrd_title() {
      return brd_title;
   }
   public void setBrd_title(String brd_title) {
      this.brd_title = brd_title;
   }
   public String getBrd_content() {
      return brd_content;
   }
   public void setBrd_content(String brd_content) {
      this.brd_content = brd_content;
   }
   public int getBrd_ref() {
      return brd_ref;
   }
   public void setBrd_ref(int brd_ref) {
      this.brd_ref = brd_ref;
   }
   public int getBrd_step() {
      return brd_step;
   }
   public void setBrd_step(int brd_step) {
      this.brd_step = brd_step;
   }
   public int getBrd_level() {
      return brd_level;
   }
   public void setBrd_level(int brd_level) {
      this.brd_level = brd_level;
   }
   public String getBrd_ip() {
      return brd_ip;
   }
   public void setBrd_ip(String brd_ip) {
      this.brd_ip = brd_ip;
   }
   public Date getBrd_regdate() {
      return brd_regdate;
   }
   public void setBrd_regdate(Date brd_regdate) {
      this.brd_regdate = brd_regdate;
   }
   public int getBrd_cnt() {
      return brd_cnt;
   }
   public void setBrd_cnt(int brd_cnt) {
      this.brd_cnt = brd_cnt;
   }
   public int getBrd_recom() {
      return brd_recom;
   }
   public void setBrd_recom(int brd_recom) {
      this.brd_recom = brd_recom;
   }
   public int getBrd_unrecom() {
      return brd_unrecom;
   }
   public void setBrd_unrecom(int brd_unrecom) {
      this.brd_unrecom = brd_unrecom;
   }
   public Board(int brd_id,String brd_title,String brd_content) {
      this.brd_id = brd_id;
      this.brd_title = brd_title;
      this.brd_content = brd_content;
   }
   public Board() {

   }
   
   
   
}