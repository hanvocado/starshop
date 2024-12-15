<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Profile</title>

<section>
	<figure class="d-flex align-items-center">
		<div class="icon me-3">
			<c:choose>
				<c:when test="${not empty customer.profileImg}">
					<img src="${customer.profileImg}" alt="Avatar"
						class="rounded-circle img-sm border">
				</c:when>
				<c:otherwise>
					<img src="${basePath}/shop/images/default_avatar.jpg"
						alt="Default Avatar" class="rounded-circle img-sm border">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="text ml-2 align-item-center">
			<strong>${customer.lastName} ${customer.firstName}</strong><br>
			<p class="mb-2">${customer.email}</p>
			<a
				href="${pageContext.request.contextPath}/customer/account/edit/${customer.id}"
				class="btn btn-light btn-sm">Edit Information</a>
		</div>
	</figure>

	<hr>
	<p>
		<i class="fa fa-map-marker text-muted"></i>&nbsp; 
				Địa chỉ: ${defaultAddress.getAddress()}&nbsp; <a
			href="${pageContext.request.contextPath}/customer/account/address/edit/${customer.id}">Chỉnh sửa</a>
	</p>
	<article class="card-group card-stat">
		<figure class="card bg-light">
			<div class="p-3 text-center">
				<h4 class="title">${customer.orders.size()}</h4>
				<span>Tổng đơn hàng</span>
			</div>
		</figure>
		<figure class="card bg-light">
			<div class="p-3 text-center">
				<h4 class="title">${customer.getWishlist().size()}</h4>
				<span>Sản phẩm yêu thích</span>
			</div>
		</figure>
		<figure class="card bg-light">
			<div class="p-3 text-center">
				<h4 class="title">abc</h4>
				<span>Đang giao</span>
			</div>
		</figure>
		<figure class="card bg-light">
			<div class="p-3 text-center">
				<h4 class="title">abc</h4>
				<span>Đã nhận</span>
			</div>
		</figure>
	</article>
	<hr>
	<article class="card mb-3">
    <div class="card-body">
        <h5 class="card-title mb-4">Recent Orders</h5>
        <div class="row">
            <c:forEach items="${customer.orders}" var="order" varStatus="status">
                <div class="col-md-6">
                    <figure class="itemside mb-3">
                        <div class="aside">
                            <img src="${pageContext.request.contextPath}/img/product-${status.index + 1}.jpg" class="border img-sm" alt="Product Image">
                        </div>
                        <figcaption class="info">
                            <time class="text-muted">
                                <i class="fa fa-calendar-alt"></i>
                                ${order.formattedOrderDate}
                            </time>
                            <p>${order.totalItems} sản phẩm</p>
                            <span class="text-success">
                                <!-- Hiển thị trạng thái đơn hàng -->
                                ${order.currentStatus != null ? order.currentStatus.name() : 'Chưa xác định'}
                            </span>
                        </figcaption>
                    </figure>
                </div>
            </c:forEach>
        </div>
        <a href="${pageContext.request.contextPath}/customer/orders" class="btn btn-block btn-outline-primary">View All Orders <i class="fa fa-chevron-down"></i></a>
    </div>
</article>

</section>
