<%--
  Created by IntelliJ IDEA.
  User: TTC
  Date: 2/21/2021
  Time: 7:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- HEADER MOBILE-->
<header class="header-mobile d-block d-lg-none">
    <div class="header-mobile__bar">
        <div class="container-fluid">
            <div class="header-mobile-inner">
                <a class="logo" href="index.html">
                    <img src="images/icon/logo.png" alt="/Admin-post"/>
                </a>
                <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                </button>
            </div>
        </div>
    </div>
    <nav class="navbar-mobile">
        <div class="container-fluid">
            <ul class="navbar-mobile__list list-unstyled">
                <li>
                    <a href="/Admin-post">
                        <i class="fas fa-chart-bar"></i>Post Management</a>
                </li>
                <li>
                    <a href="/Admin-category">
                        <i class="fas fa-chart-bar"></i>Category Management</a>
                </li>
                <li>
                    <a href="/home">
                        <i class="fas fa-chart-bar"></i>Home</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<!-- END HEADER MOBILE-->

<!-- MENU SIDEBAR-->
<aside class="menu-sidebar d-none d-lg-block">
    <div class="logo">
        <a href="/post">
            <img src="images/icon/logo.png" alt="Cool Admin"/>
        </a>
    </div>
    <div class="menu-sidebar__content js-scrollbar1">
        <nav class="navbar-sidebar">
            <ul class="list-unstyled navbar__list">
                <li>
                    <a href="/Admin-post">
                        <i class="fas fa-chart-bar"></i>Post Management</a>
                </li>
                <li>
                    <a href="/Admin-category">
                        <i class="fas fa-chart-bar"></i>Category Management</a>
                </li>
                <li>
                    <a href="/home">
                        <i class="fas fa-chart-bar"></i>Home</a>
                </li>
            </ul>
        </nav>
    </div>
</aside>
<!-- END MENU SIDEBAR-->
</body>
</html>
