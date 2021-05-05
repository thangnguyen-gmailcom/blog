<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TTC
  Date: 2/24/2021
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-md-12 col-lg-4 sidebar">
    <div class="sidebar-box search-form-wrap">
        <form action="#" class="search-form">
            <div class="form-group">
                <span class="icon fa fa-search"></span>
                <input type="text" class="form-control" id="s" placeholder="Type a keyword and hit enter">
            </div>
        </form>
    </div>
    <!-- END sidebar-box -->
    <div class="sidebar-box">
        <div class="bio text-center">
            <img src="balita/images/person_1.jpg" alt="Image Placeholder" class="img-fluid">
            <div class="bio-body">
                <h2>Meagan Smith</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem facilis sunt
                    repellendus excepturi beatae porro debitis voluptate nulla quo veniam fuga sit molestias
                    minus.</p>
                <p><a href="#" class="btn btn-primary btn-sm">Read my bio</a></p>
                <p class="social">
                    <a href="#" class="p-2"><span class="fa fa-facebook"></span></a>
                    <a href="#" class="p-2"><span class="fa fa-twitter"></span></a>
                    <a href="#" class="p-2"><span class="fa fa-instagram"></span></a>
                    <a href="#" class="p-2"><span class="fa fa-youtube-play"></span></a>
                </p>
            </div>
        </div>
    </div>
    <!-- END sidebar-box -->
    <div class="sidebar-box">
        <h3 class="heading">Most Viewed Post</h3>
        <div class="post-entry-sidebar">
            <ul>
                <c:forEach var="post" items="${top3PostInView}">
                    <li>
                        <a href="/home?action=blogSingle&id=${post.id}">
                            <img src="<c:url value="${post.image}" />" alt="Image placeholder" class="mr-4" style="width: 120px; height: 90px;">
                            <div class="text">
                                <h4>${post.title}</h4>
                                <div class="post-meta">
                                    <span class="mr-2">${post.publishDate} </span> &bullet;
                                    <span class="ml-2"><span class="fa fa-comments"></span> 3</span>
                                </div>
                            </div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- END sidebar-box -->

    <div class="sidebar-box">
        <h3 class="heading">Categories</h3>
        <ul class="categories">
            <c:forEach items="${categories}" var="category">
                <li>
                    <a href="/home?action=listPostInCategory&id=${category.id}&categoryName=${category.categoryName}">${category.categoryName}
                        </a></li>
            </c:forEach>
        </ul>
    </div>
    <!-- END sidebar-box -->

    <div class="sidebar-box">
        <h3 class="heading">Tags</h3>
        <ul class="tags">
            <c:forEach items="${tagList}" var="tag">
            <li><a href="#">${tag.tagName}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
