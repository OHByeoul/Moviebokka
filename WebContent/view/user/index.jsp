<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>무비보까</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean">
<style>
html, body {
    font-family: "Nanum Gothic", sans-serif !important;
    color: #fff;
    background-color: #333;
}

.intro-top {
    min-height: 80px;
}

.intro-middle h1 {
    font-size: 800;
    font-weight: bold;
}

.intro-middle {
    min-height: 80px;
}

.intor-info {
    color: #333;
}

.list-group {
    color: #333;
}

.btn-box {
    margin-top: 30px;
}
.btn-enter {
    border-radius: 0;
    margin: 0 !important;
}

.jumbotron {
    padding: 20px !important;
    color: #333;
    text-align: left;
    border-radius: 0;
}
</style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col align-self-center intro-top"></div>
    </div>
    <div class="row">
        <div class="col"></div>
        <div class="col text-center intro-middle">
            <p class="h1">MovieBokka</p>
        </div>
        <div class="col"></div>
    </div>
    <div class="row">
        <div class="col"></div>
        <div class="col text-center intro-info">
            <div class="jumbotron">
                <h4 class="text-center">소개</h4>
                <hr class="my-4">
                <p>영화 평가 사이트</p>
                <p>토탈 온라인 문화평가 시스템</p>
            </div>
        </div>
        <div class="col"></div>
    </div>
    <div class="row">
        <div class="col"></div>
        <div class="col">
             <ul class="list-group list-group-flush w-100 align-items-stretch">
                <li class="list-group-item text-center"><strong>팀 원</strong></li>
                <li class="list-group-item">오한별</li>
                <li class="list-group-item">유인선</li>
                <li class="list-group-item">박준민</li>
                <li class="list-group-item">이재인</li>
                <li class="list-group-item">장철혁</li>
                <li class="list-group-item">이인수</li>
                <li class="list-group-item">위선남</li>
            </ul>
        </div>
        <div class="col"></div>
    </div>
    <div class="row btn-box">
        <div class="col"></div>
    	<div class="col">
            <a href="/Moviebokka/movie/main" class="btn btn-secondary btn-block btn-enter btn-lg">Enter</a>
        </div>
        <div class="col"></div>
    </div>
</div>
</body>
</html>