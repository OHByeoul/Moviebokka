<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/view/partial/header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	메인이얌
	<p id = "movieInfo">영화정보보기</p>
	<p id = "revieForm">리뷰보기</p>
	
	<script>
		$('#movieInfo').on('click', function(){
				location.href = "/Moviebokka/movie/createReview?hi='hello!' ";
		});
		
		$('#revieForm').on('click', function(){
			location.href = "/Moviebokka/movie/getMovieInfo?hi='movieInfo!'";
		});
	</script>
</body>
</html>