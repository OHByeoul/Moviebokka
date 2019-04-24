<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${acl == 0}">
    <c:redirect url="/Moviebokka/login"></c:redirect>
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:if test="${result > 0}">
	<script type="text/javascript">
    <c:if test="${empty search}">
		location.href="/Moviebokka/board/boardView?brd_id=${brd_id}&pageNum=${pageNum}&type=${type}";
    </c:if>
    <c:if test="${not empty search}">
		location.href="/Moviebokka/board/boardView?brd_id=${brd_id}&pageNum=${pageNum}&type=${type}&search=${search}&keyword=${keyword}";
    </c:if>
	</script>
</c:if>
<c:if test="${result == 0}">
	<script type="text/javascript">
    <c:if test="${empty search}">
		alert("오류가 발생하였습니다.");
		location.href="/Moviebokka/board/boardUpdate?brd_id=${brd_id}&pageNum=${pageNum}&type=${type}";
    </c:if>
    <c:if test="${not empty search}">
		alert("오류가 발생하였습니다.");
		location.href="/Moviebokka/board/boardUpdate?brd_id=${brd_id}&pageNum=${pageNum}&type=${type}&search=${search}&keyword=${keyword}";
    </c:if>
	</script>
</c:if>
</head>
<body>
</body>
</html>