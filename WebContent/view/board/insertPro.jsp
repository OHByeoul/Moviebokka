<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${acl == 0}">
    <c:redirect url="/Moviebokka/login"></c:redirect>
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<c:if test="${result > 0}">
	<script type="text/javascript">
		location.href="/Moviebokka/board/boardList?pageNum=${pageNum}&type=${type}";
	</script>
</c:if>
<c:if test="${result == 0}">  
	<script type="text/javascript">
		alert("오류가 발생하였습니다.");  
		location.href="/Moviebokka/board/boardInsert?type=${type}&pageNum=${pageNum}&brd_id=${brd_id}";
	</script>
</c:if>
</head>
<body>
</body>
</html>