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

button:hover {
	color: cyan;
}

.container-form .input-group {
   padding: 30px 0;
}

.search-image {
    width: 150px !important;
    height: 210px !important;
}

.list-group-item {
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
}
</style>
</head>
<body>
<div class="container container-form">
    <div class="row">
        <div class="col-sm-12">
            <form action="" class="form" id="search_form" method="get">
                <div class="input-group">
                    <span class="input-group-btn">
                        <button class="btn btn-default btn-lg" type="button" disabled>영화 검색</button>
                    </span>
                    <input type="text" class="form-control input-lg" placeholder="검색어" name="movieName" id="search">
                    <span class="input-group-btn">
                        <button class="btn btn-default btn-lg" id="search_btn" type="submit">검 색</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
    <div class="row row-result">
    </div>
</div>

    <form id="template" style="display: none;">
        <div class="col-sm-2">
            <a href="#" class="thumbnail">
                <img id="image" class="search-image">
            </a>
        </div>
        <div class="col-sm-4">
            <div class="list-group">
                <div class="list-group-item">
                    영화제목: <span id="title"></span>
                </div>
                <div class="list-group-item">
                    배우: <span id="actor"></span>
                </div>
                <div class="list-group-item">
                    영화개봉일: <span id="pub_date"></span>
                </div>
                <div class="list-group-item">
                    관람객 평점: <span id="user_rating"></span>
                </div>
                <div class="list-group-item"></div>
            </div>
        </div>
    </form>

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
					$('.row.row-result').empty();//
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
				if(obj['title'] !== '' && obj['title'] !== 'undefined') {
                    $temp.attr('id', "temp" + index);
                    if(obj['image'].length > 0) {
                        $temp.find("#image").attr("src", obj['image']);
                    } else {
                    	$temp.find("#image").attr("src", '/Moviebokka/static/images/not-available.jpg');
                    }
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
                    let btn = "<button type='button' id='btn"+index+"' class='btn btn-block btn-warning'>영화정보보기</button>";
                    $temp.find(".list-group-item:last").append(btn);
                    console.log(obj['title'] + " : " + obj['image']);
                    $('.row-result').append($temp);	
                    console.log($temp.html());
                    //let $group = $('.list-group');
                    //$group.append($temp);
				}
			}

			function addBtnEvent(index, obj) {
				$(document)
						.on(
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