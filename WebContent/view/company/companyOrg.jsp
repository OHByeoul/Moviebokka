<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String imagePath = request.getContextPath() + "/static/images/";
%>
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">

<title>영화보까</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
.jumbotron {
	background-image: url(<%=imagePath%>);
	background-repeat: no-repeat;
	display: block;
	background-size: cover;
	width: 100%;
	padding: 0px;
	margin-bottom: 0;
	margin-top: 0;
	background-color: #141414;
}

h1 {
	margin: 0;
}
</style>
</head>
<body class="body">

	<!-- contentStart -->
	<div align="center">
		<div class="container">
			<div style="padding:30px;">
				<div class="jumbotron text-center">
					<h2 class="text-center" style="padding: 30px;">조직도</h2>
					<div class="text-center" style="color: white; padding: 30px;">
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>팀장: 오한별</h4></div>
						</div>
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>사원: 위선남</h4></div>
						</div>
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>사원: 유인선</h4></div>
						</div>
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>사원: 이재인</h4></div>
						</div>
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>사원: 박준민</h4></div>
						</div>
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>사원: 장철혁</h4></div>
						</div>
						<div class="well">
							<img src="" alt="" align="middle" class="img-responsive">
							<div class="text-middle"><h4>사원: 이인수</h4></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- contentEnd -->

	<!-- contentScriptStart -->

	<!-- contentScriptEnd -->

	<!-- footerStart -->
	<jsp:include page="../partial/footer.jsp"></jsp:include>
	<!-- footerEnd -->