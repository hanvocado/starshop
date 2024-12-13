<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Product Details</title>

<body>
    <section class="section__content section__product">
    <div class="container">
        <!-- Product details -->
        <div class="row">
            <!-- Product Image -->
            <div class="col-md-6">
                <c:if test="${product.image.substring(0,5)=='https'}">
                    <c:url value="${product.image}" var="imgUrl"></c:url>
                </c:if>
                <c:if test="${product.image.substring(0,5)!='https'}">
                    <c:url value="/img/${product.image}" var="imgUrl"></c:url>
                </c:if>
                <img src="${imgUrl}" class="img-fluid rounded" alt="${product.name}" style="max-height: 450px; width: 100%;">
            </div>

            <!-- Product Details -->
            <div class="col-md-6">
                <div class="product-details">
                    <!-- Product Title -->
                    <h2 class="product-title mb-3">${product.name}</h2>

                    <!-- Pricing Section -->
                    <div class="mb-2">
                        <span class="text-muted" style="text-decoration: line-through;">${product.salePrice} VND</span>
                        <span class="text-danger h4">${product.getDisplayPrice()} VND</span>
                        <span class="badge bg-danger ms-2">${product.discountPercent}% GIẢM</span>
                    </div>

                    <!-- Product Description -->
                    <p class="mt-3"><strong>Mô tả:</strong></p>
                    <p class="text-justify">${product.description}</p>

                    <!-- Additional Information -->
                    <p><strong>Trọng lượng:</strong> ${product.weight} kg</p>

                    <!-- Delivery Section -->
                    <p class="mt-3"><strong>Vận chuyển:</strong> Miễn phí giao hàng khu vực nội thành TP.HCM & Hà Nội</p>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="city">Chọn thành phố:</label>
                            <select id="city" class="form-control">
                                <option value="Hà Nội">Hà Nội</option>
                                <option value="TP.HCM">TP.HCM</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="district">Chọn quận:</label>
                            <select id="district" class="form-control">
                                <option value="Ba Đình">Ba Đình</option>
                                <option value="Cầu Giấy">Cầu Giấy</option>
                            </select>
                        </div>
                    </div>

                    <!-- Quantity Section -->
                    <div class="mt-3 row mx-0" >
                    	<p ><strong>Số lượng:</strong></p>
                    	<div class="btn-group mx-3" role="group" >
							<button type="button" class="btn btn-outline-dark update-quantity-btn" data-action="decrease" data-productline-id="${productLine.id}">-</button>
							<input type="number" name="quantity-${productLine.id}" value="${productLine.quantity}" min="1" id="quantity-${productLine.id}" data-productline-id="${productLine.id}" style="width: 100px;" class="quantity-input text-center">
							<button type="button" class="btn btn-outline-dark update-quantity-btn" data-action="increase" data-productline-id="${productLine.id}">+</button>
						</div>					        
                    </div>


                    <!-- Buttons -->
                    <div class="mt-4">
                    	<button class="fs__button custom-btn btn w-max ms-2" type="button" >
					            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-heart-fill" viewBox="0 0 16 16">
					                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
					            </svg>
					            Wishlist
					        </button>
                        <a href="${pageContext.request.contextPath}/add-to-cart/${product.id}">
                            <button class="btn btn-outline-primary me-2">
							    <i class="fa fa-shopping-cart"></i> Thêm Vào Giỏ Hàng
							</button>

                        </a>
                        <a href="${pageContext.request.contextPath}/add-to-wishlist/${product.id}">
                            <button class="btn btn-warning">Mua Ngay</button>
                        </a>
                    </div>

                    <!-- Additional Notes -->
                    <div class="mt-3 text-muted">
                        <p><i class="fas fa-shipping-fast"></i> Giao hoa NHANH trong 60 phút</p>
                        <p><i class="fas fa-gift"></i> Tặng miễn phí thiệp hoặc banner</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

    <!-- section__review .// -->

    <section class="section__feature mb-5">
        <div class="container">
            <div class="product-top-bar section-border mb-2rem">
                <div class="section-title-wrap">
                    <h3 class="section-title section-bg-gray">Related Products</h3>
                </div>
            </div>

            <div class="row">
                <c:forEach var="relatedProduct" items="${relatedProducts}">
                    <div class="col-md-3">
                        <div class="card product-card">
                            <img src="${relatedProduct.imageUrl}" class="card-img-top product-image" alt="${relatedProduct.name}">
                            <div class="card-body">
                                <h5 class="card-title">${relatedProduct.name}</h5>
                                <a href="${pageContext.request.contextPath}/add-to-cart/${relatedProduct.id}">
                                    <button class="fs__button custom-btn btn w-max mt-2" type="button">Add to Cart</button>
                                </a>
                                <a href="${pageContext.request.contextPath}/add-to-wishlist/${relatedProduct.id}">
                                    <button class="fs__button custom-btn btn w-max mt-2" type="button">Wishlist
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
        </div>
    </section>
    <!-- section__feature.// -->


</body>
	