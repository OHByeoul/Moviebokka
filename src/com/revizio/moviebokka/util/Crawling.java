package com.revizio.moviebokka.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawling {
   public static final String URL="https://movie.naver.com";
   public static final String URL_POST = "/movie/bi/mi/basic.nhn?code=";
   public static final String TARGET = ".story_area p.con_tx";
   public static final String TARGET2 = "dl.info_spec > .step9_cont > p.count"; // 관객수
   public static final String TARGET3 = "dl.info_spec > dd > p >span";
   private Document document;
   private Elements elements;

   public Crawling() {
      elements = new Elements();
      
   }
   
   public void createDocument(String url){
      try {
         document = Jsoup.connect(url).get();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public Elements createElements(String url) {
      if(!elements.isEmpty()) {
         clearElements();
      }
      elements = getDocument().select(url);       
      return elements;
   }
   
   public Elements addElements(String url) {
      if(elements.isEmpty()) {
         elements = getDocument().select(url);
      } else {
         elements.addAll(getDocument().select(url));
         System.out.println("size : "+elements.size());
      }
      return elements;
   } 
   
   public Document getDocument() {
      return document;
   }
   
   public void setDocument(Document document) {
      this.document = document;
   }
   
   public Elements getElements() {
      return elements;
   }

   public void setElements(Elements elements) {
      this.elements = elements;
   }
   
   public void clearElements() {
      this.elements.clear();
   }
   
   public String htmlTotext(String html) {
      return Jsoup.parse(html).text();
   }
}