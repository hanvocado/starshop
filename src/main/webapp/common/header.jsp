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
						<c:when
							test="${not empty user and fn:contains(user.getRole(), 'CUSTOMER')}">
							<a href="${basePath}/customer/products"
								class="brand--wrap text-decoration-none"><h1 class="logo">STAR
									SHOP</h1> </a>
						</c:when>
						<c:otherwise>
							<a href="${basePath}" class="brand--wrap text-decoration-none"><h1
									class="logo">STAR SHOP</h1> </a>
						</c:otherwise>
					</c:choose>
				</div>

				<!-- Search Bar -->
				<div class="col-xl-6 col-lg-5 col-md-6">
					<form action="${basePath}/customer/products" class="fs__form search--header">
						<div class="input-group w-100">
							<i class="fa fa-search"></i> <input type="text"
								class="form-control" placeholder="What are you looking for?"
								name="search">
						</div>
					</form>
				</div>
				<!-- User Menu -->
				<div class="col-xl-4 col-lg-4 col-md-6">
					<div class="widgets--wrap float-md-right">

						<!-- Wishlist -->
						<div class="widget__header mr-2">
							<c:if
								test="${not empty user and fn:contains(user.getRole(), 'CUSTOMER')}">
								<a href="${basePath}/customer/wishlist"
									class="widget__view text-decoration-none">
									<div class="icon">
										<i class="fa fa-heart"></i>
									</div> <small class="text">Wishlist</small>
								</a>
							</c:if>
						</div>
						<!-- Shopping Cart -->
						<div class="widget__header">
							<c:if
								test="${not empty user and fn:contains(user.getRole(), 'CUSTOMER')}">
								<a href="${basePath}/customer/cart"
									class="widget__view text-decoration-none">
									<div class="icon">
										<i class="fa fa-shopping-cart"></i>
									</div> <small class="text">Cart</small>
								</a>
							</c:if>
						</div>

						<!-- Account -->
						<div class="widget__header mr-2 nav-item dropdown">
							<c:choose>
								<c:when
									test="${not empty user and fn:contains(user.getRole(), 'CUSTOMER')}">
									<!-- Account Dropdown -->
									<div class="widget__header mr-2 nav-item dropdown">
									    <a class="nav-link widget__view text-decoration-none dropdown-toggle d-flex align-items-center"
									       href="#" id="accountDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									        <c:choose>
											<c:when test="${not empty user.profileImg}">
												<img src="${user.profileImg}" alt="Avatar"
													class="rounded-circle" style="width: 40px; height: 40px;">
											</c:when>
											<c:otherwise>
												<img src="${basePath}/shop/images/default_avatar.jpg"
													alt="Default Avatar" class="rounded-circle"
													style="width: 40px; height: 40px;">
											</c:otherwise>
										</c:choose> <small class="text ml-1 container">${user.getUserName()}</small>
									    </a>
									    <ul class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="accountDropdown">
									        <li><a class="dropdown-item" href="${basePath}/customer/account/profile">Hồ sơ cá nhân</a></li>
									        <li><a class="dropdown-item" href="${basePath}/customer/orders">Đơn mua</a></li>
									        <li>
									            <hr class="dropdown-divider">
									        </li>
									        <li>
									            <form action="${basePath}/auth/logout" method="post" style="margin: 0;">
									                <button class="dropdown-item" type="submit">Logout</button>
									            </form>
									        </li>
									    </ul>
									</div>

								</c:when>
								<c:otherwise>
									<a class="nav-link widget__view text-decoration-none"
										href="${basePath}/auth/login" role="button"> <strong
										class="text">Login</strong>
									</a>
									<a class="nav-link widget__view text-decoration-none"
										href="${basePath}/auth/register" role="button"> <strong
										class="text">Register</strong>
									</a>
								</c:otherwise>
							</c:choose>
						</div>


					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Navbar -->
	<nav class="navbar navbar-main navbar-expand-lg border__bottom">
		<div class="container">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#main_nav" aria-controls="main_nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
			</button>
			<div class="collapse navbar-collapse" id="main_nav">
			    <ul class="navbar-nav">
			        <c:choose>
			            <c:when test="${not empty user and fn:contains(user.getRole(), 'CUSTOMER')}">
			                <li class="nav-item">
			                    <a class="nav-link" href="${basePath}/customer/products">Home</a>
			                </li>
			            </c:when>
			            <c:otherwise>
			                <li class="nav-item">
			                    <a class="nav-link" href="${basePath}/">Home</a>
			                </li>
			            </c:otherwise>
			        </c:choose>
			
			        <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					        Categories
					    </a>
					    <ul class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="navbarDropdown">
					        <c:forEach var="category" items="${categories}">
					            <li><a class="dropdown-item" href="${basePath}/categories/${category.name}">${category.name}</a></li>
					        </c:forEach>
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



<script>
    $(document).on('click', '.dropdown', function (event) {
        event.stopPropagation();
    });
</script>




