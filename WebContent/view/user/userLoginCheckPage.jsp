<%@page import="com.revizio.moviebokka.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>무비보까 로그인</title>
</head>

<body>
	<c:set var="result" value="${result}"></c:set>
	<c:choose>
	  <c:when test="${result eq true}">
	  	<script type="text/javascript">
	  		alert("${nickname}님 환영합니다.");
			location.href="/Moviebokka"; 
		</script>

	  </c:when>
	  <c:when test="${result eq false}">
	  	<script type="text/javascript">
			alert("아이디나 비밀번호를 확인하세요");
			location.href="/Moviebokka/user/login2";
		</script>
	  </c:when>
	</c:choose>  
</body>
</html>