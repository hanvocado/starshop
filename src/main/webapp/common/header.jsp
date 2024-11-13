<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<header class="section__header">
    <section class="header_main border_bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-xl-2 col-lg-3 col-md-12">
                    <a href="" class="brand--wrap text-decoration-none">
                        <h1 class="logo">STAR SHOP</h1>
                    </a>
                    <!-- brand--wrap.// -->
                </div>
                <!-- col.// -->
                <div class="col-xl-6 col-lg-5 col-md-6">
                    <form action="#" class="fs__form search--header">
                        <div class="input-group w-100">
                            <i class="fa fa-search"></i>
                            <input type="text" class="form-control" placeholder="Why are you searching? When we have some exciting categories...">

                        </div>
                    </form>
                    <!-- search--wrap.// -->
                    
                </div>
                <!-- col.// -->
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="widgets--wrap float-md-right">
                        <div class="widget__header mr-2 ">
                            <a class="nav-link widget__view text-decoration-none" href="{% url 'login-page' %}" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <div class="'icon">
                                  <i class="fas fa-user-circle"></i>
                                </div>
                                <small class="text">Account</small>
                              </a>
                              <ul class="dropdown-menu">
                                <%-- <%	
                        
                        	if (userId != null) {
                        %>
                        		<li><a class="dropdown-item" href="{% url 'signout-page' %}">Logout </a></li>
                        	<%
                        	} else {
                        	%>
                        		<li><a class="dropdown-item" href="{% url 'login-page' %}">Login </a></li>
                                <li><a class="dropdown-item" href="{% url 'signup-page' %}">Sign Up</a></li>
                        	<%
                        	}                        	
                        	%> --%>
                              </ul>
                              
                              
                        </div>
                        <div class="widget__header mr-2">
                            <%-- <a href="/user/${user.id}/wishlist" class="widget__view text-decoration-none">
                                <div class="icon">
                                    <i class="fa fa-heart"></i>
                                </div>
                                <small class="text">WishList</small>
                            </a> --%>
                            <c:if test="${not empty user}">
						       <a href="/user/${user.id}/wishlist" class="widget__view text-decoration-none">
						           <div class="icon">
						               <i class="fa fa-heart"></i>
						           </div>
						           <small class="text">WishList</small>
						       </a>
						   </c:if>
                        </div>
                        <div class="widget__header">
                            <a href="{% url 'cart-page' %}" class="widget__view text-decoration-none">
                                <div class="icon">
                                    <i class="fa fa-shopping-cart"></i>
                                </div>
                                <small class="text">Shopping cart</small>
                            </a>
                        </div>
                    </div>
                    <!-- widgets--wrap.// -->
                </div>
                <!-- col.// -->
            </div>
            <!-- row.// -->
        </div>
        <!-- container.// -->
    </section>
    <!-- header__main.// -->

    <nav class="navbar navbar-main navbar-expand-lg border__bottom">
        <div class="container">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main_nav" aria-controls="main_nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
            </button>
    
            <div class="collapse navbar-collapse" id="main_nav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="{% url 'home-page' %}">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Categories
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="{% url 'category-page' %}">Birthday Flowers</a></li>
                            <li><a class="dropdown-item" href="{% url 'valentineday-page' %}">Valentine Day Flowers</a></li>
                            <li><a class="dropdown-item" href="{% url 'marriage-page' %}">Marriage Flowers</a></li>
                            <li><a class="dropdown-item" href="{% url 'anniversary-page' %}">Anniversary Flowers</a></li>
                            <li><a class="dropdown-item" href="{% url 'puja-page' %}">Puja Flowers</a></li>
                            <li><a class="dropdown-item" href="{% url 'plant-page' %}">Plants & Greenery</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="{% url 'cactus-page' %}">Cactus</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="{% url 'gift-page' %}">All Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="{% url 'about-page' %}">About</a>
                    </li>
                </ul>
            </div>
            <!-- collapse.// -->
        </div>
        <!-- container.// -->
    </nav>
    

</header>