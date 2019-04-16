<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String imagePath = request.getContextPath() + "/static/images/";
%>
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">

<title>${movieInfoForm.m_title}정보</title>
<style type="text/css">
.jumbotron {
	background-image: url(<%=imagePath%>); /* 무비보까 배너이미지 */
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
</style>
</head>
<body>
	contentStart
	<div class="content">

		<!-- movieDetailInfoStart -->
		<div class="container padding_top">
			<!-- movieImageStart -->
			<div class="col-sm-6">
				<img src="${movieInfoForm.m_img}" alt="이미지 없음"
					class="img-responsive vcenter" height="450" width="300">
				<div class="text-center">
					<ul class="rating vcenter">
						<li class="fa fa-star" style="font-size: 28px; color: yellow"></li>
						<li class="fa fa-star" style="font-size: 28px; color: yellow"></li>
						<li class="fa fa-star" style="font-size: 28px; color: yellow"></li>
						<li class="fa fa-star" style="font-size: 28px; color: yellow"></li>
						<li class="fa fa-star disable" style="font-size: 28px; color: yellow"></li>
					</ul>
				</div>
			</div>
			<!-- MovieImageEnd -->

			<!-- movieInfoStart -->
			<div class="padding_top padding_bottom">
				<div class="col-sm-6">
					<div class="text-left">
						<div>
							<div class="well">영화 코드 : ${movieInfoForm.m_code}</div>
							<div class="well">영화 제목 : ${movieInfoForm.m_title}</div>
							<div class="well">영화 개봉일 : ${movieInfoForm.m_pub_date}</div>
							<div class="well">영화 평점 : ${movieInfoForm.m_user_rating}</div>

							<c:forEach items="${movieInfoForm.genre}" var="item"
								varStatus="status">
								<span class="well">${item} |</span>
							</c:forEach>
							<c:forEach items="${movieInfoForm.actor}" var="item"
								varStatus="status">
								<span class="well">${item} |</span>
							</c:forEach>
							<c:forEach items="${movieInfoForm.director}" var="item"
								varStatus="status">
								<span class="well">${item} |</span>
							</c:forEach>
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
				let movieCode = ${movieInfoForm.m_code};
				location.href = "/Moviebokka/review/reviewForm?movieCode="+movieCode;
			});
		});
	</script>
<!-- movieDetailinfoPageScriptEnd -->

	<!-- footerStart -->
	<jsp:include page="../partial/footer.jsp"></jsp:include>
	<!-- footerEnd -->