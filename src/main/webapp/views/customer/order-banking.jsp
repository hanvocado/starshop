<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
    
<div class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Chi Tiết Đơn Hàng</h3>
                </div>
                <div class="card-body">
                    <h5 class="text-muted mb-4">Thông tin đơn hàng</h5>
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td><strong>Mã đơn hàng:</strong></td>
                                <td>${order.id}</td>
                            </tr>
                            <tr>
                                <td><strong>Khách hàng:</strong></td>
                                <td>${order.user.fullName}</td>
                            </tr>
                            <tr>
                                <td><strong>Ngày đặt hàng:</strong></td>
                                <td>${order.getFormattedOrderDate()}</td>
                            </tr>
                            <tr>
                                <td><strong>Trạng thái hiện tại:</strong></td>
                                <td>${order.currentStatus.getDescription()}</td>
                            </tr>
                            <tr>
                                <td><strong>Phương thức thanh toán:</strong></td>
                                <td>${order.payMethod}</td>
                            </tr>
                            <tr>
                                <td><strong>Ghi chú:</strong></td>
                                <td>${order.note != null ? order.note : "Không có"}</td>
                            </tr>
                            <tr>
                                <td><strong>Tổng sản phẩm:</strong></td>
                                <td>${order.productsTotal} ₫</td>
                            </tr>
                            <tr>
                                <td><strong>Phí vận chuyển:</strong></td>
                                <td>${order.shippingFee} ₫</td>
                            </tr>
                            <tr>
                                <td><strong>Tổng thanh toán:</strong></td>
                                <td class="text-danger font-weight-bold">${order.finalTotal} ₫</td>
                            </tr>
                            <tr>
                                <td><strong>Voucher:</strong></td>
                                <td>${order.voucher != null ? order.voucher.code : "Không áp dụng"}</td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- Danh sách sản phẩm -->
                    <h5 class="text-muted mt-4 mb-3">Danh sách sản phẩm</h5>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Hình ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Phân loại</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="line" items="${order.lines}">
                                    <tr>
                                        <td>
                                            <img src="${pageContext.request.contextPath}/img/product-${line.product.id}.jpg"
                                                 alt="${line.product.name}" style="width: 80px; height: 80px;">
                                        </td>
                                        <td>${line.product.name}</td>
                                        <td>${line.product.getCategoryNames()}</td>
                                        <td>${line.quantity}</td>
                                        <td>${line.subTotal} ₫</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Theo dõi đơn hàng -->
                    <h5 class="text-muted mt-4 mb-3">Lịch sử đơn hàng</h5>
                    <div class="timeline">
                        <c:forEach var="tracking" items="${order.tracking}">
                            <div class="timeline-item mb-3">
                                <div class="timeline-marker bg-primary"></div>
                                <div class="timeline-content">
                                    <p><strong>${tracking.status.getDescription()}</strong></p>
                                    <p>${tracking.getFormattedTime()}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    