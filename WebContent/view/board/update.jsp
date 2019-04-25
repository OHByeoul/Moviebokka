<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${acl == 0}">
    <c:redirect url="/Moviebokka/user/login"></c:redirect>
</c:if>
<c:if test="${type != 1 and mem_id != 1}">
    <c:redirect url="/Moviebokka/board/boardList?type=1"></c:redirect>
</c:if>
<jsp:include page="../partial/header.jsp"/>
<title>
<c:choose>
    <c:when test="${type == 1}">자유게시판 글수정</c:when>
    <c:when test="${type == 2}">공지사항 글수정</c:when>
    <c:when test="${type == 3}">FAQ 글수정</c:when>
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
            <div class="container mboard board-input-wrapper">
                <div class="row">
                    <div class="col-sm-12 text-center board-title"><h3>글 수정</h3></div>
                </div>
                <div class="row">
                    <div class="col-sm-12"><hr class="hr-top" /></div>
                </div>
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-2">
                        <div class="well">
                            <p class="h4"><i class="fa fa-exclamation-circle"></i> 게시판 운영원칙</p>
                            <p>아래 사항에 해당되는 글을 게시할 경우 글이 <span class="text-danger">삭제되거나 글쓰기가 제한</span> 될 수 있습니다.</p>
                            <ol>
                                <li>
                                    유언비어 및 비방성 게시물
                                    <ul class="list-unstyled">
                                        <li> - 유언비어, 인신공격 및 비방성 글 (실명이 거론된 비방성 글로 인해 상대방이 불쾌감을 느끼는 글)</li>
                                        <li> - 욕설 및 욕을 변형한 단어가 포함된 글</li>
                                        <li> - 분란이나 분쟁을 유도하는 글</li>
                                        <li> - 타인 또는 타 단체의 권리를 침해하거나 명예를 훼손하는 내용</li>
                                    </ul>
                                </li>
                                <li>
                                    음란물의 게재 등 공공질서와 미풍양속을 해치는 게시물
                                    <ul class="list-unstyled">
                                        <li> - 음란물 게시 또는 음란 사이트를 링크한 글 </li>
                                        <li> - 폭력행위를 미화하거나, 퇴폐적인 행위를 미화하여 혐오감을 주는 글</li>
                                    </ul>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <form class="form-horizontal" action="/Moviebokka/board/boardUpdatePro" method="post">
                        <input type="hidden" name="brd_id" value="${board.brd_id}">
                        <input type="hidden" name="pageNum" value="${pageNum}">
                        <input type="hidden" name="type" value="${board.brd_type}">
                        <c:if test="${not empty search}">
                        <input type="hidden" name="search" value="${search}">
                        <input type="hidden" name="keyword" value="${keyword}">
                        </c:if>
                        <div class="form-group">
                            <label for="brd_title" class="col-sm-2 control-label">제 목</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control input-lg" name="brd_title" id="brd_title" value="${board.brd_title}" required="required" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">작성자</label>
                            <div class="col-sm-10"><span class="help-block">${board.mem_nick}</span></div>
                        </div>
                        <div class="form-group">
                            <label for="brd_content" class="col-sm-2 control-label">내 용</label>
                            <div class="col-sm-10">
                                <textarea name="brd_content" id="brd_content" rows="10" class="form-control" required="required">${board.brd_content}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12"><hr class="hr-bottom" /></div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="row">
                                    <div class="col-sm-12 text-center">
                                        <button class="btn btn-default btn-submit" type="submit"><i class="fa fa-fw fa-paper-plane"></i> 수 정</button>
                                        <button class="btn btn-default btn-cancel" type="button"><i class="fa fa-fw fa-reply"></i> 취 소</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('#brd_title').focus();
    $('.btn-cancel').on('click', function(){
        history.back();
    });
});
</script>
<jsp:include page="../partial/footer.jsp"/>