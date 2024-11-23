<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
    
<title>Carts</title>

<body>

<nav class="navbar navbar-main navbar-expand-lg border__bottom">
    <div class="container my-3 mx-0 d-flex justify-content-between" style="max-width: 100%;">
        <c:choose>
            <c:when test="${empty productLines}">
                <div class="d-flex justify-content-center align-items-center text-center" style="height: 50vh; width: 100%;">
                    <p class="text-muted fs-5">
                        Không có sản phẩm trong giỏ hàng
                    </p>
                </div>
            </c:when>

            <c:otherwise>
                <div class="container-fluid my-3">
                    <div class="w-100">
                    <h3 class="mb-4">Shopping Cart</h3>
                        <p>${productLines.size()} items</p>

                        <form action="${pageContext.request.contextPath}/user/cart/update" method="POST">
                            <div class="table-responsive">
                                <table class="table align-middle">
                                    <thead class="bg-light">
                                        <tr>
                                            <th class="text-center col-1">
                                                <input type="checkbox" id="selectAll" onclick="toggleAll(this)"> <!-- Select All Checkbox -->
                                            </th>
                                            <th class="col-3">Sản Phẩm</th>
                                            <th class="col-2">Loại</th>
                                            <th class="col-2">Đơn Giá</th>
                                            <th class="col-2">Số Lượng</th>
                                            <th class="col-1">Số Tiền</th>
                                            <th class="col-1"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:set var="totalCartPrice" value="0" />
                                        <c:forEach var="productLine" items="${productLines}" varStatus="loop">
                                        	<c:set var="productLinePrice" value="${productLine.quantity * productLine.product.getDisplayPrice()}" />
    										<c:set var="totalCartPrice" value="${totalCartPrice + productLinePrice}" />
                                            <tr>
                                                <!-- Checkbox -->
                                                <td class="text-center">
                                                    <input type="checkbox" name="selectedItems" value="${productLine.id}" class="selectItem">
                                                </td>
                                                <!-- Product Image and Name -->
                                                <td>
                                                    <div class="d-flex align-items-center">
                                                        <c:set var="imageUrl">
													    <c:choose>
													        <c:when test="${productLine.product.image.startsWith('https')}">
													            ${productLine.product.image}
													        </c:when>
													        <c:otherwise>
													            /img/${productLine.product.image}
													        </c:otherwise>
													    </c:choose>
													</c:set>
													<img src="${imageUrl}" 
													     alt="${productLine.product.name}" 
													     class="img-fluid mr-2" 
													     style="width: 80px; height: 80px;">

                                                        <span>${productLine.product.name}</span>
                                                    </div>
                                                </td>
                                                <!-- Product Description -->
                                                <td> 
                                                	<div class="d-flex align-items-center">${productLine.product.getCategoryNames()}</div>
                                                </td>
                                                <!-- Product Price -->
                                                <td>
                                                    <div class="row">
                                                        <span class="text-muted text-decoration-line-through mr-2">đ${productLine.product.salePrice}</span><br>
                                                        <span class="fw-bold">đ${productLine.product.getDisplayPrice()}</span>
                                                    </div>
                                                </td>
                                                <!-- Quantity -->
                                                <td>
												    <div class="input-group">
												        <button class="btn btn-outline-secondary update-quantity-btn" data-action="decrease" data-productline-id="${productLine.id}" type="button">-</button>
												        <input type="number" name="quantity-${productLine.id}" value="${productLine.quantity}" min="1" id="quantity-${productLine.id}" data-productline-id="${productLine.id}" class="quantity-input form-control text-center">
												        <button class="btn btn-outline-secondary update-quantity-btn" data-action="increase" data-productline-id="${productLine.id}" type="button">+</button>
												    </div>
												</td>
                                                <!-- Total Price -->
                                                <td class="text-danger fw-bold product-line-total" data-productline-id="${productLine.id}">đ${productLinePrice}</td>
                                                <!-- Remove -->
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/user/cart/remove/${productLine.id}" class="btn btn-link text-danger">Xóa</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>

                        <!-- Voucher and Total -->
                        <div class="mt-4">
                            <div class="d-flex justify-content-between align-items-center">
                                <!-- Voucher Section -->
                                <div>
                                    <button class="btn btn-link text-decoration-none" data-bs-toggle="modal" data-bs-target="#voucherModal">
                                        <i class="bi bi-ticket-perforated"></i> Chọn hoặc nhập mã
                                    </button>
                                </div>
                            </div>

                            <!-- Total Payment Section -->
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <div></div>
                                <div class="text-end d-flex align-items-center">
                                    <p class="mb-0 me-4 fs-4 fw-bold">Tổng thanh toán: <span class="text-danger" id="total-price">đ${totalCartPrice}</span></p>
                                    <button class="btn btn-primary btn-lg">Mua Hàng</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Voucher Modal -->
    <div class="modal fade" id="voucherModal" tabindex="-1" aria-labelledby="voucherModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="voucherModalLabel">Chọn Voucher</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="voucherCode" class="form-label">Mã Voucher</label>
                            <input type="text" id="voucherCode" class="form-control" placeholder="Nhập mã voucher">
                        </div>
                        <button type="button" class="btn btn-primary">Áp Dụng</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</nav>

