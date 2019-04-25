<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../partial/header.jsp"/>
<title>
<c:choose>
    <c:when test="${type == 1}">자유게시판 목록</c:when>
    <c:when test="${type == 2}">공지 목록</c:when>
    <c:when test="${type == 3}">FAQ 목록</c:when>
</c:choose>
</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean">
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<link rel="stylesheet" href="/Moviebokka/static/css/board.css">
</head>
<body>
<div class="container-fluid body_style">
    <jsp:include page="../partial/navbar.jsp"/>
    <div class="row">
        <div class="col-12">
            <div class="container mboard">
                <div class="row">
                    <div class="col-sm-12 text-center board-title">
                        <h3>
                        <c:choose>
                            <c:when test="${type == 1}">자유게시판 목록</c:when>
                            <c:when test="${type == 2}">공지사항 목록</c:when>
                            <c:when test="${type == 3}">FAQ 목록</c:when>
                        </c:choose>
                        </h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4 board-search-wrapper">
                        <div class="input-group">
                            <div class="input-group-btn board-search">
                                <button type="button" class="btn btn-default dropdown-toggle" id="btn-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span id="search-select" data-select="${search}">
                                    <c:choose>
                                        <c:when test="${search eq null}">선 택 </c:when>
                                        <c:when test="${search == 'mem_nick'}">작성자 </c:when>
                                        <c:when test="${search == 'brd_title'}">제 목 </c:when>
                                        <c:when test="${search == 'brd_cotent'}">내 용 </c:when>
                                        <c:when test="${search == 'all'}">제목+내용 </c:when>
                                    </c:choose>
                                    </span>
                                <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li data-type="brd_title">제 목</li>
                                    <li role="separator" class="divider"></li>
                                    <li data-type="brd_content">내 용</li>
                                    <li role="separator" class="divider"></li>
                                    <li data-type="mem_nick">닉네임</li>
                                    <li role="separator" class="divider"></li>
                                    <li data-type="all">제목+내용</li>
                                </ul>
                            </div>
                            <input type="text" name="keyword" id="keyword" class="form-control" placeholder="검색어" value="${keyword}" />
                            <span class="input-group-btn">
                                <button class="btn btn-default btn-board-search" type="button">
                                    <i class="fa fa-fw fa-search"></i>검 색
                                    <c:if test="${search ne null}"><span class="badge badge-search"> ${totCnt}</span></c:if>
                                </button>
                            </span>
                        </div>
                    </div>
                    <div class="col-sm-6 col-sm-offset-2 text-right">
                        <c:if test="${mem_id ne null}">
                            <c:choose>
                                <c:when test="${(type == 2 or type == 3) and mem_id == 1}">
                                <button class="btn btn-default" onclick="location.href='boardInsert?type=${type}';"><i class="fa fa-fw fa-paper-plane"></i> 글작성</button>
                                </c:when>
                                <c:when test="${type == 1 and mem_id != 1}">
                                <button class="btn btn-default" onclick="location.href='boardInsert?type=1';"><i class="fa fa-fw fa-paper-plane"></i> 글작성</button>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </div>
                </div>    
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-border table-board">
                        <tr>
                            <td>번 호</td>
                            <td>제 목</td>
                            <td>작성자</td>
                            <td>작성일</td>
                            <td>조 회</td>
                        </tr>
                        <c:if test="${totCnt > 0 }">
                            <c:forEach var="board" items="${list}">
                            <tr>
                                <td>${startNum}</td>
                                <td>
                                    <c:if test="${board.brd_level > 0}">
                                        <img src="/Moviebokka/static/images/level.gif" width="${board.brd_level * 10}" height="15">
                                        <button class="btn btn-default btn-xs" type="button" disabled>Re</button>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${search == null}"><a href='/Moviebokka/board/boardView?type=${board.brd_type}&brd_id=${board.brd_id}&pageNum=${currentPage}'>${board.brd_title}</a></c:when>
                                        <c:when test="${search != null}"><a href='/Moviebokka/board/boardView?type=${board.brd_type}&brd_id=${board.brd_id}&pageNum=${currentPage}&search=${search}&keyword=${keyword}'>${board.brd_title}</a></c:when>
                                    </c:choose>
                                    <c:if test="${board.brd_cnt > 20}"></c:if>
                                </td>
                                <td>${board.mem_nick}</td>
                                <td>${board.brd_regdate}</td>
                                <td>${board.brd_cnt}</td>
                            </tr>
                            <c:set var="startNum" value="${startNum - 1 }" />
                            </c:forEach>
                        </c:if>
                        <c:if test="${totCnt == 0 }">
                            <tr><td colspan="5" class="text-center"><h4>등록된 게시물이 없습니다.</h4></td></tr>
                        </c:if>
                        </table>
                    </div>
                </div>
                <div class="row page-wrapper">
                    <div class="col-sm-12 text-right">
                        <nav>
                            <ul class="pagination">
                                <c:if test="${startPage > blockSize}">
                                    <c:choose>
                                        <c:when test="${search == null}">
                                            <li class="page-item"><a href='/Moviebokka/board/boardList?pageNum=${startPage - blockSize}&type=${type}'><span>&laquo;</span></a></li>
                                        </c:when>
                                        <c:when test="${search != null}">
                                            <li class="page-item"><a href='/Moviebokka/board/boardList?pageNum=${startPage - blockSize}&type=${type}&search=${search}&keyword=${keyword}'><span>&laquo;</span></a></li>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                    <c:choose>
                                        <c:when test="${currentPage == i}">
                                            <c:choose>
                                                <c:when test="${search == null}">
                                                    <li class="page-item active"><a href='/Moviebokka/board/boardList?pageNum=${i}&type=${type}'>${i}</a></li>
                                                </c:when>
                                                <c:when test="${search != null}">
                                                    <li class="page-item active"><a href='/Moviebokka/board/boardList?pageNum=${i}&type=${type}&search=${search}&keyword=${keyword}'>${i}</a></li>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${currentPage != i}">
                                            <c:choose>
                                                <c:when test="${search == null}">
                                                    <li class="page-item"><a href='/Moviebokka/board/boardList?pageNum=${i}&type=${type}'>${i}</a></li>
                                                </c:when>
                                                <c:when test="${search != null}">
                                                    <li class="page-item"><a href='/Moviebokka/board/boardList?pageNum=${i}&type=${type}&search=${search}&keyword=${keyword}'>${i}</a></li>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${endPage < pageCnt}">
                                    <c:choose>
                                        <c:when test="${search == null}">
                                            <li class="page-item active"><a href='/Moviebokka/board/boardList?pageNum=${startPage + blockSize}'><span>&raquo;</span></a></li>
                                        </c:when>
                                        <c:when test="${search != null}">
                                            <li class="page-item active"><a href='/Moviebokka/board/boardList?pageNum=${startPage + blockSize}&type=${type}&search=${search}&keyword=${keyword}'><span>&raquo;</span></a></li>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('.board-search .dropdown-menu li').on('click', function(){
        var type = $(this).data('type');
        $('#search-select').text($(this).text().trim());
        $('#search-select').attr('data-select', type);
        $('.board-search').removeClass('open');
    });
    
    $('.btn-board-search').on('click', function() {
        if($('#search-select').attr('data-select') == '') {
            alert("검색 항목을 선택하세요");
            setTimeout(function() {
                $('.board-search').addClass('open');
            });
            return;
        } else if($('#keyword').val().trim() == '') {
            alert("검색어를 입력하세요");
            $('#keyword').focus();
            return;
        } else if($('#keyword').val().trim().length < 2) {
            alert("검색어를 2글자 이상 입력하세요");
            $('#keyword').focus();
            return;
        } else {
        	var url = "boardList?type=" + ${type} + "&search=" + $('#search-select').attr('data-select') + "&keyword=" + $('#keyword').val().trim();
            window.location.href = url;
        }
    });
});
</script>
<jsp:include page="../partial/footer.jsp"/>