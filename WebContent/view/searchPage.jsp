<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="./partial/header.jsp"/>
<html> 
	<head>
	<link rel="stylesheet" href="/Moviebokka/static/css/searchPage.css">
	<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
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
		            <div class = "search-movie-form" >
		                <span class="result-title">영화에서 검색 결과</span><span class="sub">더보기</span>
		                <div class="line"></div>
			                <div class="movie-result">
			                <!-- 영화 검색 결과 들어가야됨-->
			                </div>
		            </div>
		            <div class = "search-review-form">
		                <span class="result-title">리뷰에서 검색 결과</span><span class="sub">더보기</span>
		                <div class="line"></div>
		                <div class="review-result">
		                	<table class="table table-hover" id="temp-review" style="display:none">
							    <thead>
							      <tr>
							        <th>번호</th>
							        <th>제목</th>
							        <th>닉네임</th>
							        <th>날짜</th>
							      </tr>
							    </thead>
							    <tbody id = "tbody">
							     
							    </tbody>
							  </table>
		                </div>
		                <!-- 리뷰 검색 결과 들어가야됨-->
		            </div>
		            <div class = "search-community-form">
		                <span class="result-title">커뮤니티에서 검색 결과</span><span class="sub">더보기</span>
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
		var cnt = 1;
		var rev = 1;
		var page = 1;
		 $('#search').on('click', function(){
		        let $input = $(this).closest('#custom-search-input').find('#input').val();
		        console.log($input);
		        
		        $.getJSON('/Moviebokka/movie/searchMovies',{search : $input},function(result){
		        	$('.movie-result').empty();
		        	$.each(result, function(i){
		        		searchMovie(result[i].m_title,result[i].m_img,result[i].m_user_rating,result[i].m_code);
		        		clickedEvent();
		        	});
		        });
		        
		        $.getJSON('/Moviebokka/review/searchReviews',{search : $input},function(result){
		        	$('#tbody').empty();
		        	$.each(result, function(i){
		        		searchReview(result[i].rev_id,result[i].rev_title,result[i].rev_regdate,result[i].mem_nick);
		        	});
		        });
		   
		  });
		 
		 function searchReview(rev_id,rev_title,rev_regdate,mem_nick){
			 let $review = $('#temp-review');
			 
				let temp = "";
					temp="";
						temp+='<tr>';
						temp+='<td>'+rev_id+'</td>';
						temp+='<td><a href="/Moviebokka/review/getSelectedReview?revId='+rev_id+'">'+rev_title+'</a></td>';
						temp+='<td>'+mem_nick+'</td>';
						temp+='<td>'+rev_regdate+'</td>';
						temp+="</tr>";
						$review.find('#tbody').append(temp);
				$review.show();
		 }
		 
		 
		function searchMovie(m_title, m_img, m_rating,m_code){
			let $clone = $('.temp_movie').clone();
			let $target;
			let title = m_title;
			let img = m_img;
			let rating = m_rating;
			let code = m_code;
				
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
				$('.search-movie-form').css("margin-bottom",80*cnt);
		
			function setUserRating(rating, target) {
				let highStar = 4;
				let limit = Math.round(rating/2);
				console.log(limit);
				for (let i = highStar; i >= limit; i--) {
					target.eq(i).attr("class", "fa fa-star disable");
				}
			}
		}
		
		function clickedEvent(){
			for(let i = 0; i<cnt; i++){
				$('body').on('click', '#movie'+i, function(){
					let movieCode = $(this).find('#movieCode').val();
					console.log(movieCode);
					location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
				});
			}
		}
	});
		   
 </script>

	</body>
</html>