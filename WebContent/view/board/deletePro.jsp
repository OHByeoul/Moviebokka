<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${acl == 0}">
    <c:redirect url="/Moviebokka/user/login"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:if test="${result > 0 }">
	<script type="text/javascript">
    <c:if test="${search eq null}">
		location.href="/Moviebokka/board/boardList?type=${type}&pageNum=${pageNum}";
    </c:if>
    <c:if test="${search ne null}">
		location.href="/Moviebokka/board/boardList?type=${type}&pageNum=${pageNum}&search=${search}&keyword=${keyword}";
    </c:if>
	</script>
</c:if>	
<c:if test="${result <= 0}">
	<script type="text/javascript">
    <c:if test="${search eq null}">
		alert("오류가 발생하였습니다.");
		location.href="/Moviebokka/board/boardView?type=${type}&pageNum=${pageNum}&brd_id=${brd_id}";
    </c:if>
    <c:if test="${search ne null}">
		location.href="/Moviebokka/board/boardList?type=${type}&pageNum=${pageNum}&brd_id=${brd_id}&search=${search}&keyword=${keyword}";
    </c:if>
	</script>
</c:if>	
</head>
<body>
</body>
</html>