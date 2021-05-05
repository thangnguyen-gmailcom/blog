<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TTC
  Date: 2/25/2021
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Blog Single</title>
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

<section class="site-section py-lg">
    <div class="container">

        <div class="row blog-entries">
            <div class="col-md-12 col-lg-8 main-content">
                <h1 class="mb-4">${post.title}</h1>
                <div class="post-meta">
                    <span class="category">${post.category.categoryName}</span>
                    <span class="mr-2">${post.publishDate} </span> &bullet;
                </div>
                <div class="post-content-body">
                    <div class="row mb-5">
                        <div class="col-md-12 mb-4 element-animate">
                            <img src="<c:url value="${post.image}"/>" alt="Image placeholder" class="img-fluid">
                        </div>
                    </div>
                    <div class="row mb-5">
                        <div class="col-md-12 mb-4 element-animate">
                    ${post.fullContent}
                        </div>
                    </div>
                </div>

                <div class="pt-5">
                    <p>Categories: <a
                            href="/home?action=listPostInCategory&id=${post.category.id}">${post.category.categoryName}</a>
                    </p>
                </div>


                <div class="pt-5">
                    <ul class="comment-list">
                        <c:forEach items="${commentList}" var="comment">
                        <li class="comment">
                            <div class="comment-body">
                                <h3>${comment.name}</h3>
                                <div class="meta">${comment.dateComment}</div>
                                <p>${comment.content}</p>
                                <p><a href="#" class="reply">Reply</a></p>
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                    <!-- END comment-list -->

                    <div class="comment-form-wrap pt-5">
                        <h3 class="mb-5">Leave a comment</h3>
                        <form action="/comment?action=insertComment" method="post" class="p-5 bg-light">
                            <div class="form-group">
                                <input type="hidden" class="form-control" value="${post.id}"  name="postId">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="user name" name="username">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="gmail" name="email">
                            </div>
                            <div class="form-group">
                                <label for="message">Message</label>
                                <textarea name="message" id="message" cols="30" rows="10" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Post Comment" class="btn btn-primary">
                            </div>

                        </form>
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
