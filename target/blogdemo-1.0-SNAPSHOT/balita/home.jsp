<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TTC
  Date: 2/21/2021
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Home</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700" rel="stylesheet">

    <link rel="stylesheet" href="balita/css/bootstrap.css">
    <link rel="stylesheet" href="balita/css/animate.css">
    <link rel="stylesheet" href="balita/css/owl.carousel.min.css">

    <link rel="stylesheet" href="balita/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="balita/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="balita/fonts/flaticon/font/flaticon.css">

    <!-- Theme Style -->
    <link rel="stylesheet" href="balita/css/style.css">
</head>
<body>

<jsp:include page="headerblog.jsp"></jsp:include>
<!-- END header -->

<section class="site-section pt-5">
    <div class="container">
        <div class="row">
            <div class="col-md-12">

                <div class="owl-carousel owl-theme home-slider">
                    <c:forEach items="${top3Post}" var="post">
                        <div>
                            <a href="/home?action=blogSingle&id=${post.id}" class="a-block d-flex align-items-center height-lg"
                               style="background-image: url('${post.image}'); ">
                                <div class="text half-to-full">
                                    <div class="post-meta">
                                        <span class="category">${post.category.categoryName}</span>
                                        <span class="mr-2">${post.publishDate} </span> &bullet;
                                        <span class="ml-2"><span class="fa fa-comments"></span> 3</span>
                                    </div>
                                    <h3>${post.title}</h3>
                                    <p>${post.shortContent}</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
        <div class="row">
            <c:forEach items="${top3Post}" var="post">
                <div class="col-md-6 col-lg-4">
                    <a href="/home?action=blogSingle&id=${post.id}" class="a-block d-flex align-items-center height-md"
                       style="background-image: url('${post.image}'); ">
                        <div class="text">
                            <div class="post-meta">
                                <span class="category">${post.category.categoryName}</span>
                                <span class="mr-2">${post.publishDate} </span> &bullet;
                                <span class="ml-2"><span class="fa fa-comments"></span> 3</span>
                            </div>
                            <h3>${post.title}</h3>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>


</section>
<!-- END section -->

<section class="site-section py-sm">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h2 class="mb-4">All Posts</h2>
            </div>
        </div>
        <div class="row blog-entries">
            <div class="col-md-12 col-lg-8 main-content">
                <div class="row">
                    <c:forEach items="${postListPage}" var="post">
                        <div class="col-md-6">
                            <a href="/home?action=blogSingle&id=${post.id}" class="blog-entry element-animate"
                               data-animate-effect="fadeIn">
                                <img style="width: 350px; height: 200px;" src="<c:url value="${post.image}"/>"
                                     alt="Image placeholder">
                                <div class="blog-content-body">
                                    <div class="post-meta">
                                        <span class="category">${post.category.categoryName}</span>
                                        <span class="mr-2">${post.publishDate} </span> &bullet;
                                        <span class="ml-2"><span class="fa fa-comments"></span> 3</span>
                                    </div>
                                    <h2 class="text-truncate">${post.title}</h2>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="col-md-12 text-center">
                        <nav aria-label="Page navigation" class="text-center">
                            <ul class="pagination">
                                <li class="page-item  active"><a class="page-link" href="home?page=${back}">Prev</a>
                                </li>
                                <c:forEach begin="${start}" end="${end}" var="i">
                                    <li class="page-item"><a class="page-link" href="home?page=${i}">${i}</a></li>
                                </c:forEach>
                                <c:if test="${isDotDot}">
                                    <li class="page-item"><a class="page-link">...</a></li>
                                </c:if>
                                <li class="page-item"><a class="page-link" href="home?page=${next}">Next</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>


                <div class="row mb-5 mt-5">

                    <div class="col-md-12">
                        <h2 class="mb-4">More Blog Posts</h2>
                    </div>

                    <div class="col-md-12">
                        <c:forEach items="${randomPost}" var="post">
                            <div class="post-entry-horzontal">
                                <a href="/home?action=blogSingle&id=${post.id}" class="w-100">
                                    <div class="image element-animate" data-animate-effect="fadeIn"
                                         style="background-image: url('<c:url value="${post.image}"/>');"></div>
                                    <span class="text">
                      <div class="post-meta">
                        <span class="category">${post.category.categoryName}</span>
                        <span class="mr-2">${post.publishDate} </span> &bullet;
                        <span class="ml-2"><span class="fa fa-comments"></span> 3</span>
                      </div>
                      <h2>${post.title}</h2>
                    </span>
                                </a>
                            </div>
                            <!-- END post -->
                        </c:forEach>
                    </div>
                </div>


            </div>

            <!-- END main-content -->
            <jsp:include page="bodyright.jsp"></jsp:include>
            <!-- END sidebar -->

        </div>
    </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
<!-- END footer -->

<!-- loader -->
<div id="loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#f4b214"/>
    </svg>
</div>

<script src="balita/js/jquery-3.2.1.min.js"></script>
<script src="balita/js/jquery-migrate-3.0.0.js"></script>
<script src="balita/js/popper.min.js"></script>
<script src="balita/js/bootstrap.min.js"></script>
<script src="balita/js/owl.carousel.min.js"></script>
<script src="balita/js/jquery.waypoints.min.js"></script>
<script src="balita/js/jquery.stellar.min.js"></script>


<script src="balita/js/main.js"></script>
</body>
</html>
