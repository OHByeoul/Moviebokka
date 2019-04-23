<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내가 쓴 글 보기</title>
</head>
<body>
	<c:if test="${result eq 1 }">
		<script type="text/javascript">
		location.href="/Moviebokka/board/boardView?brd_id=${keyNum}&pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result eq 2 }">
		<script type="text/javascript">
		location.href="/Moviebokka/review/getSelectedReview?revId=${keyNum}&pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result eq 0 }">
		<script type="text/javascript">
			alert("삭제된 페이지 입니다.");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>