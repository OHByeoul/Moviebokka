<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./partial/header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../static/css/searchMoviePage.css">
</head>

<body>
	<form action="" class="center_block" id="search_form" method="get">
		<input type="text" class="search-query" name="movieName" id="search"
			placeholder="검색">
		<button class="btn btn-warning" id="search_btn" type="submit">검색</button>
	</form>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-md-offset-3" id="col">
				<div class="list-group">
					
				</div>
			</div>
		</div>

		<form id="template" style="display: none">
			<div class="list-group-item active">
				<img class="col" src="" id="image" alt="이미지없오">
					<div class="content">
						<div><h4 class="list-group-item-heading" id="title"></h4></div>
						<div><p class="list-group-item-text" id="actor"></p></div>
						<div><p class="list-group-item-text" id="userRating"></p></div>
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
				$temp.find("#actor").text(obj['userRating']);
				$temp.css("display", "block");
				element += "<input type='hidden' name='title' value='"+obj['title']+"'>";
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
</body>