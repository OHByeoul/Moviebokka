<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css"> -->
</head>
<body class=body>
	<div align="center">
		<h1>비밀번호 찾기</h1>
		<form action="/Moviebokka/user/findPassowrdCheck" method="post">
			<input type="email" name=email class="junForm" placeholder="비밀번호를 찾고자 하는 이메일을 입력 하세요.">
			<input type="submit" class=btn value="다음">
		</form>
	</div>
	
	
</body>
<jsp:include page="../partial/footer.jsp"/>
</html>