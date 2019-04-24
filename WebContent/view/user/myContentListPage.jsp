<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<title>내가 쓴 글 보기</title>
<style>
.body {
	background-color: #333333;
	margin: none;
	padding: none;
}
.jumbotron {
	background-color: #141414;
}
</style>
</head>
<body class=body>
	
		<div class="container">
		    <div class="row">
		        <div class="col-sm-6">
		            <table class="table table-border table-board">
		            	<h3 style="color: white">게시판 작성 내역</h3>
			            <tr class=tableHead>
			                <th>번 호</th>
			                <th>제 목</th>
			                <th>작성일</th>
			            </tr>
		            	<c:if test="${brdTotalCount > 0 }">
			                <c:forEach var="board" items="${brdList}">
				                <tr>
				                    <td class=myListNum>${brdStartNum }</td>
				                    <td class=myListTitle>
										<a href='/Moviebokka/board/boardView?brd_id=${board.brd_id }&title=${board.brd_title }&pageNum=${brdCurrentPage}'>${board.brd_title }</a>
										<%-- <a href='/Moviebokka/review/getSelectedReview?rev_id=${board.brd_id }&title=${board.brd_title }&pageNum=${brdCurrentPage}'>${board.brd_title }</a> --%>
				                    </td>
				                    <td class=myListRegdate>${board.brd_regdate}</td>
				                </tr>
				                <c:set var="brdStartNum" value="${brdStartNum - 1 }" />
			                </c:forEach>
		            	</c:if>
			            <c:if test="${brdTotalCount == 0 }">
			                <tr><td colspan="3" class="text-center"><h4>등록된 게시물이 없습니다.</h4></td></tr>
			            </c:if>
			            <tr>
			            	<td class="page-wrapper text-right" colspan="3">
			            		<nav>
					                <ul class="pagination">
					                    <c:if test="${brdStartPage > brdBlockSize}">
					                    	<li class="page-item"><a href='/Moviebokka/board/myContentList?brdPageNum=${brdStartPage-brdBlockSize }'><span>&laquo;</span></a></li>
					                    </c:if>
					                    <c:forEach var="i" begin="${brdStartPage}" end="${brdEndPage}">
					                        <c:choose>
					                            <c:when test="${brdCurrentPage == i}">
					                                <li class="page-item active"><a href='/Moviebokka/board/myContentList?brdPageNum=${i }'>[${i }]</a></li>
					                            </c:when>
					                            <c:when test="${brdCurrentPage != i}">
					                                <li class="page-item"><a href='/Moviebokka/board/myContentList?brdPageNum=${i }'>[${i }]</a></li>
					                            </c:when>
					                        </c:choose>
					                    </c:forEach>
					                    <c:if test="${brdEndPage < brdPageCnt}">
					                         <a href='/Moviebokka/board/myContentList?brdPageNum=${brdStartPage+brdBlockSize }'><span>&raquo;</span></a></li>
					                    </c:if>
					                </ul>
			           			</nav>
			            	</td>
			            </tr>
		            </table>
		        </div>
			        <div class="col-sm-6">
						<h3 style="color: white">리뷰 작성 내역</h3>
			    	<table class="table">
			    	<tr class=tableHead>
			                <th>번 호</th>
			                <th>제 목</th>
			                <th>작성일</th>
			            </tr>
		            	<c:if test="${reviewTotalCount > 0 }">
			                <c:forEach var="item" items="${reviews}">
				                <tr>
				                    <td class=myListNum>${item.rev_id }</td>
				                    <td class=myListTitle>
										<a href='/Moviebokka/review/getSelectedReview?revId=${item.rev_id}&title=${item.rev_title}'>${item.rev_title }</a>
				                    </td>
				                    <td class=myListRegdate>${item.rev_regdate}</td>
				                </tr>
				                
			                </c:forEach>
		            	</c:if>
			            <c:if test="${reviewTotalCount == 0 }">
			                <tr><td colspan="3" class="text-center"><h4>등록된 게시물이 없습니다.</h4></td></tr>
			            </c:if>
			            <tr>
			            	<td class="page-wrapper text-right" colspan="3">
			            		<nav>
					                <ul class="pagination">
					                    <c:if test="${reviewStartPage > reviewBlockSize}">
					                    	<li class="page-item"><a href='/Moviebokka/board/myContentList?reviewPageNum=${reviewStartPage - reviewBlockSize }'><span>&laquo;</span></a></li>
					                    </c:if>
					                    <c:forEach var="i" begin="${reviewStartPage}" end="${reviewEndPage}">
					                        <c:choose>
					                            <c:when test="${reviewCurrentPage == i}">
					                                <li class="page-item active"><a href='/Moviebokka/board/myContentList?reviewPageNum=${i }'>[${i }]</a></li>
					                            </c:when>
					                            <c:when test="${reviewCurrentPage != i}">
					                                <li class="page-item"><a href='/Moviebokka/board/myContentList?reviewPageNum=${i }'>[${i }]</a></li>
					                            </c:when>
					                        </c:choose>
					                    </c:forEach>
					                    <c:if test="${reviewEndPage < reviewPageCnt}">
					                         <a href='/Moviebokka/board/myContentList?reviewPageNum=${reviewStartPage + reviewBlockSize }'><span>&raquo;</span></a></li>
					                    </c:if>
					                </ul>
			           			</nav>
			            	</td>
			            </tr>
		            </table>
		    	</div>
		    </div>
		    
		</div>

</body>
<jsp:include page="../partial/footer.jsp"/>
</html>