<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<style>
.body {
	background-color: #333333;
	margin: none;
	padding: none;
}
.jumbotron {
	background-color: #141414;
}
</style>
</head>
<body class=body>
	<c:set var="result" value="${result}"></c:set>
	<c:choose>
	  <c:when test="${result eq true}">
	  	<div align="center">
			<p id=joinTitle>회원가입을 환영합니다.</p>
			<img alt="" src="../static/images/smile.png" width="500px" height="500px"><p>
			<span id=joinText>무비보까의 모든 컨텐츠를 정상적으로 이용하시려면 이메일 인증을 해주세요.</span>
			<button class="joinBtn" onclick="window.open('https://www.naver.com')">이메일 인증하러가기</button>
		</div>

	  </c:when>
	  <c:when test="${result eq false}">
	  	<script type="text/javascript">
			alert("아이디나 비밀번호를 확인하세요");
			location.href="history.go(-1)";
		</script>
	  </c:when>
	</c:choose>  
</body>
</html>