<script>
    function toggleAll(source) {
        const checkboxes = document.querySelectorAll('.selectItem');
        checkboxes.forEach(cb => cb.checked = source.checked);
    }
</script>


<!-- Form thanh toán, chỉ hiển thị nếu có sản phẩm trong giỏ -->
<c:if test="${not empty productLines}">
    <form id="payment-form" class="container" method="POST" action="${pageContext.request.contextPath}/initiate-payment">
        <textarea name="address" id="address" cols="30" rows="3" class="form-control" placeholder="Enter Delivery Address"></textarea>
        <input type="hidden" id="amount" name="amount" value="${totalPrice}">
        <button type="submit" id="pay-button" class="btn custom-btn">Pay Now</button>
    </form>
</c:if>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // Xử lý sự kiện click vào nút "+" hoặc "-"
    $(document).on('click', '.update-quantity-btn', function() {
        var action = $(this).data('action');
        var productLineId = $(this).data('productline-id');
        updateQuantity(action, productLineId);
    });

    // Xử lý sự kiện nhấn phím "Enter" trong ô input
    $(document).on('keyup', '.quantity-input', function(event) {
        if (event.which === 13 || event.keyCode === 13) {
            event.preventDefault(); 
            var productLineId = $(this).data('productline-id');
            var action = 'set';
            updateQuantity(action, productLineId);
        }
    });

    function updateQuantity(action, productLineId) {
        var quantity = $("#quantity-" + productLineId).val();  

        $.ajax({
            url: '/user/cart/update-quantity',
            type: 'POST',
            data: {
                action: action,
                productLineId: productLineId,
                quantity: quantity
            },
            success: function(response) {
                $('input[name="quantity-' + productLineId + '"]').val(response.newQuantity);
                $('.product-line-total[data-productline-id="' + productLineId + '"]').text('đ' + response.plTotalPrice);
                $('#total-price').text('đ' + response.totalPrice);
            },
            error: function(xhr, status, error) {
                console.error('Error updating quantity:', error);
            }
        });
    }
</script>

    <script>
        $(document).ready(function() {
            $("#pay-button").click(function(e) {
                e.preventDefault();

                const amount = $("#amount").val();
                const address = $("#address").val();

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/initiate-payment",
                    data: { amount: amount, address: address },
                    dataType: "json",
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("X-CSRFToken", $("meta[name='_csrf']").attr("content"));
                    },
                    success: function(data) {
                        const options = {
                            key: data.key,
                            amount: data.amount,
                            currency: data.currency,
                            order_id: data.id,
                            name: data.name,
                            description: data.description,
                            image: data.image,
                            handler: function(response) {
                                if (response.razorpay_payment_id) {
                                    window.location.href = "${pageContext.request.contextPath}/payment-success";
                                } else {
                                    window.location.href = "${pageContext.request.contextPath}/payment-failed";
                                }
                            },
                            prefill: {
                                name: "Card Holder Name",
                                email: "Customer@example",
                                contact: "Customer_Contact",
                            },
                        };

                        const rzp = new Razorpay(options);
                        rzp.open();
                    },
                    error: function(error) {
                        console.error("Error initiating payment:", error);
                    }
                });
            });
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <style>
        .custom-border {
            border: 2px solid #dd3577 !important;
            box-sizing: border-box;
        }
        .custom-btn {
            background-color: #dd3577;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .custom-btn:hover {
            background-color: #ff66a3;
        }
    </style>
</body>
