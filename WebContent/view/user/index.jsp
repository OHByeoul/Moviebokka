<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../partial/header.jsp"/>
<title>무비보까</title>
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css?after"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<jsp:include page="../partial/header.jsp"/>
<style>
body {
	background-color: #333333;
	margin: none;
	padding: none;
}
</style>

</head>
<body class=body>
	<div align="center" id=index>
		<table class="indexTable" onclick="location.href='/Moviebokka/movie/main'" >
			<tr>
				<td><img alt="" src="/Moviebokka/static/images/movie.png" width="100px" height="100px">
				<td>입장하기

			</tr>
		</table>
		<table class="indexTable" onclick="location.href='/Moviebokka/user/login2'">
			<tr>
				<td><img alt="" src="/Moviebokka/static/images/login.png" width="100px" height="100px">
				<td>로그인
			</tr>
		</table>

	</div>

	<div align="center" class=indexImage>
		<img alt="" src="/Moviebokka/static/images/minions.gif" width="500px" height="500px">
	</div>
		
		

	
</body>
<jsp:include page="../partial/footer.jsp"/>
</html>