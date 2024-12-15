<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<div class="container my-4">
	<!-- Header Section -->
	<div class="row">
		<div class="col-12 text-center">
			<h1>Đặt Hàng</h1>
		</div>
	</div>

	<!-- Address Section -->
	<div class="row mt-4">
		<div class="col-md-6">
			<div class="bg-light p-3 border rounded">
				<h5 class="text-danger">
					<i class="fas fa-map-marker-alt"></i> Địa Chỉ Nhận Hàng
				</h5>
				<p>
					<strong>${order.address.houseNumber}
						${order.address.street}</strong><br> ${order.address.ward},
					${order.address.district}, ${order.address.city}<br> Phone:
					${order.customer.phoneNumber}
				</p>
				<div>
					<span class="badge badge-danger">Mặc Định</span> <a href="#"
						class="text-primary ml-2">Thay Đổi</a>
				</div>
			</div>
		</div>

		<!-- User Info Section -->
		<div class="col-md-6">
			<div class="bg-white p-3 border rounded">
				<h5>Thông Tin Khách Hàng</h5>
				<p>
					<strong>${order.customer.fullName}</strong>
				</p>
				<p>Email: ${order.customer.email}</p>
				<p>Phone: ${order.customer.phoneNumber}</p>
			</div>
		</div>
	</div>

	<!-- Product Section -->
	<div class="mt-4 bg-white p-3 border rounded">
		<h5>Sản phẩm</h5>
		<hr class="mt-1 mb-2">
		<div class="table-responsive">
			<table class="table align-middle">
				<thead class="bg-light">
					<tr>
					    <th class="col-3"></th>
					    <th class="col-3 ">Loại</th>
					    <th class="col-2 text-center">Đơn Giá</th>
					    <th class="col-2 text-center">Số Lượng</th>
					    <th class="col-2 text-center">Số Tiền</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="productLine" items="${order.selectedProductLines}">
						<c:set var="productLinePrice"
							value="${productLine.quantity * productLine.product.getDisplayPrice()}" />
						<tr>
							<!-- Product Image and Name -->
							<td>
								<div class="d-flex align-items-center">
									<c:set var="imageUrl">
										<c:choose>
											<c:when
												test="${productLine.product.image.startsWith('https')}">
													            ${productLine.product.image}
													        </c:when>
											<c:otherwise>
													            /img/${productLine.product.image}
													        </c:otherwise>
										</c:choose>
									</c:set>
									<img src="${imageUrl}" alt="${productLine.product.name}"
										class="img-fluid mr-2" style="width: 80px; height: 80px;">

									<span>${productLine.product.name}</span>
								</div>
							</td>
							<!-- Product Description -->
							<td>
								<div class="d-flex align-items-center">${productLine.product.getCategoryNames()}</div>
							</td>
							<!-- Product Price -->
							<td>
								<div class="row d-flex align-items-center justify-content-center">
									<span class="text-muted text-decoration-line-through mr-3">đ${productLine.product.salePrice}</span><br>
									<span class="fw-bold">đ${productLine.product.getDisplayPrice()}</span>
								</div>
							</td>
							<!-- Quantity -->
							<td>
								<div class="d-flex align-items-center justify-content-center">${productLine.quantity}</div>
							</td>
							<!-- Total Price -->
							<td class="text-danger fw-bold product-line-total d-flex align-items-center justify-content-center"
								data-productline-id="${productLine.id}">đ${productLinePrice}</td>							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="row mt-4">
		<div class="col-md-6">
			<!-- Voucher Section -->
			<div class="mb-4 bg-white p-3 border rounded">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<div>
						<i class="fas fa-ticket-alt text-danger"></i> Shop Voucher
					</div>
					<a type="button" class="text-primary" id="voucherButton"
						onclick="checkAndToggleVoucherInputs()">Chọn Voucher</a>
				</div>
			</div>
			<!-- Voucher Modal -->
			<%@include file="/views/customer/vouchers.jsp"%>
			
			<!-- Payment Method Section -->
			<div class="bg-white p-3 border rounded">
				<div class="d-flex justify-content-between">
					<h5>Phương thức thanh toán</h5>
					<a href="#" class="text-primary">Thay Đổi</a>
				</div>
				<p class="text-muted">Thanh toán khi nhận hàng</p>
			</div>
		</div>

		<!-- Total Section -->
		<div class="col-md-6" style="position: relative; z-index: -1;">
			<div class="bg-white p-3 border rounded">
				<table class="table table-borderless">
					<tbody>
						<tr>
							<td>Tổng tiền hàng</td>
							<td class="text-right">${order.getSubtotal()}₫</td>
						</tr>
						<tr>
							<td>Phiếu giảm giá của cửa hàng</td>
							<td class="text-right">-${order.getDiscount()}₫</td>
						</tr>
						<tr>
							<td>Phiếu giảm giá vận chuyển</td>
							<td class="text-right">-${order.getFreeShip()}₫</td>
						</tr>
						<tr>
							<th>Tổng thanh toán</th>
							<th class="text-right text-danger" id="finalPrice">${order.getFinalPrice()}₫</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Place Order Section -->
	<div class="mt-4 d-flex justify-content-end">

		<form action="/customer/order/submit" method="POST">
			<button type="submit" class="btn btn-danger">Đặt
				Hàng</button>
		</form>
	</div>
