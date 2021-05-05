<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TTC
  Date: 2/21/2021
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- HEADER DESKTOP-->
<header class="header-desktop">
    <div class="section__content section__content--p30">
        <div class="container-fluid">
            <div class="header-wrap">
                <form class="form-header" action="" method="POST">
                    <input class="au-input au-input--xl" type="text" name="search"
                           placeholder="Search for datas &amp; reports..."/>
                    <button class="au-btn--submit" type="submit">
                        <i class="zmdi zmdi-search"></i>
                    </button>
                </form>
                <div class="header-button">
                    <c:if test="${sessionScope.user != null}">
                        <div class="account-wrap">
                            <div class="account-item clearfix js-item-menu">
                                <div class="image">
                                    <img src="<c:url value="${sessionScope.user.imageUser}"/>" alt="John Doe"/>
                                </div>
                                <div class="content">
                                    <a class="js-acc-btn" href="#">${sessionScope.user.userName}</a>
                                </div>
                                <div class="account-dropdown js-dropdown">
                                    <div class="info clearfix">
                                        <div class="image">
                                            <a href="#">
                                                <img src="<c:url value="${sessionScope.user.imageUser}"/>" alt="John Doe"/>
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h5 class="name">
                                                <a href="#">${sessionScope.user.userName}</a>
                                            </h5>
                                            <span class="email">${sessionScope.user.email}</span>
                                        </div>
                                    </div>
                                    <div class="account-dropdown__body pl-3">
                                        <a href="/logout" class="text-dark">
                                            <i class="zmdi zmdi-power"></i>Logout
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- END HEADER DESKTOP-->
</body>
</html>
