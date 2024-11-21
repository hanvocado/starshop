<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<header class="section__header">
    <section class="header_main border_bottom">
        <div class="container">
            <div class="row align-items-center">
                <!-- Logo -->
                <div class="col-xl-2 col-lg-3 col-md-12">
                    <a href="${pageContext.request.contextPath}/home" class="brand--wrap text-decoration-none">
                        <h1 class="logo">STAR SHOP</h1>
                    </a>
                </div>
                <!-- Search Bar -->
                <div class="col-xl-6 col-lg-5 col-md-6">
                    <form action="${pageContext.request.contextPath}/search" class="fs__form search--header">
                        <div class="input-group w-100">
                            <i class="fa fa-search"></i>
                            <input type="text" class="form-control" placeholder="What are you looking for?" name="query">
                        </div>
                    </form>
                </div>
                <!-- User Menu -->
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="widgets--wrap float-md-right">
                        <!-- Account -->
                        <div class="widget__header mr-2 dropdown">
                            <c:choose>
                                <c:when test="${not empty user and fn:contains(user.getRole().getName(), 'USER')}">
                                    <a class="nav-link widget__view text-decoration-none dropdown-toggle" href="#" role="button" id="accountDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                        <div class="icon">
                                            <i class="fas fa-user-circle"></i>
                                        </div>
                                        <small class="text">Account</small>
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="accountDropdown">
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/settings">Settings</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link widget__view text-decoration-none" href="${pageContext.request.contextPath}/login" role="button">
                                        <div class="icon">
                                            <i class="fas fa-user-circle"></i>
                                        </div>
                                        <small class="text">Login</small>
                                    </a>
                                    <a class="nav-link widget__view text-decoration-none" href="${pageContext.request.contextPath}/register" role="button">
                                        <div class="icon">
                                            <i class="fas fa-user-circle"></i>
                                        </div>
                                        <small class="text">Register</small>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Wishlist -->
                        <div class="widget__header mr-2">
                            <c:if test="${not empty user and fn:contains(user.getRole().getName(), 'USER')}">
                                <a href="${pageContext.request.contextPath}/user/${user.id}/wishlist" class="widget__view text-decoration-none">
                                    <div class="icon">
                                        <i class="fa fa-heart"></i>
                                    </div>
                                    <small class="text">Wishlist</small>
                                </a>
                            </c:if>
                        </div>
                        <!-- Shopping Cart -->
                        <div class="widget__header">
                            <c:if test="${not empty user and fn:contains(user.getRole().getName(), 'USER')}">
                                <a href="${pageContext.request.contextPath}/cart" class="widget__view text-decoration-none">
                                    <div class="icon">
                                        <i class="fa fa-shopping-cart"></i>
                                    </div>
                                    <small class="text">Cart</small>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Navbar -->
    <nav class="navbar navbar-main navbar-expand-lg border__bottom">
        <div class="container">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main_nav" aria-controls="main_nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
            </button>
            <div class="collapse navbar-collapse" id="main_nav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Categories
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories/birthday-flowers">Birthday Flowers</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories/valentine-flowers">Valentine Day Flowers</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories/marriage-flowers">Marriage Flowers</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories/anniversary-flowers">Anniversary Flowers</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories/puja-flowers">Puja Flowers</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories/plants">Plants & Greenery</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/categories/cactus">Cactus</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/products">All Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
