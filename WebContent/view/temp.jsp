<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="//code.jquery.com/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<style>
@charset "UTF-8";


/* Remove the navbar's default margin-bottom and rounded borders */ 
.navbar {
  margin-bottom: 0;
  border-radius: 0;
}
.body {
    background-color : #141414;
}

/* Add a gray background color and some padding to the footer */
footer {
  background-color: #f2f2f2;
  padding: 25px;
}

h3.h3{color:#E6E6E6;text-align:center;margin:1em;text-transform:capitalize;font-size:1.7em;}
.jumbotron{background-color : #141414;}
/********************* movie-grid **********************/
.movie-grid,.movie-grid .movie-image{position:relative}
.movie-grid{font-family:Poppins,sans-serif;z-index:1}
.movie-grid .movie-image a{display:block}
.movie-grid .movie-image img{width:70%;height:auto}
.movie-grid .pic-1{opacity:1;transition:all .5s ease-out 0s}
.movie-grid:hover .pic-1{opacity:0}
.movie-grid .pic-2{position:absolute;top:0;left:0;opacity:0;transition:all .5s ease-out 0s}
.movie-grid:hover .pic-2{opacity:1}
.movie-grid .movie-full-view{color:#141414;background-color:#141414;font-size:16px;height:45px;width:45px;padding:18px;border-radius:100px 0 0;display:block;opacity:0;position:absolute;right:0;bottom:0;transition:all .3s ease 0s}
.movie-grid .movie-full-view:hover{color:#c0392b}
.movie-grid:hover .movie-full-view{opacity:1}
.movie-grid .movie-content{padding:12px 12px 0;overflow:hidden;position:relative}
.movie-content .rating{padding:0;margin:0 0 7px;list-style:none}
.movie-grid .rating li{font-size:12px;color:#ffd200;transition:all .3s ease 0s}
.movie-grid .rating li.disable{color:rgba(0,0,0,.2)}
.movie-grid .title{font-size:16px;font-weight:400;text-transform:capitalize;margin:0 0 3px;transition:all .3s ease 0s}
.movie-grid .title a{color:#E6E6E6}
.movie-grid .title a:hover{color:#c0392b}
.movie-grid .gernre{color:#FE9A2E;font-size:17px;margin:0;display:block;transition:all .5s ease 0s}
.movie-grid:hover .gernre{opacity:0}
.movie-grid .add-to-cart{display:block;color:#c0392b;font-weight:600;font-size:14px;opacity:0;position:absolute;left:10px;bottom:-20px;transition:all .5s ease 0s}
.movie-grid:hover .add-to-cart{opacity:1;bottom:0}
@media only screen and (max-width:990px){.movie-grid{margin-bottom:30px}}

</style>
<div class = "container">
    <h3 class="h3">영화 TOP 10 </h3>
    <div class="top10"></div> <!-- 영화 별점으로 해서 값을 넘겨줌 -->
    <h3 class="h3">추천 영화</h3>
    <div class="recommand"></div>


    <div class="row">
       
    </div>
    
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
					</div>
				</div>
			</div>
		</div>

</div>

<script>
	$(function() {
		var cnt = 1;

		let $clone = $('.temp_movie').clone();
		let $target;
		let title;
		let img;
		let rating;
		
		<c:forEach var="item" items="${movieInfoFormList}">
			title = "${item.m_title}";
			img = "${item.m_img}";
			rating = ${item.m_user_rating}; 
			
			console.log(title);
			$clone = $('.temp_movie').clone();
			$clone.attr("class", "movie");
			$clone.attr("id", "movie" + cnt++);
			$clone.find('.title').find('a').html(title);
			$clone.find('.pic-1').attr("src", img);
			$clone.find('.pic-2').attr("src", img);
			$clone.find('.user_rating').html(rating);
			$clone.show();
					
			$target = $clone.find('.rating').find('.fa.fa-star');
			userRating(rating, $target);
			$('.top10').append($clone);
		</c:forEach>
		
		function userRating(rating, target) {
			let highStar = 4;
			let limit = Math.round(rating/2);
			console.log(limit);
			for (let i = highStar; i >= limit; i--) {
				target.eq(i).attr("class", "fa fa-star disable");
			}
		}
	});
</script>
</html>