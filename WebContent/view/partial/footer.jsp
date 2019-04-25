<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
footer {
    color: #ccc;
    background-color: #222;
}
.footer-content {
    padding-top: 45px;
    text-align: center;
}
.footer-content p {
    font-size: 16px;
}
.footer-logo {
    margin-bottom: 15px;
}
.footer-social {
    margin-bottom: 45px;
}
.footer-social a {
    display: inline-block;
    width: 34px;
    height: 34px;
    background-color: #ccc;
}
.footer-social i {
    color: #222;
    font-size: 22px;
    line-height: 34px;
}
.subfooter {
    display: flex;
    margin: 15px 0;
}
.subfooter p:first-child {
    text-align: left;
    margin-right: auto;
}
.subfooter p {
    font-size: 14px;
}

.subfooter i {
    color: #f7931d;
}
</style>
<footer>
    <div class="container">
        <div class="footer-content">
            <div class="footer-logo">
                <h4><a href="/Moviebokka/movie/main">MOVIEBOKKA</a></h4>
            </div>
            <p>주소 : 서울특별시 마포구 신촌로 176</p>
            <div class="footer-social">
                <a target="_blank" href="https://www.facebook.com/moviebokka/"><i class="fa fa-facebook"></i></a> 
                <a target="_blank" href="https://twitter.com/moviebokka/"><i class="fa fa-twitter"></i></a>
            </div>
        </div>
        <div class="subfooter">
            <p>MovieBokka © 2019 - All rights reserved</p>
            <p>Made with <i class="fa fa-heart"></i> by <a target="_blank" href="/Moviebokka/movie/main">Revisio</a></p>
            <div class="clearfix"></div>
        </div>
    </div>
</footer>
</body>
</html>