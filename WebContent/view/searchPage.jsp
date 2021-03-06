<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="./partial/header.jsp"/>
<jsp:include page="./partial/navbar.jsp" />
<html> 
<head>
<link rel="stylesheet" href="/Moviebokka/static/css/searchPage.css">
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css"> 
<style>
.container-form .input-group {
   padding: 30px 0 !important; 
}

.label-result {
    padding: 10px !important;
    font-size: 12px !important;
}
td {
	text-align : center;
}

.line-result {
    margin: 10px 0 !important;
}

.community,
.review {
    margin-top: 20px !important;
}
</style>
</head>
<body>
<div class="container container-form">
    <div class="form-group-row col-sm-12">
        <div class="col-sm-12">
            <form action="" class="form" id="search_form" method="get">
                <div class="input-group">
                    <span class="input-group-btn">
                        <button class="btn btn-default btn-lg" type="button" disabled>전체 검색</button>
                    </span>
                    <input type="text" class="form-control input-lg" placeholder="search" name="movieName" id="input">
                    <span class="input-group-btn">
                        <button class="btn btn-default btn-lg" id="search" type="button">검 색</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
    <div class="form-group-row col-sm-12">
        <div class="col-sm-12 movie">
            <span class="label label-default label-result">영화 검색 결과</span>
            <button class="btn btn-default btn-sm pull-right" id="movie_sub">더보기</button>
        </div>
        <div class="col-sm-12"><hr class="line-result" /></div>
        <div class="col-sm-12 movie-result"><!-- 영화 결과 들어감. --></div>
    </div>
    <div class="form-group-row col-sm-12 review">
        <div class="col-sm-12">
            <span class="label label-default label-result">리뷰 검색 결과</span>
            <button class="btn btn-default btn-sm pull-right" id="review_sub">더보기</button>
        </div>
        <div class="col-sm-12"><hr class="line-result" /></div>
			<div class="col-sm-12 review-result">
				<!-- 리뷰 결과 들어감. -->
				<table class="table table-hover" id="temp-review"
					style="display: none">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>닉네임</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody id="tbody">

					</tbody>
				</table>
			</div>
		</div>
		<!-- 
    <div class="form-group-row col-sm-12 community">
        <div class="col-sm-12">
            <span class="label label-default label-result">커뮤니티 검색 결과</span>
            <button class="btn btn-default btn-sm pull-right" >더보기</button>
        </div>
        <div class="col-sm-12"><hr class="line-result" /></div>
        <div class="col-sm-12 community-result"><!-- 커뮤니티 결과 들어감. </div>
    </div> -->
 
</div>

  <div class="temp_movie" style="display: none">
    <div class="col-md-2 col-sm-6"> 
        <div class="movie-grid">
            <div class="movie-image">
                <a href="#"> <img class="pic-1" src="" onerror="this.src='/Moviebokka/static/images/no-photo.jpg'" alt="이미지 없음"> <img
                    class="pic-2" src="" onerror="this.src='/Moviebokka/static/images/not-available.jpg'" alt="이미지 없음">
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
                <h3 class="title title_clip">
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
				$('.col-sm-12.movie-result').append($clone);	
				
				function setUserRating(rating, target) {
					let highStar = 4;
					let limit = Math.round(rating/2);
					
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
		        let $input = $('#input').val();
		        let start =1;
		        let end = 5;
		        
		        console.log($input);
		        location.href="/Moviebokka/search/searchContents?search="+$input+"&startNum="+start+"&endNum="+end;
		    
		 });
		 
		 $('#review_sub').on('click', function(){
			 console.log("review more clicked!");
			let $input = "${search}";
			
			let s = Number(startR)+5;
			let e = Number(endR)+5;
			startR = String(s);
			endR = String(e);
			 $.getJSON('/Moviebokka/search/searchReviewMore',{search : $input, startNum : startR, endNum:endR},function(result){
		        		if(result.length === 0){
		        		//	let empty = "<div>검색된 결과가 없습니다.</div>";
		        		//	$('.line-result').append(empty);
		        		}
		        	$.each(result, function(i){
		        		console.log(result);
		        		searchReview(result[i].rev_id,result[i].rev_title,result[i].rev_regdate,result[i].mem_nick);
		        	});
		        });
			 
		 });
		 
		 $('#movie_sub').on('click', function(){
			 console.log('movie more cliked!');
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


<jsp:include page="./partial/footer.jsp" />
</body>
</html>