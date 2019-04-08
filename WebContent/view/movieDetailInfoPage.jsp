<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./partial/header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	body {
		background-color : #141414;
	}
	
	h2, .info {
		color : #E6E6E6;
		background-color : #141414;
	}
	.review_table {
		color : #141414;
		background-color : #E6E6E6;
	}
</style>
<body>
	<div>
		<img src="${movieInfoForm.m_img}" alt ="이미지 없음">
	</div>
	<div class="info">
		${movieInfoForm.m_code}
	</div>
	<div class="info">
		${movieInfoForm.m_title}
	</div>
	<div class="info">
		${movieInfoForm.m_pub_date}
	</div>
	<div class="info">
		${movieInfoForm.m_user_rating}
	</div>
	<div class="info">
		<c:forEach  items="${movieInfoForm.genre}" var="item" varStatus="status">
				<span>${item} |</span>
		</c:forEach>
	</div>
	<div class="info">
		<c:forEach  items="${movieInfoForm.actor}" var="item" varStatus="status">
			<span>${item} |</span>
		</c:forEach>
	</div>
	<div class="info">
		<c:forEach  items="${movieInfoForm.director}" var="item" varStatus="status">
			<span>${item} |</span>
		</c:forEach>
	</div>
	<div class="info">
		${movieInfoForm.m_story}
	</div>
	<div>
		 <button type="button" id="create_review"class="btn btn-primary">리뷰작성</button>
	</div>
	<div class="review_table">
		<h2>리뷰</h2>           
		  <table class="table table-hover">
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
						temp+='<td>${item.rev_title}</td>';
						temp+='<td>${item.rev_content}</td>';
						temp+='<td>${item.mem_nick}</td>';
						temp+='<td>${item.rev_regdate}</td>';
						temp+="</tr>";
					$tbody.append(temp);
				</c:forEach>
			}
			
			$('#create_review').on('click', function(){
				let movieCode = ${movieInfoForm.m_code};
				location.href = "/Moviebokka/movie/reviewForm?movieCode="+movieCode;
			});
		});
	</script>
</body>
</html>