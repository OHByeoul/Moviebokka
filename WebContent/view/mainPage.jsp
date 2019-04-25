<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String imagePath = request.getContextPath() + "/static/images/";
%>
<jsp:include page="./partial/header.jsp" />
<jsp:include page="./partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">

<title>영화보까</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
</style>
</head>
<body class="body">
	<!-- carouselStart -->
	<div class="padding">
		<div class="jumbotron">
			<div class="text-center">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<div class="item active">
							<div style="padding:30px;">
							<a href = "http://localhost:8090/Moviebokka/movie/getMovieInfo?code=174050">
								<img src="<%=imagePath%>birthday.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>birth2.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>birth3.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>birth4.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>birth5.jpg" alt="" height="340px" width="200px">
							</a>
							</div>
						</div>
						<div class="item">
							<div style="padding:30px;">
							<a href = http://localhost:8090/Moviebokka/movie/getMovieInfo?code=132623>
								<img src="<%=imagePath%>cap1.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>cap.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>cap.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>cap.jpg" alt="" height="340px" width="200px">
							</a>
							</div>
						</div>
						<div class="item">
							<div style="padding:30px;">
							<a href = http://localhost:8090/Moviebokka/movie/getMovieInfo?code=132623>
								<img src="<%=imagePath%>yo.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>yo1.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>yo2.jpg" alt="" height="340px" width="200px">
								<img src="<%=imagePath%>yo3.jpg" alt="" height="340px" width="200px">
							</a>
							</div>
						</div>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- carouselEnd -->

	<!-- testStart -->

	<!-- secondRowEnd -->

	<!-- testEnd -->

	<br>

	<div class="container">

		<div>
			<h3 class="h3">영화 TOP 6</h3>
		</div>
		<div class="top10"></div>
		<!-- 영화 별점으로 해서 값을 넘겨줌 -->
		<div>
			<h3 class="h3">많이 본 영화리뷰</h3>
		</div>
		<div class="recommand"></div>

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
						<h3 class="title title_clip">
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
		<br> <br>
	</div>
	<br>
	<br>
	<br>
	<!-- mainpageScriptStart -->
	<script src="/Moviebokka/static/js/mainPage.js"></script>
	<script>
	$(function() {
		var cnt = 1;
		setMovieMain();
		setMovieRecommand();
		
		function setMovieMain(){
			let $clone = $('.temp_movie').clone();
			let $target;
			let title;
			let img;
			let rating;
			let code;
			let getClone;
			
			<c:forEach var="item" items="${movieInfoFormList}">
				title = "${item.m_title}";
				img = "${item.m_img}";
				rating = ${item.m_user_rating};
				code = ${item.m_code};
				
				console.log(title);
				getClone = setTempAttribute(title, img, rating, code);
				
				$('.top10').append(getClone);
			</c:forEach>
		}
		
		function setMovieRecommand(){
			let $clone = $('.temp_movie').clone();
			let $target;
			let title;
			let img;
			let rating;
			let code;
			let getClone;
			
			<c:forEach var="item" items="${movieRecomList}">
				title = "${item.m_title}";
				img = "${item.m_img}";
				rating = ${item.m_user_rating};
				code = ${item.m_code};
				
				console.log(title);
				getClone = setTempAttribute(title, img, rating, code);
				
				$('.recommand').append(getClone);
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
		
		for(let i = 0; i<cnt; i++){
			$('.container').on('click', '#movie'+i, function(){
				let movieCode = $(this).find('#movieCode').val();
				console.log(movieCode);
				location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
			});
		}
	});
	</script>
	<!-- mainpageScriptEnd -->

	<!-- footerStart -->
	<jsp:include page="./partial/footer.jsp"></jsp:include>
	<!-- footerEnd -->