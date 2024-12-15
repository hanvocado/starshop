<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<div class="container my-4">

    <!-- Tabs Section -->
    <ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link ${currentStatus == 'ALL' ? 'active' : ''}" 
           href="?status=ALL&page=0&size=${orderPage.size}">
            Tất cả (${orderCounts['ALL']})
        </a>
    </li>
    <c:forEach var="status" items="${orderStatuses}">
        <li class="nav-item">
            <a class="nav-link ${currentStatus == status.name() ? 'active' : ''}" 
               href="?status=${status.name()}&page=0&size=${orderPage.size}">
                ${status.getDescription()} (${orderCounts[status] != null ? orderCounts[status] : 0}
                )
            </a>
        </li>
    </c:forEach>
</ul>

    <!-- Orders List Section -->
    <div class="mt-3">
        <c:if test="${fn:length(orderPage.content) == 0}">
            <div class="alert alert-warning">Không có đơn hàng nào.</div>
        </c:if>

        <c:forEach var="order" items="${orderPage.content}">
            <div class="card border-0 shadow-sm mb-3">
                <div class="card-body">

                    <!-- Product Details -->
                    <c:forEach var="line" items="${order.lines}">
					    <!-- Product Details -->
					    <div class="row py-3">
					        <div class="col-md-2">
					            <img src="${pageContext.request.contextPath}/img/product-${line.product.id}.jpg" 
					                 alt="${line.product.name}" class="img-fluid rounded">
					        </div>
					        <div class="col-md-8">
					            <!-- Product Name -->
					            <p class="mb-1 font-weight-bold">${line.product.name}</p>
					            <!-- Product Category -->
					            <p class="text-muted mb-1">Phân loại hàng: ${line.product.getCategoryNames()}</p>
					            <!-- Product Quantity -->
					            <span>Số lượng: ${line.quantity}</span>
					            <!-- Subtotal -->
					            <p class="text-danger mt-2">Tổng: đ${line.subTotal}</p>
					        </div>
					        <div class="col-md-2">
                                <p class="mb-1 text-muted"><del>${order.productsTotal} ₫</del></p>
                                <p class="text-danger font-weight-bold">${order.finalTotal} ₫</p>
					        </div>
					    </div>
					    <hr>
					</c:forEach>

                    <!-- Payment Info -->
                    <div class="border-top pt-2">
                        <div class="d-flex justify-content-end align-items-center">
                            <div class="text-right">
                                <p class="text-muted mb-1">Số tiền phải trả:</p>
                                <p class="text-danger font-weight-bold">${order.finalTotal} ₫</p>
                            </div>
                        </div>
                    </div>

                    <!-- Actions -->
                    <div class="d-flex justify-content-end align-items-center mt-3">
                        <div>
                            <button class="btn btn-danger btn-sm">Hủy Đơn Hàng</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <!-- Pagination Controls -->
        <c:if test="${orderPage.totalPages > 1}">
            <nav>
                <ul class="pagination justify-content-center mt-3">
                    <c:forEach var="i" begin="0" end="${orderPage.totalPages - 1}">
                        <li class="page-item ${i == orderPage.number ? 'active' : ''}">
                            <a class="page-link" href="?status=${currentStatus}&page=${i}&size=${orderPage.size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </c:if>

        <!-- Page Size Selector -->
        <div class="form-group mt-3">
            <label for="pageSize">Hiển thị:</label>
            <select id="pageSize" class="form-control w-auto d-inline" onchange="updatePageSize(this.value)">
                <option value="5" ${orderPage.size == 5 ? 'selected' : ''}>5</option>
                <option value="10" ${orderPage.size == 10 ? 'selected' : ''}>10</option>
                <option value="20" ${orderPage.size == 20 ? 'selected' : ''}>20</option>
            </select>
        </div>
    </div>
</div>

<!-- JavaScript -->
<script>
    function updatePageSize(size) {
        const params = new URLSearchParams(window.location.search);
        params.set("size", size);
        params.set("page", 0); // Reset to the first page
        window.location.search = params.toString();
    }
</script>

