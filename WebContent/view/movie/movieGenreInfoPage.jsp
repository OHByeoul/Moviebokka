<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />

<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
	<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
    </head>
    <body>
            <div>
                <h3 class="h3">장르병 영화</h3>
                </div>
                <div class="genre_movie">
                	
               
                </div>
        
                <div class="temp_movie" style="display: none">
                    <div class="col-md-2 col-sm-6">
                        <div class="movie-grid">
                            <div class="movie-image">
                                <a href="#"> <img class="pic-1" src="" onerror="this.src='/Moviebokka/static/images/no-photo.jpg'"alt="임시 이미지"> <img
                                    class="pic-2" src="" alt="이미지 없음">
                                </a> <a href="#" class="fa fa-search movie-full-view"></a>
                            </div>
                            <div class="movie-content">
                                <ul class="rating">
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                </ul>
                                <h3 class="title">
                                    <a href="#"></a>
                                </h3>
                                <div class="gernre"></div>
                                <div class="user_rating"></div>
                                <a class="add-to-cart" href="">줄거리 보기</a> <input type="hidden"
                                    id="movieCode" name="movieCode" value="">
                            </div>
                        </div>
                    </div>
                </div>
        
        
        <script>
          $(function(){
        	  var cnt = 1;
      		setMovieGenre();
      		
      		function setMovieGenre(){
      			let $clone = $('.temp_movie').clone();
      			let $target;
      			let title;
      			let img;
      			let rating;
      			let code;
      			let getClone;
      			
      			<c:forEach var="item" items="${movies}">
      				title = "${item.m_title}";
      				img = "${item.m_img}";
      				rating = ${item.m_user_rating};
      				code = ${item.m_code};
      				
      				console.log(title);
      				getClone = setTempAttribute(title, img, rating, code);
      				
      				$('.genre_movie').append(getClone);
      			</c:forEach>
      		}
      		
      		function setTempAttribute(title, img, rating, code){
      			$clone = $('.temp_movie').clone();
      			$clone.attr("class", "movie");
      			$clone.attr("id", "movie" + cnt++);
      			$clone.find('.title').find('a').html(title);
      			$clone.find('.pic-1').attr("src", img);
      			$clone.find('.pic-2').attr("src", img);
      			$clone.find('.user_rating').html(rating);
      			$clone.find('#movieCode').val(code);
      			$clone.show();
      					
      			$target = $clone.find('.rating').find('.fa.fa-star');
      			setUserRating(rating, $target);
      			return $clone;
      		}
      		
      		function setUserRating(rating, target) {
      			let highStar = 4;
      			let limit = Math.round(rating/2);
      			console.log(limit);
      			for (let i = highStar; i >= limit; i--) {
      				target.eq(i).attr("class", "fa fa-star disable");
      			}
      		} 
          });
            
        </script>
    </body>
    </html>