</div>

<script>
	function toggleModal() {
	    const modal = document.getElementById('voucherModal');
	    modal.classList.toggle('hidden');
	}

    // Hàm kiểm tra và hiển thị thông báo nếu không có sản phẩm nào được chọn
    function checkAndToggleVoucherInputs() {
		
       minPriceCondition();
        toggleModal();
    }
</script>


	<script>
	const vouchers = [
	    // Discount Vouchers
	    <c:forEach items="${discountVouchers}" var="voucher" varStatus="status">
	        {
	            code: "${voucher.code}",
	            description: "${voucher.description}",
	            discountPercent: ${voucher.discountPercent}, 
	            minItemsTotal: ${voucher.minOrderItemsTotal}, 
	            expiredAt: "${voucher.getFormattedExpiredAt()}"
	        }<c:if test="${!status.last || not empty freeShipVouchers}">,</c:if>
	    </c:forEach>
	    // Free Ship Vouchers
	    <c:forEach items="${freeShipVouchers}" var="voucher" varStatus="status">
	        {
	            code: "${voucher.code}",
	            description: "${voucher.description}",
	            discountPercent: ${voucher.discountPercent}, 
	            minItemsTotal: ${voucher.minOrderItemsTotal}, 
	            expiredAt: "${voucher.getFormattedExpiredAt()}"
	        }<c:if test="${!status.last}">,</c:if>
	    </c:forEach>
	];


	function minPriceCondition() {
	    // Lấy giá trị totalPrice từ input
	   // Lấy giá trị từ nội dung text của phần tử
		const finalPrice = parseFloat(document.getElementById('finalPrice').textContent.replace(/[^\d.-]/g, ''));
		document.getElementById('totalPriceNotVoucher').value = finalPrice;
	    
	    // Lặp qua từng voucher để xử lý thông báo
	    for (let i = 0; i < vouchers.length; i++) {
	        const voucher = vouchers[i];

	        const radioButton = document.querySelector(
	            'input[name="voucherCode"][value="' + voucher.code + '"]'
	        );

	        // Tạo hoặc lấy messageElement gắn trực tiếp vào radioButton.closest('.voucher-content')
	        let messageElement = radioButton.closest('.flex').querySelector('.color__main');
	        if (!messageElement) {
	            messageElement = document.createElement('p');
	            messageElement.classList.add('color__main', 'text-sm'); // Thêm style hiển thị lỗi
	            radioButton.closest('.flex').appendChild(messageElement);
	        }

	        // Ẩn thông báo trước khi kiểm tra
	        messageElement.style.display = 'none';

	        // Kiểm tra điều kiện minItemsTotal và trạng thái đã sử dụng
	        if (finalPrice < voucher.minItemsTotal) {
	        	messageElement.innerText = 'Giá trị đơn hàng không đủ điều kiện áp dụng';
	            radioButton.disabled = true; // Vô hiệu hóa radio button
	            messageElement.style.display = 'block'; // Hiển thị thông báo
	        } else {
	            // Nếu đủ điều kiện
	            radioButton.disabled = false; // Kích hoạt lại radio button
	            messageElement.style.display = 'none'; // Ẩn thông báo
	        }
	        
	    }
	    
	}

</script>

