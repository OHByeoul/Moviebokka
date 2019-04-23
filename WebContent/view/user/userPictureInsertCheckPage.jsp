<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로필 사진 삽입 결과</title>
</head>
<body>
	<c:if test="${result eq true }">
		<script type="text/javascript">
			alert("사진 삽입 성공");
			window.close();
		</script>
	</c:if>
	<c:if test="${result eq false }">
		<script type="text/javascript">
			alert("사진 삽입 실패");
			window.close();
		</script>
	</c:if>
</body>
</html>