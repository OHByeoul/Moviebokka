<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">

<title>무비보까 검색 페이지</title>
<style type="text/css">
.jumbotron {
	display: block;
	background-size: cover;
	width: 100%;
	padding: 0px;
	margin-bottom: 0;
	margin-top: 0;
	background-color: #141414;
}

h1 {
	margin: 0;
}

h3 {
	color: white;
	padding: 20px;
}

button:hover {
	color: cyan;
}
</style>
</head>

<body>
	<div class="text-center padding_top padding_bottom">
		<h3>영화검색</h3>
		<form action="" class="center_block" id="search_form" method="get">
			<input type="text" class="search-query" name="movieName" id="search" placeholder="검색">
			<button class="btn search_btn_color" id="search_btn" type="submit">검색</button>
		</form>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-md-offset-3" id="col">
				<div class="list-group"></div>
			</div>
		</div>

		<form id="template" style="display: none">
			<div class="list-group-item col-sm-12"
				style="background-color: black;">
				<div>
					<img class="col-sm-4 search_movie_img_center" src="" id="image" alt="이미지없오" height="auto" width="auto">
				</div>
				<div class="row">
					<div class="col-sm-4 well"> 
						영화제목: <h5 class="list-group-item-heading search_movie_detail" id="title"></h5>
					</div>
					<div class="col-sm-4 well">
						배우: <p class="list-group-item-text search_movie_detail" id="actor"></p>
					</div>
					<div class="col-sm-4 well">
						영화개봉일: <p class="list-group-item-text search_movie_detail" id="pub_date"></p>
					</div>
					<div class="col-sm-4 well">
						관람객 평점: <p class="list-group-item-text search_movie_detail" id="user_rating"></p>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script>
		$(function() {
			$('#search_form').on('submit', function(e) {
				e.preventDefault();
				let searchName = $('.search-query').val();
				var data = $(this).serialize();

				$.ajax({
					url : '/Moviebokka/movie/getMovieInfoes',
					type : 'GET',
					data : data
				}).done(function(result) {
					$('.list-group').empty();
					$.each(result, function(key, value) {
						if (key === "items") {
							$.each(value, function(index, obj) {
								createNewForm(index, obj);
								addBtnEvent(index, obj);
							});
						}
					});
				}).fail(function(fail) {
					alert(fail);
				});

			});

			function createNewForm(index, obj) {
				let element = "";
				let $temp = $('#template').clone();
				$temp.attr('id', "temp" + index);
				$temp.find("#image").attr("src", obj['image']);
				$temp.find("#title").html(obj['title']);
				$temp.find("#actor").text(obj['actor']);
				$temp.find("#pub_date").text(obj['pubDate']);
				$temp.find("#user_rating").text(obj['userRating']);
				$temp.css("display", "block");
				element += "<input type='hidden' name='title' value='"+obj['title']+"'>";
				element += "<input type='hidden' name='pubDate' value='"+obj['pubDate']+"'>";
				element += "<input type='hidden' name='img' value='"+obj['image']+"'>";
				element += "<input type='hidden' name='director' value='"+obj['director']+"'>";
				element += "<input type='hidden' name='actor' value='"+obj['actor']+"'>";
				element += "<input type='hidden' name='userRating' value='"+obj['userRating']+"'>";
				$temp.append(element);
				let btn = "<button type='button' id='btn"+index+"' class='btn btn-warning'>영화정보보기</button>";
				$temp.find(".list-group-item.active").append(btn);
				let $group = $('.list-group');
				$group.append($temp);
			}

			function addBtnEvent(index, obj) {
				$(document).on(
						'click',
						'#btn' + index,
						function(e) {
							e.preventDefault();

							let link = obj['link'];
							let temp = link.split("?");
							let code = temp[1].substring(5, temp[1].length);
							let param = $(this).closest("#temp"+index).serialize();

							location.href = "/Moviebokka/movie/getMovieInfo?code="
									+ code + "&" + param;
						});
			}

		});
	</script>
	<!-- footer&scripts -->
	<jsp:include page="../partial/footer.jsp" />
</body>