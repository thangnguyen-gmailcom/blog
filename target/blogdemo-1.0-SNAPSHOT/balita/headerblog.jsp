<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TTC
  Date: 2/24/2021
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<header role="banner">
    <div class="top-bar">
        <div class="container">
            <div class="row">
                <div class="col-4 social">
                    <a href="#"><span class="fa fa-twitter"></span></a>
                    <a href="#"><span class="fa fa-facebook"></span></a>
                    <a href="#"><span class="fa fa-instagram"></span></a>
                    <a href="#"><span class="fa fa-youtube-play"></span></a>
                    <a href="#"><span class="fa fa-vimeo"></span></a>
                    <a href="#"><span class="fa fa-snapchat"></span></a>
                </div>
                <div class="col-3 search-top">
                    <!-- <a href="#"><span class="fa fa-search"></span></a> -->
                    <form action="#" class="search-top-form">
                        <span class="icon fa fa-search"></span>
                        <input type="text" id="s" placeholder="Type keyword to search...">
                    </form>
                </div>
                <div class="col-5 text-right">
                    <c:if test="${sessionScope.user == null}">
                        <a class="text-white" href="/user?action=login">Login |</a>
                        <a class="text-white" href="/user?action=signup">Sign up</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <span class="text-white">Helo ${sessionScope.user.userName}</span>
                        <button class="btn btn-black dropdown-toggle " type="button" id="dropdownMenuButton"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="border-radius: 50%">
                            <img style="width: 45px; height: 45px; border-radius: 50%" src="<c:url value="${sessionScope.user.imageUser}" />" alt="">
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <c:if test="${sessionScope.user.isAdmin == 1}">
                                <a class="dropdown-item" href="/Admin-post">Manager Page </a>
                            </c:if>
                            <a class="dropdown-item" href="#">Setting</a>
                        </div>
                        <a class="text-white" href="/logout">logout</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <div class="container logo-wrap">
        <div class="row pt-5">
            <div class="col-12 text-center">
                <a class="absolute-toggle d-block d-md-none" data-toggle="collapse" href="#navbarMenu" role="button"
                   aria-expanded="false" aria-controls="navbarMenu"><span class="burger-lines"></span></a>
                <h1 class="site-logo"><a href="index.html">Cool</a></h1>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-expand-md  navbar-light bg-light">
        <div class="container">
            <div class="collapse navbar-collapse" id="navbarMenu">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="/home">Home</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="category.html" id="dropdown05" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">Categories</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown05">
                            <c:forEach items="${categories}" var="category">
                                <a class="dropdown-item"
                                   href="/home?action=listPostInCategory&id=${category.id}&categoryName=${category.categoryName}">${category.categoryName}</a>
                            </c:forEach>
                        </div>

                    </li>
                </ul>

            </div>
        </div>
    </nav>
</header>
</body>
</html>
