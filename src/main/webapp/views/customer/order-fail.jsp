<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<div class="body py-5">
    <div class="container">
        <div class="w-50 m-auto">
            <!-- Thêm lớp "my-4" để tạo ra khoảng cách trên và dưới tiêu đề trang -->
            <h1 class="my-3 text-danger text-center">Thanh toán thất bại</h1>
            <!-- Sử dụng lớp "alert" và "alert-danger" để tạo ra thông báo lỗi -->
            <h2 class="my-2">Chi tiết đơn hàng</h2>
            <!-- Sử dụng lớp "table" và "table-bordered" để tạo ra bảng hiển thị thông tin chi tiết đơn hàng -->
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <td>Thông tin đơn hàng:</td>
                        <td><c:out value="${orderId}" /></td>
                    </tr>
                    <tr>
                        <td>Tổng tiền:</td>
                        <td><c:out value="${totalPrice}" /></td>
                    </tr>
                    <tr>
                        <td>Thời gian thanh toán:</td>
                        <td><c:out value="${paymentTime}" /></td>
                    </tr>
                    <tr>
                        <td>Mã giao dịch:</td>
                        <td><c:out value="${transactionId}" /></td>
                    </tr>
                </tbody>
            </table>
            <a href="/" class="btn btn-primary">Về trang chủ</a>
        </div>
    </div>
</div>
<!-- end body -->
