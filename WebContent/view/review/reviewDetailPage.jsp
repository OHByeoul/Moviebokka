<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	background-color : #141414;
}
.panel-body {
	height : 400px;
	background-color : #f2f2f2;
}
.title {
	margin-left : 90px;
}
.ip{
	margin-left : 500px;
}
.time {
	margin-left : 120px;
}
.view {
	margin-left : 600px;
}
.recommand {
	margin-left : 50px;
}
h2 {
	color : #E6E6E6;
	text-align : center;
	margin-bottom : 30px;
}
</style>
<body>
	<div class="container">
		<h2>리뷰 내용</h2>
		<div class="list">
			<button type="button" id="list" class="btn btn-default">목록으로</button>
			<button type="button" id="update" class="btn btn-default" style="display:none">글수정</button>
			<button type="button" id="delete" class="btn btn-default" style="display:none">글삭제</button>
		</div>
		<div class="panel-group">
			<div class="Panel with panel-primary class">
				<div class="panel-heading">
					<span class="num">번호 ${reviewDetail.rev_id}</span> <span class="title">제목 ${reviewDetail.rev_title}</span>
					<span class="ip">아이피 ${reviewDetail.rev_ip}</span>
				</div>
				<div class="Panel with panel-info class">
					<div class="panel-heading">
					
						<span class="nick">닉네임 ${reviewDetail.mem_nick}</span> <span class="recommand">추천 ${reviewDetail.rev_recommand}</span> 
						<span class="view">조회수 ${reviewDetail.rev_view}</span> <span class="time">시간 ${reviewDetail.rev_regdate}</span>
					</div>
					<div class="panel-body">${reviewDetail.rev_content}</div>
				</div>
			</div>
		</div>
		<button type="button" id="recommand" class="btn btn-success">추천<span id="score"> ${reviewDetail.rev_recommand}</span></button>
		<button type="button" id="unrecommand" class="btn btn-danger">비추천 <span id="unscore">${reviewDetail.rev_unrecommand}</span></button>
	</div>
	<form action="/Moviebokka/review/deleteReview" method="POST" id="deleteReviewForm" style="display: hidden">
	  <input type="hidden" id="revId" name="revId" value=""/>
	  <input type="hidden" id="movieCode" name="movieCode" value=""/>
	</form>
	<script>
		$(function(){
			let session = "${session.mem_nick}";
			let nick = "${reviewDetail.mem_nick}";
			let movieCode = "${reviewDetail.m_code}";
			let revId = "${reviewDetail.rev_id}";
			
			if(session !== 'undefined' && nick !== 'undefined'){
				if(session == nick){
					$('#delete').show();
					$('#update').show();
				}
			}
			
			$('#delete').on('click', function(){
				let result = confirm("정말로 삭제할꼬얌??");
				
				if(result){
					$('#revId').val(revId);
					$('#movieCode').val(movieCode);
					$("#deleteReviewForm").submit();	
				}
			});
			
			$('#update').on('click', function(){
				location.href = "/Moviebokka/review/updateReviewForm?revId="+revId;
			});
			
			$('#list').on('click', function(){
				let code = ${reviewDetail.m_code};
				location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+code;
			});			
			
			let recomStatus = false;
			if("${recommand.recom_status}"==1){
				recomStatus = true;
			} 
			let unrecomStatus = false;
			if("${recommand.unrecom_status}"==1){
				unrecomStatus = true;
			}
			
			$('#recommand').on('click', function(){
				if(unrecomStatus){
					alert("비추를 해제해야 ㅊㅊ가능");
					return false;
				}
				if(recomStatus){
					recomStatus = false;
				} else {
					recomStatus = true;
				}
				
				checkUserRecommand();
				
					$.ajax({
						url : "/Moviebokka/review/recommandReview",
						type : "GET",
						data : {revId : revId, status : recomStatus}
					}).done(function(result){
						console.log(result);
					}).fail(function(fail){
						console.log(fail);
					});
					let $score = $('#score').text();
					let score;
				if(recomStatus){
					score = String(Number($score)+1);
				} else {
					score = String(Number($score)-1);
				}
					$('#score').text(score);
				
				
			});
			
			$('#unrecommand').on('click', function(){
				if(recomStatus){
					alert("추천을 해제해야 비추 가능함 ㅇㅋ?");
					return false;
				}
				if(unrecomStatus){
					unrecomStatus = false;
				} else {
					unrecomStatus = true;
				}
				checkUserRecommand();
				
					$.ajax({
						url : "/Moviebokka/review/unrecommandReview",
						type : "GET",
						data : {revId : revId, status : unrecomStatus}
					}).done(function(result){
						console.log(result);
					}).fail(function(fail){
						console.log(fail);
					});
					let $score = $('#unscore').text();
					let score;
					if(unrecomStatus){
						score = String(Number($score)+1);
					} else {
						score = String(Number($score)-1);
					}
					$('#unscore').text(score);
				
			});
			
			function checkUserRecommand(){
				$.ajax({
					url : "/Moviebokka/review/checkUserRecommand",
					type : "GET",
					data : {revId : revId, status : recomStatus, unStatus : unrecomStatus}
				}).done(function(result){
					console.log(result);
				}).fail(function(fail){
					console.log(fail);
				});
			}
			
			
		});
	</script>
</body>
</html>