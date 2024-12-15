<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Product Details</title>

<body>

<%@include file="/common/toast-message.jsp"%>

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
				        <div class="mt-3 mx-0">
				                <strong>Vận Chuyển Tới:</strong>
				                    <span class="text-primary "><i class="fas fa-shipping-fast mr-1"></i>${defaultAddress}</span>
				          
				        </div>
				        
				        <div class="mt-3 mx-0">
				                <strong>Phí Vận Chuyển:</strong>
				                    <span class="text-danger">${shippingFee}₫</span>
				          
				        </div>
				        
				        
                    <!-- Quantity Section -->
                    <div class="mt-3 row mx-0" >
                    	<p ><strong>Số lượng:</strong></p>
                    	<div class="btn-group mx-3" role="group" >
							<button type="button" class="btn btn-outline-dark update-quantity-btn" data-action="decrease" data-productline-id="${productLine.id}">-</button>
    						<input type="number" value="1" min="1" id="quantity-${product.id}" class="quantity-input text-center" style="width: 100px;">
							<button type="button" class="btn btn-outline-dark update-quantity-btn" data-action="increase" data-productline-id="${productLine.id}">+</button>
						</div>					        
                    </div>


                    <!-- Buttons -->
                    <div class="mt-4 row mx-0">
                    	<button class="fs__button custom-btn btn w-max ms-2 mr-1 add-to-wishlist" type="button" data-product-id="${product.id}">
					            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-heart-fill" viewBox="0 0 16 16">
					                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
					            </svg>
					            Wishlist
					        </button>
                            <button class="btn btn-outline-primary me-2 mr-1 add-to-cart" data-product-id="${product.id}" id="addToCartBtn" type="submit">
							    <i class="fa fa-shopping-cart"></i> Thêm Vào Giỏ Hàng
							</button>

                        <form action="${pageContext.request.contextPath}/customer/order" method="post">
                        	<input type="hidden" name="productId" value="${product.id}">
                        	<input type="hidden" name="quantity" id="quantityHidden" value="">
                            <button class="btn btn-warning" id="placeOrderBtn">Mua Ngay</button>
                        </form>
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

   <%--  <section class="section__feature mb-5">
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
                                    <button class="fs__button custom-btn btn w-max mt-2" id="addToCartBtn" type="button">Add to Cart</button>
                                </a>
                                <a href="${pageContext.request.contextPath}/add-to-wishlist/${relatedProduct.id}">
                                    <button class="fs__button custom-btn btn w-max mt-2" id="placeOrderBtn" type="button">Wishlist
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
    </section> --%>
    <!-- section__feature.// -->

<!-- Update quantity and hidden input quantity-->
<script>
document.addEventListener('DOMContentLoaded', function() {
    const quantityInputs = document.querySelectorAll('.quantity-input');

    quantityInputs.forEach(function(input) {
        // Cập nhật số lượng khi nhấn nút +
        const increaseButton = input.parentElement.querySelector('.update-quantity-btn[data-action="increase"]');
        const decreaseButton = input.parentElement.querySelector('.update-quantity-btn[data-action="decrease"]');
        
        increaseButton.addEventListener('click', function() {
            let quantity = parseInt(input.value);
            quantity = isNaN(quantity) ? 1 : quantity;
            input.value = quantity + 1;
            updateHiddenInput(input);  // Cập nhật vào hidden input ngay sau khi giá trị thay đổi
        });

        // Cập nhật số lượng khi nhấn nút -
        decreaseButton.addEventListener('click', function() {
            let quantity = parseInt(input.value);
            quantity = isNaN(quantity) ? 1 : quantity;
            if (quantity > 1) {
                input.value = quantity - 1;
            }
            updateHiddenInput(input);  // Cập nhật vào hidden input ngay sau khi giá trị thay đổi
        });

        // Cập nhật số lượng khi nhập số và nhấn Enter
        input.addEventListener('input', function() {  // Sử dụng sự kiện input thay vì change để cập nhật giá trị ngay lập tức
            let quantity = parseInt(input.value);
            quantity = isNaN(quantity) || quantity < 1 ? 1 : quantity; // Đảm bảo giá trị hợp lệ
            input.value = quantity;
            updateHiddenInput(input);  // Cập nhật vào hidden input ngay sau khi giá trị thay đổi
        });
    });

    // Hàm cập nhật giá trị vào input ẩn
    function updateHiddenInput(input) {
        const quantityHiddenInput = document.getElementById('quantityHidden'); 
        
        // Cập nhật giá trị vào trường ẩn
        quantityHiddenInput.value = input.value; 
    }
});


</script>


</body>
	