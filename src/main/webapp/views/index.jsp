<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>index page</title>

<body>
 	<section class="section__slider">
        <section class="hero-text">
            <div class="hero" data-arrows="true" data-autoplay="true">
                <div class="hero-slide">
                    <img alt="Slider Image 1" class="img-responsive cover" src="/shop/images/h3-slider-background-1.jpg">
                    <div class="container">
                        <div class="header-content slide-content col-lg-4">
                            <h1 class="mb-3">Send a bouquet of flowers to express a thousand <span class="color__main">love</span></h1>
                            <p>Tulips symbolize love and romance. This type of flower always carries passion and allure in its beauty and simplicity.</p>
    
                            <a href="{% url 'cart-page"><button class="fs__button custom-btn btn w-max mt-2" type="button" style="width: 140px; height: 40px;">Order Now</button></a>
                        </div>
                    </div>
                </div>
                <!--.hero-slide-->
    
                <div class="hero-slide">
                    <img alt="Slider Image 2" class="img-responsive cover" src="/shop/images/h3-slider-background-2.jpg">
                    <div class="container">
                        <div class="header-content slide-content col-lg-4">
                            <h1 class="mb-3">Flowers and Ornamental Plants for Space Decoration</h1>
                            <p>Cacti symbolize strong will, resilience in overcoming challenges, refusal to be subdued, and adaptability for survival.</p>
                            <a href="{% url 'gift-page"><button class="fs__button custom-btn btn w-max mt-2" type="button" style="width: 140px; height: 40px;">View More</button></a>
                        </div>
                    </div>
                </div>
                <!--.hero-slide-->
    
                <div class="hero-slide">
                    <img alt="Slider Image 3" class="img-responsive cover" src="/shop/images/h3-slider-background-3.jpg">
                    <div class="container">
                        <div class="header-content slide-content col-lg-4">
                            <h1 class="mb-3">Diverse Types of Flowers and Decorative Plants</h1>
                            <p>Decorating homes with plants and flowers makes people feel close to nature, enhancing the beauty and freshness of the room.</p>
                            
                            <a href="{% url 'about-page"><button class="fs__button custom-btn btn w-max mt-2" type="button" style="width: 140px; height: 40px;">See More</button></a>
                        </div>
                    </div>
                </div>
                <!--.hero-slide-->
            </div>
            <!--.hero-->
        </section>
    </section>
    <!-- section__slider.// -->

    <section class="section__deal my-5">
        <div class="container">
            <div class="product-top-bar section-border mb-5">
                <div class="section-title-wrap">
                    <h3 class="section-title section-bg-gray">Our main products</h3>
                </div>
            </div>
            <div class="single-best-selling">
          <div class="row">
    <c:forEach var="product" items="${products}">
        <div class="col-md-4">
          <a href="${pageContext.request.contextPath}/customer/products/${product.id}" style="text-decoration: none; color: inherit;">
            <div class="card product-card">
            	<c:if test="${product.image.substring(0,5)=='https'}">
					<c:url value="${product.image }" var="imgUrl"></c:url>
				  </c:if>
				  <c:if test="${product.image.substring(0,5)!='https'}">
					<c:url value="/img/${product.image }" var="imgUrl"></c:url>
				  </c:if>
                <img src="${imgUrl}" class="card-img-top product-image" alt="${product.name}" height=350>
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <div>
                    </div>
                    <div class="d-flex justify-content-start">
					    <!-- Add to Cart Button -->
					    <div class="mr-2">
						    <form action="${pageContext.request.contextPath}/customer/cart/add/${product.id}" method="POST">
						        <button class="fs__button custom-btn btn w-max mt-2" type="submit" style="width: 140px; height: 40px;">
						            Add to Cart
						        </button>
						    </form>
					    </div>
					
					    <!-- Wishlist Button -->
					    <a href="${pageContext.request.contextPath}/add_to_wishlist?p_id=${product.id}">
					        <button class="fs__button custom-btn btn w-max mt-2 ms-2" type="button" style="width: 140px; height: 40px;">
					            Wishlist
					            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-heart-fill" viewBox="0 0 16 16">
					                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
					            </svg>
					        </button>
					    </a>
					</div>

                </div>
            </div>
            </a>
        </div>
    </c:forEach>
</div>

<!-- Footer -->
<div class="mt-3">
    <%@include file="/common/pagination.jsp"%>
</div>
<!-- End Footer -->

            </div>
        </div>
    </section>
    
    <!-- section__new.// -->

    <section class="section__quote my-5 p-5">
        <div class="container-fluid">
            <h3>Customer Reviews</h3>
            <div class="section__quote--box">
                <div class="quote">
                    <div class="quote--box">
                        <p>The flowers are beautiful and fragrant. Vibrant and diverse colors. A wide variety of flower types. The flower shop has many promotional programs that suit customers' budgets.</p>
                        <img src="/shop/images/face-1.png" alt="Face 1">
                        <p class="mt-3">Nguyen Thi A</p>
                    </div>
                </div>
                <div class="quote">
                    <div class="quote--box">
                        <p>The store has many types of flowers. Friendly staff. Many beautiful flowers, diverse origins, with clear sources. Affordable prices. Beautiful and lively atmosphere.</p>
                        <img src="/shop/images/face-2.png" alt="Face 2">
                        <p class="mt-3">Tran Thi B</p>
                    </div>
                </div>
                <div class="quote">
                    <div class="quote--box">
                        <p>Diverse and abundant flowers. Friendly staff. Prices suitable for all budgets. There are often promotions for holidays and festivals. Offers flower wrapping and packaging services.</p>
                        <img src="/shop/images/face-3.png" alt="Face 3">
                        <p class="mt-3">Le Thi C</p>
                    </div>
                </div>
            </div>
        </div>
    </section><!-- section__quote.// -->
    
    <section class="section__about section__feature my-5">
        <div class="container">
            <div class="product-top-bar section-border mb-2rem">
                <div class="section-title-wrap">
                    <h3 class="section-title section-bg-gray">About Us</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p><span class="color__main">Spring Bloom</span> is the leading flower store in India. The shop specializes in providing various types of fresh flowers, diverse cut flowers, with many colors, sourced from famous gardens
                        both locally and internationally. The flowers are arranged in various aesthetically pleasing forms, catering to the diverse needs of customers.</p>
                        <a href="{% url 'about-page"><button class="fs__button custom-btn btn w-max mt-2" type="button" style="width: 140px; height: 40px;">Learn More</button></a>
                </div>
                <div class="col-md-6">
                    <div class="about__video">
                        <img src="/shop/images/vv.gif" alt="Video" width="600" height="340">
                    </div>
                    
                </div>
            </div>
        </div>
    </section>
    <!-- section__about.// -->
</body>