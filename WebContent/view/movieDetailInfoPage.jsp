<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./partial/header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<img src="${movieInfoForm.m_img}" alt ="이미지 없음">
	</div>
	<div>
		${movieInfoForm.m_code}
	</div>
	<div>
		${movieInfoForm.m_title}
	</div>
	<div>
		${movieInfoForm.m_pub_date}
	</div>
	<div>
		${movieInfoForm.m_userRating}
	</div>
	<div>
		<c:forEach  items="${movieInfoForm.actor}" var="item" varStatus="status">
			<tr>
				<td>${item} |</td>
			</tr>
		</c:forEach>
	</div>
	<div>
		<c:forEach  items="${movieInfoForm.director}" var="item" varStatus="status">
			<tr>
				<td>${item} |</td>
			</tr>
		</c:forEach>
	</div>
	<div>
		${movieInfoForm.m_story}
	</div>
</body>
</html>