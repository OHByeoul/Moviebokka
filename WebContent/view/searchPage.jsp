<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="./partial/header.jsp"/>
<html> 
	<head>
	<link rel="stylesheet" href="/Moviebokka/static/css/searchPage.css">
	</head>
	<body>
		 <div class="container">
		    <div class="row">
		          <div class="col-md-6">
		          <h2></h2>
		              <div id="custom-search-input">
		                  <div class="input-group col-md-12">
		                      <input type="text" class="form-control input-lg" id="input" placeholder="search" />
		                      <span class="input-group-btn">
		                          <button class="btn btn-info btn-lg" id="search" type="button">
		                              <i class="glyphicon glyphicon-search"></i>
		                          </button>
		                      </span>
		                  </div>
		              </div>
		          </div>
		    </div>
		    <div class = "search-result-form">
		        <div class = "search-in-movie">
		            <div class = "search-movie-form">
		                <span class="title">영화에서 검색 결과</span><span class="sub">더보기</span>
		                <div class="line"></div>
		                <div class="movie-result"></div>
		                <!-- 영화 검색 결과 들어가야됨-->
		            </div>
		            <div class = "search-review-form">
		                <span class="title">리뷰에서 검색 결과</span><span class="sub">더보기</span>
		                <div class="line"></div>
		                <div class="review-result"></div>
		                <!-- 리뷰 검색 결과 들어가야됨-->
		            </div>
		            <div class = "search-community-form">
		                <span class="title">커뮤니티에서 검색 결과</span><span class="sub">더보기</span>
		                <div class="line"></div>
		                <div class="community-result"></div>
		                <!-- 커뮤니티 검색 결과 들어가야됨-->
		            </div>
		        </div>
		    </div>
		  </div>
		  <!-- 영화 보여주는 form -->
		  <div class="temp_movie" style="display: none">
			<div class="col-md-2 col-sm-6">
				<div class="movie-grid">
					<div class="movie-image">
						<a href="#"> <img class="pic-1" src="" alt="이미지 없음"> <img
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
						<div class="gernre">
							
						</div>
						<div class= "user_rating">
							
						</div>
						<a class="add-to-cart" href="">줄거리 보기</a>
						<input type="hidden" id="movieCode" name="movieCode" value="">
					</div>
				</div>
			</div>
		</div>
<script>
	$(function(){
		 $('#search').on('click', function(){
		        let $input = $(this).closest('#custom-search-input').find('#input').val();
		        console.log($input);
		  });
		function searchMovie(){
			let $clone = $('.temp_movie').clone();
			let $target;
			let title;
			let img;
			let rating;
			let code;
			
			<c:forEach var="item" items="${movieInfoFormList}">
				title = "${item.m_title}";
				img = "${item.m_img}";
				rating = ${item.m_user_rating};
				code = ${item.m_code};
				
				console.log(title);
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
				$('.movie-result').append($clone);
			</c:forEach>
		
		function setUserRating(rating, target) {
			let highStar = 4;
			let limit = Math.round(rating/2);
			console.log(limit);
			for (let i = highStar; i >= limit; i--) {
				target.eq(i).attr("class", "fa fa-star disable");
			}
		}
		
		for(let i = 0; i<cnt; i++){
			$('.movie-result').on('click', '#movie'+i, function(){
				let movieCode = $(this).find('#movieCode').val();
				console.log(movieCode);
				location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
			});
		}
	});
		   
 </script>

	</body>
</html>