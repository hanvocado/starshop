<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container my-4">
    <!-- Tabs -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="#all" data-toggle="tab">Tất cả</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#waiting-payment" data-toggle="tab">Chờ thanh toán</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#shipping" data-toggle="tab">Vận chuyển</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#delivered" data-toggle="tab">Đang giao</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#completed" data-toggle="tab">Hoàn thành</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#cancelled" data-toggle="tab">Đã hủy</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#return" data-toggle="tab">Trả hàng/Hoàn tiền</a>
        </li>
    </ul>

    <!-- Tab Content -->
    <div class="tab-content mt-3">
        <!-- Waiting Payment Tab -->
        <div class="tab-pane fade show active" id="waiting-payment">
            <c:forEach items="${customer.orders}" var="order">
                <c:if test="${order.currentStatus == 'WAITING_PAYMENT'}">
                    <div class="card border-0 shadow-sm mb-3">
                        <div class="card-body">
                            <!-- Store and Chat -->
                            <div class="d-flex justify-content-between align-items-center border-bottom pb-2">
                                <div class="d-flex align-items-center">
                                    <span class="badge badge-danger mr-2">Mall</span>
                                    <strong>Amazfit_Official_Store</strong>
                                    <a href="#" class="ml-2 btn btn-link btn-sm">Xem Shop</a>
                                </div>
                                <span class="text-danger font-weight-bold">Chờ Thanh Toán</span>
                            </div>

                            <!-- Product -->
                            <div class="row py-3">
                                <div class="col-md-2">
                                    <img src="${pageContext.request.contextPath}/img/product-${order.id}.jpg" alt="Product Image" class="img-fluid rounded">
                                </div>
                                <div class="col-md-10">
                                    <div>
                                        <p class="mb-1 font-weight-bold">${order.lines[0].product.name}</p>
                                        <p class="text-muted mb-1">Phân loại hàng: ${order.lines[0].product.category}</p>
                                        <span>x${order.totalItems}</span>
                                    </div>
                                    <div class="mt-2">
                                        <span class="badge badge-warning">7 ngày trả hàng</span>
                                    </div>
                                </div>
                            </div>

                            <!-- Payment Info -->
                            <div class="border-top pt-2">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="text-muted">
                                        <p class="mb-1"><del>${order.productsTotal}</del></p>
                                        <p class="text-danger font-weight-bold">${order.finalTotal}</p>
                                    </div>
                                    <div class="text-right">
                                        <p class="text-muted mb-1">Số tiền phải trả:</p>
                                        <p class="text-danger font-weight-bold">${order.finalTotal}</p>
                                    </div>
                                </div>
                            </div>

                            <!-- Actions -->
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <button class="btn btn-secondary btn-sm" disabled>Chờ</button>
                                <div>
                                    <button class="btn btn-light btn-sm mr-2">Liên Hệ Người Bán</button>
                                    <button class="btn btn-danger btn-sm">Hủy Đơn Hàng</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <!-- Other Tabs Placeholder -->
        <div class="tab-pane fade" id="all">
            <p>Tất cả đơn hàng...</p>
        </div>
        <div class="tab-pane fade" id="shipping">
            <c:forEach items="${customer.orders}" var="order">
                <c:if test="${order.currentStatus == 'SHIPPING'}">
                    <div class="card border-0 shadow-sm mb-3">
                        <div class="card-body">
                            <!-- Similar structure as "waiting-payment", but for shipping status -->
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="tab-pane fade" id="delivered">
            <p>Đơn hàng đang giao...</p>
        </div>
        <div class="tab-pane fade" id="completed">
            <p>Đơn hàng đã hoàn thành...</p>
        </div>
        <div class="tab-pane fade" id="cancelled">
            <p>Đơn hàng đã hủy...</p>
        </div>
        <div class="tab-pane fade" id="return">
            <p>Đơn hàng trả hàng/hoàn tiền...</p>
        </div>
    </div>
</div>
