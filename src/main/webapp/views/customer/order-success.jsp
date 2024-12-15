<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<div class="body py-5">
    <div class="container">
        <div class="w-75 m-auto">
            <!-- Card chứa nội dung -->
            <div class="card shadow-lg">
                <div class="card-header bg-success text-white text-center">
                    <h2 class="mb-0">Thanh toán thành công</h2>
                </div>
                <div class="card-body">
                    <!-- Biểu tượng thành công -->
                    <div class="text-center my-3">
                        <i class="fas fa-check-circle text-success" style="font-size: 4rem;"></i>
                        <p class="mt-2 text-muted">Cảm ơn bạn đã mua sắm tại cửa hàng!</p>
                    </div>
                    
                    <!-- Chi tiết đơn hàng -->
                    <h4 class="my-3">Chi tiết đơn hàng</h4>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td><strong>Thông tin đơn hàng:</strong></td>
                                <td><c:out value="${orderId}" /></td>
                            </tr>
                            <tr>
                                <td><strong>Tổng tiền:</strong></td>
                                <td class="text-danger"><c:out value="${totalPrice}" /> ₫</td>
                            </tr>
                            <tr>
                                <td><strong>Thời gian thanh toán:</strong></td>
                                <td><c:out value="${paymentTime}" /></td>
                            </tr>
                            <tr>
                                <td><strong>Mã giao dịch:</strong></td>
                                <td><c:out value="${transactionId}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer text-center">
                    <!-- Nút điều hướng -->
                    <a href="/customer/products" class="btn btn-primary">
                        <i class="fas fa-home"></i> Về trang chủ
                    </a>
                    <a href="/customer/orders" class="btn btn-secondary">
                        <i class="fas fa-list"></i> Xem đơn hàng của tôi
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
