<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<header class="section__header">
    <section class="header_main border_bottom">
        <div class="container">
            <div class="row align-items-center">
			    <!-- Logo -->
        		<div class="col-xl-2 col-lg-3 col-md-12">
                    <c:choose>
		                <c:when test="${not empty user and fn:contains(user.getRole().getName(), 'USER')}">
		                    <a href="${basePath}/user/products" class="brand--wrap text-decoration-none"><h1 class="logo">STAR SHOP</h1>
		                    </a>
		                </c:when>
		                <c:otherwise>
		                    <a href="${basePath}" class="brand--wrap text-decoration-none"><h1 class="logo">STAR SHOP</h1>
		                    </a>
		                </c:otherwise>
		            </c:choose>
                </div>

                <!-- Search Bar -->
                <div class="col-xl-6 col-lg-5 col-md-6">
                    <form action="${basePath}/search" class="fs__form search--header">
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
                                    <a class="nav-link widget__view text-decoration-none dropdown-toggle d-flex align-items-center" href="#" role="button" id="accountDropdown" data-bs-toggle="dropdown" aria-expanded="false">
									    <div class="icon me-2">
									        <i class="fas fa-user-circle fa-2x"></i>
									    </div>
									    <small class="text">${user.getUserName()})</small>
									</a>
									<ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="accountDropdown">
									    <li><a class="dropdown-item" href="${basePath}/profile"><i class="fas fa-user me-2"></i>Profile</a></li>
									    <li><a class="dropdown-item" href="${basePath}/settings"><i class="fas fa-cog me-2"></i>Settings</a></li>
									    <li><hr class="dropdown-divider"></li>
									    <li><a class="dropdown-item" href="${basePath}/logout"><i class="fas fa-sign-out-alt me-2"></i>Logout</a></li>
									</ul>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link widget__view text-decoration-none" href="${basePath}/auth/login" role="button">
                                        <strong class="text">Login</strong>
                                    </a>
                                    <a class="nav-link widget__view text-decoration-none" href="${basePath}/auth/register" role="button">
                                        <strong class="text">Register</strong>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Wishlist -->
                        <div class="widget__header mr-2">
                            <c:if test="${not empty user and fn:contains(user.getRole().getName(), 'USER')}">
                                <a href="${basePath}/user/wishlist" class="widget__view text-decoration-none">
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
                                <a href="${basePath}/user/cart" class="widget__view text-decoration-none">
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
                        <a class="nav-link" href="${basePath}/home">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Categories
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${basePath}/categories/birthday-flowers">Birthday Flowers</a></li>
                            <li><a class="dropdown-item" href="${basePath}/categories/valentine-flowers">Valentine Day Flowers</a></li>
                            <li><a class="dropdown-item" href="${basePath}/categories/marriage-flowers">Marriage Flowers</a></li>
                            <li><a class="dropdown-item" href="${basePath}/categories/anniversary-flowers">Anniversary Flowers</a></li>
                            <li><a class="dropdown-item" href="${basePath}/categories/puja-flowers">Puja Flowers</a></li>
                            <li><a class="dropdown-item" href="${basePath}/categories/plants">Plants & Greenery</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${basePath}/categories/cactus">Cactus</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${basePath}/products">All Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${basePath}/about">About</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
