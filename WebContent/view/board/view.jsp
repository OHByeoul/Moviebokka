
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%pageContext.setAttribute("newLine", "\n");%>
<!DOCTYPE html >
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>
<c:choose>
    <c:when test="${type == 1}">게시판 내용</c:when>
    <c:when test="${type == 2}">공지 내용</c:when>
    <c:when test="${type == 3}">FAQ 내용</c:when>
</c:choose>
</title>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<link rel="stylesheet" href="/Moviebokka/static/css/board.css">
</head>
<body>
<div class="container board-content-wrapper">
    <div class="row">
        <div class="col-sm-12 text-center"><h3>글 내용</h3></div>
    </div>
    <div class="row"><div class="col-sm-12"><hr class="hr-top" /></div></div>
    <div class="row">
    	<div class="col-sm-12">
    		<div class="well board-content-title">제 목 :  ${board.brd_title}</div>
    	</div>
    </div>
    <div class="row">
        <div class="col-sm-1 text-center"><strong>작성자</strong></div>
        <div class="col-sm-3">${board.mem_nick}</div>
        <div class="col-sm-1 text-center"><strong>작성일</strong></div>
        <div class="col-sm-3">${board.brd_regdate}</div>
        <div class="col-sm-1 text-center"><strong>조회수</strong></div>
        <div class="col-sm-3">${board.brd_cnt}</div>
    </div>
    <div class="row"><div class="col-sm-12"><hr class="hr-middle" /></div></div>
    <div class="row">
        <div class="col-sm-12 board-content">${fn:replace(board.brd_content, newLine, "<br />")}</div>
    </div>
    <div class="row"><div class="col-sm-12"><hr class="hr-bottom" /></div></div>
    <div class="row">
    	<div class="col-sm-12 text-center">
    		<button class="btn btn-default btn-modify">수 정</button>
    		<button class="btn btn-default btn-reply">답 변</button>
    		<button class="btn btn-default btn-del">삭 제</button>
    		<button class="btn btn-default btn-list">목 록</button>
    	</div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function() {
	var prefix = '';
    <c:if test="${search ne null}">
    prefix = '&search=${search}&keyword=${keyword}';
    </c:if>

    <c:if test="${mem_id eq null}">
    $('.btn-modify, .btn-reply, .btn-del').on('click', function() {
        alert("로그인 후에 이용하여 주세요.");
    });
    </c:if>

    <c:if test="${mem_id ne null}">
        <c:choose>
            <c:when test="${mem_id eq board.mem_id}">
                $('.btn-modify').on('click', function() {
                    var url = 'boardUpdate?brd_id=${board.brd_id}&pageNum=${pageNum}&type=${type}';
                    window.location.href = url + prefix;
                });

                $('.btn-del').on('click', function() {
                    var result = confirm("삭제하시겠습니까?");
                    var url = 'boardDelete?brd_id=${board.brd_id}&pageNum=${pageNum}&type=${type}';
                    if(result) {
                        window.location.href = url + prefix;
                    } else {
                        return;
                    }
                });
            </c:when>
            <c:when test="${mem_id ne board.mem_id}">
                $('.btn-modify').on('click', function() {
                    alert("자신의 글이 아닙니다.");
                });

                $('.btn-del').on('click', function() {
                    alert("자신의 글이 아닙니다.");
                });
            </c:when>
        </c:choose>
        $('.btn-reply').on('click', function() {
        	var url = 'boardInsert?brd_id=${board.brd_id}&pageNum=${pageNum}&type=${type}';
            window.location.href = url + prefix;
        });
    </c:if>
    $('.btn-list').on('click', function() {
        var url = 'boardList?pageNum=${pageNum}&type=${type}';
        window.location.href = url + prefix;
    });
});
</script>
</body> 
</html>