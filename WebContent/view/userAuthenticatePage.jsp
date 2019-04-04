<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<<c:set var="result" value="${result}" />

<c:choose>
    <c:when test="${result eq true}">
        세션 만들어짐
    </c:when>

    <c:when test="${result eq false}">
        세션 생성 실패
    </c:when>

</c:choose>
</body>
</html>