<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css?after">

<title>${movieInfoForm.m_title}정보</title>
<style type="text/css">
.jumbotron {

	background-repeat: no-repeat;
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

.navbar {
  margin-bottom: 0;
  border-radius: 0;
}


/********************* text-size **********************/
a {
   font-size: 0.9em;
}

ul {
   font-size: 1.3em;
}

.footer_quick_link {
   font-size: 2em;
   color: cyan;
}

.nav_name {
   font-size: 0.3em;
   color: white;
}


/********************* boot-strap **********************/
.jumbotron {
   background-color: #141414;
}

h2, .info {
   color: #ffffff;
}

.review_table {
   color: #141414;
   background-color: #E6E6E6;
}

.well {
   color: white;
   background: #141414;
}

.review_btn_color {
   color: #141414;
}

.search_btn_color {
   color: #141414;
}

.padding_top {
   padding-top: 60px;
}
.padding_bottom {
   padding-bottom: 60px;
}

.search_movie_img_center {
   margin: auto;
   width: 50%;
   padding: 10px;
}

.search_movie_detail {
   text-align: left;
}

.spacing {
   padding: 30px;
}

.review_btn_text_color {
   color: white;
}

body {
   background-color: #333333;
   margin: none;
   padding: none;
}

content {
    background-color : #3c3c3c;
}

/* Add a gray background color and some padding to the footer */
footer {
  background-color: #f2f2f2;
  padding: 25px;
}
.rating {
	margin-top : 20px;
	margin-left : 15px;
}
.rating li{font-size:40px;color:#ffd200;transition:all .3s ease 0s}
.rating li.disable{color:rgba(0,0,0,.2)}


</style>
</head>
<body>
	contentStart
	<div class="content">

		<!-- movieDetailInfoStart -->
		
		<div class="container padding_top">
			<!-- movieImageStart -->
			<div class="col-sm-6">
				<img src="${movieInfoForm.m_img}" onerror="this.src='/Moviebokka/static/images/not-available.jpg'" alt="이미지 없음"
					class="img-responsive vcenter" height="450" width="300">
					<ul class="rating">
						<li class="fa fa-star"></li>
						<li class="fa fa-star"></li>
						<li class="fa fa-star"></li>
						<li class="fa fa-star"></li>
						<li class="fa fa-star"></li>
					</ul>
			</div>
			<!-- MovieImageEnd -->

			<!-- movieInfoStart -->
			<div class="padding_top padding_bottom">
				<div class="col-sm-6">
					<div class="text-left">
						<div>
							<div class="well">영화 제목 : ${movieInfoForm.m_title}</div>
							<div class="well">영화 개봉일 : ${movieInfoForm.m_pub_date}</div>
							<div class="well">영화 평점 : ${movieInfoForm.m_user_rating}</div>
							
							<div class="well">장르 : 
							<c:forEach items="${movieInfoForm.genre}" var="item"
								varStatus="status">
								${item} |
							</c:forEach>
							</div>
							<div class="well">배우 :
							<c:forEach items="${movieInfoForm.actor}" var="item"
								varStatus="status">
								${item} |
							</c:forEach>
							</div>
							<div class="well">감독 :
							<c:forEach items="${movieInfoForm.director}" var="item"
								varStatus="status">
								${item} |
							</c:forEach>
							</div>
							<div class="well">
								<h4>영화 시놉시스 :</h4>
								<div>
									<b>${movieInfoForm.m_story}</b>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- movieInfoEnd -->
		</div>
		<!-- movieDetailInfoEnd -->

		<!-- userReviewStart -->
		<div class="container">
			<div>
				<h3 class="review_btn_text_color">
					영화리뷰
					<div>
						<button type="button" id="create_review"
							class="btn review_btn_color">
							<div>리뷰작성</div>
						</button>
						<button type="button" id="more_review"
							class="btn review_btn_color">
							<div>더보기</div>
						</button>
					</div>
					<div>
						
					</div>
				</h3>


				<table class="table table-hover review_table">
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
		<!-- userReviewEnd -->
	</div>

	<!-- movieDetailinfoPageScriptStart -->
	<script>
		$(function(){			
			setInitReview();
			let $target = $('.rating').find('.fa.fa-star');
			let rating = ${movieInfoForm.m_user_rating};
			setUserRating(rating, $target);
			
			function setInitReview(){
				let $tbody = $('#tbody');
				let temp = "";
				<c:forEach  items="${reviews}" var="item" varStatus="status">
					temp="";
						temp+='<tr id="temp${status.index}">';
						temp+='<td>${item.rev_id}</td>';
						temp+='<td><a href="/Moviebokka/review/getSelectedReview?revId=${item.rev_id}">${item.rev_title}</a></td>';
						temp+='<td>${item.mem_nick}</td>';
						temp+='<td>${item.rev_regdate}</td>';
						temp+="</tr>";
					$tbody.append(temp);
				</c:forEach>
			}
			
			$('#create_review').on('click', function(){
				let session = "${sessionScope.user}";
				let movieCode = ${movieInfoForm.m_code};
				if(session !== ''){
					location.href = "/Moviebokka/review/reviewForm?movieCode="+movieCode;
				} else {
					alert('로그인 후 리뷰를 작성해주세요!');
					location.href = "/Moviebokka/user/login2";
				}
			});
			
			let start = "${start}"=== ""? 1 : "${start}";
			let end = "${end}"=== ""? 5 : "${end}";
			let movieCode = ${movieInfoForm.m_code};
			
			$('#more_review').on('click', function(){
				let s = parseInt(start)+5;
				let e = parseInt(end)+5;
				start = String(s);
				end = String(e);
				
				 $.getJSON('/Moviebokka/review/getReviewMore',{movieCode : movieCode, startNum : start, endNum:end},function(result){
		        		if(result.length === 0){
		        			//let empty = "<div>검색된 결과가 없습니다.</div>";
		        			//$('.review_table').append(empty);
		        		}
		        	$.each(result, function(i){
		        		console.log(result);
		        		getMoreReview(result[i].rev_id,result[i].rev_title,result[i].rev_regdate,result[i].mem_nick);
		        	});
		        });
			});
			
			 function getMoreReview(rev_id,rev_title,rev_regdate,mem_nick){
				let temp="";
					temp+='<tr>';
					temp+='<td>'+rev_id+'</td>';
					temp+='<td><a href="/Moviebokka/review/getSelectedReview?revId='+rev_id+'">'+rev_title+'</a></td>';
					temp+='<td>'+mem_nick+'</td>';
					temp+='<td>'+rev_regdate+'</td>';
					temp+="</tr>";
					$('#tbody').append(temp);
			
			 }
			
			
			function setUserRating(rating, target) {
				let highStar = 4;
				let limit = Math.round(rating/2);
				console.log(limit);
				for (let i = highStar; i >= limit; i--) {
					target.eq(i).attr("class", "fa fa-star disable");
				}
			}
		});
	</script>
<!-- movieDetailinfoPageScriptEnd -->

	<!-- footerStart -->
	<jsp:include page="../partial/footer.jsp"></jsp:include>
	</body>
	<!-- footerEnd -->