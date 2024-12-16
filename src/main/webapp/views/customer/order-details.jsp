<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Chi tiết đơn hàng</title>

 
<body>
    <!-- Page Header -->
    <div class="container my-4">
        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm-12">
                    <h1 class="page-header-title">Order #${order.id}</h1>
                    <div class="d-flex align-items-center mt-2">
                        <c:choose>
                            <c:when test="${order.isPayed()}">
                                <span class="badge badge-success mr-2">Paid</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-warning mr-2">Not Paid</span>
                            </c:otherwise>
                        </c:choose>
                        <span class="badge badge-info">${order.currentStatus}</span>
                        <span class="ml-3">
                            <i class="fas fa-calendar-alt"></i> ${order.formattedOrderDate}
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Order Details -->
        <div class="row mt-4">
            <!-- Order Items -->
            <div class="col-lg-8 mb-4">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Chi tiết đơn hàng</h4>
                    </div>
                    <div class="card-body">
                        <c:forEach var="line" items="${order.lines}">
                            <div class="media mb-3">
                                <c:if test="${line.product.image.substring(0,5)=='https'}">
							<c:url value="${product.image }" var="imgUrl"></c:url>
						  </c:if>
						  <c:if test="${line.product.image.substring(0,5)!='https'}">
							<c:url value="/img/${line.product.image }" var="imgUrl"></c:url>
						  </c:if>
	                    <img class="img-fluid mr-2 rounded" src="${imgUrl }" alt="Image Description" style="width: 80px; height: 80px;">
                                <div class="media-body">
                                    <h5 class="mt-0">${line.product.name}</h5>
                                    <p class="mb-0 text-muted">Phân loại hàng: ${line.product.getCategoryNames()}</p>
                                    <p class="mb-0">Số lượng: <strong>${line.quantity}</strong></p>
                                    <p class="mb-0 text-danger">Tổng: đ${line.subTotal}</p>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                        <!-- Order Summary -->
                        <div class="row justify-content-end">
                            <div class="col-md-8">
                                <table class="table table-sm">
                                    <tbody>
                                        <tr>
                                            <td>Tổng sản phẩm:</td>
                                            <td class="text-right">đ${order.productsTotal}</td>
                                        </tr>
                                        <tr>
                                            <td>Phí vận chuyển:</td>
                                            <td class="text-right">đ${order.shippingFee}</td>
                                        </tr>
                                        <c:if test="${order.discountTotal > 0}">
                                            <tr>
                                                <td>Voucher:</td>
                                                <td class="text-right text-danger">-đ${order.discountTotal}</td>
                                            </tr>
                                        </c:if>
                                        <tr class="font-weight-bold">
                                            <td>Tổng cộng:</td>
                                            <td class="text-right text-danger">đ${order.finalTotal}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Order Tracking -->
            <div class="col-lg-4">
                <div class="card">
                    <div class="card-header bg-secondary text-white">
                        <h4 class="mb-0">Theo dõi đơn hàng</h4>
                    </div>
                    <div class="card-body">
                        <ul class="list-group">
                            <c:forEach items="${order.tracking}" var="track">
                                <li class="list-group-item">
                                    <div class="d-flex justify-content-between">
                                        <span>${track.status.getDescription()}</span>
                                        <span class="text-muted small">${track.formattedTime}</span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <small class="d-block text-muted mt-3 text-center">Times are shown in the local time zone.</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
 