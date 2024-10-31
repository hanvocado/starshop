<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

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
            <div class="card product-card">
                <img src="${product.image}" class="card-img-top product-image" alt="${product.name}">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    
                    <!-- Thay thế URL với các liên kết JSP phù hợp -->
                    <a href="${pageContext.request.contextPath}/add_to_cart?p_id=${product.id}">
                        <button class="fs__button custom-btn btn w-max mt-2" type="button" style="width: 140px; height: 40px;">Add to Cart</button>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/add_to_wishlist?p_id=${product.id}">
                        <button class="fs__button custom-btn btn w-max mt-2" type="button" style="width: 140px; height: 40px;">Wishlist
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-heart-fill" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
                            </svg>
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Footer -->
<div class="mt-3">
    <!-- Pagination -->
    <div class="row justify-content-center justify-content-sm-between align-items-sm-center">
        <div class="col-sm mb-2 mb-sm-0">
            <div class="d-flex justify-content-center justify-content-sm-start align-items-center">
                <span class="mr-2">Page size: </span>
                <!-- Select -->
                <form id="pageSizeForm" action="<c:url value='/${role}'/>" method="get">
                    <select name="pageSize" onchange="document.getElementById('pageSizeForm').submit();"
                        id="datatableEntries" class="js-select2-custom" data-hs-select2-options='{
		                            "minimumResultsForSearch": "Infinity",
		                            "customClass": "custom-select custom-select-sm custom-select-borderless",
		                            "dropdownAutoWidth": true,
		                            "width": true
		                          }'>
                        <option value="2" <c:if test="${pageSize == 2}">selected</c:if>>2</option>
                        <option value="6" <c:if test="${pageSize == 6}">selected</c:if>>6</option>
                        <option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
                    </select>
                </form>

                <!-- End Select -->
            </div>
        </div>

        <div class="col-sm-auto">
            <div class="d-flex justify-content-center justify-content-sm-end">
                <!-- footer Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${isFirst }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isFirst }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/?pageNo=${pageNo-1}' />" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                            <span class="sr-only">Previous</span>
                        </a>
                        </li>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item <c:if test=" ${pageNo==i-1}">active</c:if>">
                                <a class="page-link" href="<c:url value='/?pageNo=${i-1}' />">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${isLast }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isLast }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/?pageNo=${pageNo+1}' />" aria-label="Next">
                            <span aria-hidden="true">»</span>
                            <span class="sr-only">Next</span>
                        </a>
                        </li>
                    </ul>
                </nav>
                <!-- End footer Pagination -->
            </div>
        </div>
    </div>
    <!-- End Pagination -->
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