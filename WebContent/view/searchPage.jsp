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
		                <span class="result-title">영화에서 검색 결과</span><span class="sub" id="movie_sub">더보기</span>
		                <div class="line"></div>
			                <div class="movie-result">
			                <!-- 영화 검색 결과 들어가야됨-->
			                </div>
		            </div>
		            <div class = "search-review-form">
		                <span class="result-title">리뷰에서 검색 결과</span><span class="sub" id="review_sub">더보기</span>
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
		    <input type="hidden" id="startNum" name="startNum" value="">
		    <input type="hidden" id="endNum" name="endNum" value="">
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
		let startM = "${startM}"=== ""? 1 : "${startM}";
		let endM = "${endM}"=== ""? 5 : "${endM}";
		let startR = "${startR}"=== ""? 1 : "${start}";
		let endR = "${endR}"===""? 5 : "${end}";
		
		getMovieResult();
		getSearchResult();
		
		function getMovieResult(){
			let $target;
			 <c:forEach  items="${movieInfoFormList}" var="item" varStatus="status">
				$clone = $('.temp_movie').clone();
				$clone.attr("class", "movie");
				$clone.attr("id", "movie" + cnt++);
				$clone.find('.title').find('a').html("${item.m_title}");
				$clone.find('.pic-1').attr("src", "${item.m_img}");
				$clone.find('.pic-2').attr("src", "${item.m_img}");
				$clone.find('.user_rating').html("${item.m_user_rating}");
				$clone.find('#movieCode').val("${item.m_code}");
				$clone.show();
						
				$target = $clone.find('.rating').find('.fa.fa-star');
				setUserRating("${item.m_user_rating}", $target);
				$('.movie-result').append($clone);		
				function setUserRating(rating, target) {
					let highStar = 4;
					let limit = Math.round(rating/2);
					console.log(limit);
					for (let i = highStar; i >= limit; i--) {
						target.eq(i).attr("class", "fa fa-star disable");
					}
				}
			</c:forEach>
		}
		function getSearchResult() {
			 <c:forEach  items="${reviewLists}" var="item" varStatus="status">
				temp="";
				temp+="<tr>"
					temp+='<td>${item.rev_id}</td>';
					temp+='<td><a href="/Moviebokka/review/getSelectedReview?revId=${item.rev_id}">${item.rev_title}</a></td>';
					temp+='<td>${item.mem_nick}</td>';
					temp+='<td>${item.rev_regdate}</td>';
					temp+="</tr>";
				$('#tbody').append(temp);
			</c:forEach>
			$('#input').val("${search}");
			$('#temp-review').show();
			clickedEvent();
			
		}
		 $('#search').on('click', function(){
		        let $input = $(this).closest('#custom-search-input').find('#input').val();
		        let start =1;
		        let end = 5;
		        
		        console.log($input);
		        location.href="/Moviebokka/search/searchContents?search="+$input+"&startNum="+start+"&endNum="+end;
		    
		 });
		 
		 $('#review_sub').on('click', function(){
			let $input = "${search}";
			
			let s = Number(startR)+5;
			let e = Number(endR)+5;
			startR = String(s);
			endR = String(e);
			 $.getJSON('/Moviebokka/search/searchReviewMore',{search : $input, startNum : startR, endNum:endR},function(result){
		        		if(result.length === 0){
		        			let empty = "<div>검색된 결과가 없습니다.</div>";
		        			$('.review-result').append(empty);
		        		}
		        	$.each(result, function(i){
		        		console.log(result);
		        		searchReview(result[i].rev_id,result[i].rev_title,result[i].rev_regdate,result[i].mem_nick);
		        	});
		        });
			 
		 });
		 
		 $('#movie_sub').on('click', function(){
			 let $input = "${search}";
				
			let s = Number(startM)+5;
			let e = Number(endM)+5;
			startM = String(s);
			endM = String(e);
			$.getJSON('/Moviebokka/search/searchMovieMore',{search : $input, startNum : startM, endNum:endM}, function(result){
				if(result.length === 0){
        			let empty = "<div>검색된 결과가 없습니다.</div>";
        			$('.movie-result').append(empty);
        		}
				$.each(result, function(i){
	        		console.log(result);
	        		searchMovie(result[i].m_title,result[i].m_img,result[i].m_user_rating,result[i].m_code);
	        		clickedEvent();
	        	});
			});
		 });
		 
		 function searchReview(rev_id,rev_title,rev_regdate,mem_nick){
			 let $review = $('#temp-review');
			 
			let temp="";
				temp+='<tr>';
				temp+='<td>'+rev_id+'</td>';
				temp+='<td><a href="/Moviebokka/review/getSelectedReview?revId='+rev_id+'">'+rev_title+'</a></td>';
				temp+='<td>'+mem_nick+'</td>';
				temp+='<td>'+rev_regdate+'</td>';
				temp+="</tr>";
				$('#tbody').append(temp);
		
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
			//	$('.search-movie-form').css("margin-bottom",80*cnt);
		
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