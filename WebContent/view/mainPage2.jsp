<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./partial/header.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>영화보까</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
  <script src = "/Moviebokka/static/js/mainPage.js"></script>
 
  <style>
      .jumbotron{background-color : #141414;}
  </style>
</head>
<body class ="body">
<%
	String imagePath = request.getContextPath()+"/static/images/";
%>

<br>
<div class="jumbotron">
  <div class="container text-center">
     <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="<%=imagePath%>m1.jpg" alt="Los Angeles" style="width:100%;">
      </div>

      <div class="item">
        <img src="<%=imagePath%>m2.jpg" alt="Chicago" style="width:100%;">
      </div>
    
      <div class="item">
        <img src="<%=imagePath%>m3.jpg" alt="New york" style="width:100%;">
      </div>
      
      <div class="item">
        <img src="<%=imagePath%>m4.jpg" alt="Chicago" style="width:100%;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  </div>
</div>
<br>

<div class = "container">

   <div> <h3 class="h3">영화 TOP 10 </h3></div>
    <div class="top10"></div> <!-- 영화 별점으로 해서 값을 넘겨줌 -->
    <div><h3 class="h3">추천 영화</h3></div>
    <div class="recommand"></div>
    
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
<br>
<br>
</div>
    <br>
<br><br>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
<script>
$(function() {
	var cnt = 1;
	setMovieMain();

	function setMovieMain(){
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
			$('.top10').append($clone);
		</c:forEach>
	}
	
	function setUserRating(rating, target) {
		let highStar = 4;
		let limit = Math.round(rating/2);
		console.log(limit);
		for (let i = highStar; i >= limit; i--) {
			target.eq(i).attr("class", "fa fa-star disable");
		}
	}
	
	for(let i = 0; i<cnt; i++){
		$('.top10').on('click', '#movie'+i, function(){
			let movieCode = $(this).find('#movieCode').val();
			console.log(movieCode);
			location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
		});
	}
});
</script>

</body>
</html>