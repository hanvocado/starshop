<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Chi tiết đơn hàng</title>

<body>
	<!-- Page Header -->
        <div class="page-header d-print-none">
          <div class="row align-items-center">
            <div class="col-sm mb-2 mb-sm-0">
              
              <div class="d-sm-flex align-items-sm-center">
                <h1 class="page-header-title">Order #${order.id }</h1>
                <c:choose>
               		<c:when test="${order.isPayed()}">
	                	<span class="badge badge-soft-success ml-sm-3">
	                      <span class="legend-indicator bg-success"></span>Paid
	                    </span>
               		</c:when>
               		<c:otherwise>
               			<span class="badge badge-soft-warning ml-sm-3">
	                      <span class="legend-indicator bg-warning"></span>Not paid
	                    </span>
               		</c:otherwise>
               	</c:choose>
               	<span class="badge badge-soft-info ml-2 ml-sm-3">
                  <span class="legend-indicator bg-info"></span>${order.currentStatus }
                </span>
                <span class="ml-2 ml-sm-3">
                  <i class="tio-date-range"></i> ${order.formattedOrderDate }
                </span>
              </div>

            </div>
          </div>
        </div>
        <!-- End Page Header -->
        
        <div class="row">
          <div class="col-lg-8 mb-3 mb-lg-0">
            <!-- Card -->
            <div class="card mb-3 mb-lg-5">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">Chi tiết đơn hàng <span class="badge badge-soft-dark rounded-circle ml-1">${order.numberOfProducts }</span></h4>
              </div>
              <!-- End Header -->
          
              <!-- Body -->
              <div class="card-body">
              	<c:forEach var="line" items="${order.lines }">              	
              	
	                <!-- Media -->
	                <div class="media">
	                  <div class="avatar avatar-xl mr-3">
	                  	<c:if test="${line.product.image.substring(0,5)=='https'}">
							<c:url value="${product.image }" var="imgUrl"></c:url>
						  </c:if>
						  <c:if test="${line.product.image.substring(0,5)!='https'}">
							<c:url value="/img/${line.product.image }" var="imgUrl"></c:url>
						  </c:if>
	                    <img class="img-fluid" src="${imgUrl }" alt="Image Description">
	                  </div>
	
	                  <div class="media-body">
	                    <div class="row">
	                      <div class="col-md-6 mb-3 mb-md-0">
	                        <a class="h5 d-block" href="<c:url value='/admin/products/update/${line.product.id }' />">${product.name }</a>
	
	                        <div class="font-size-sm text-body">
	                          <span class="font-weight-bold">${line.product.name }</span>
	                        </div>
	                        <div class="font-size-sm text-body">
	                          <span class="font-weight-bold"><fmt:formatNumber value = "${line.product.salePrice }" type = "currency"/></span>
	                        </div>
	                      </div>
	
	                      <div class="col col-md-2 align-self-center">
	                        <h5><fmt:formatNumber value = "${line.product.displayPrice }" type = "currency"/></h5>
	                      </div>
	
	                      <div class="col col-md-2 align-self-center">
	                        <h5>${line.quantity }</h5>
	                      </div>
	
	                      <div class="col col-md-2 align-self-center text-right">
	                        <h5><fmt:formatNumber value = "${line.subTotal }" type = "currency"/></h5>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	                <!-- End Media -->
	
	                <hr>
				</c:forEach>
				

                <div class="row justify-content-md-end mb-3">
                  <div class="col-md-8 col-lg-7">
                    <dl class="row text-sm-right">
                      <dt class="col-sm-6">Tổng sản phẩm:</dt>
                      <dd class="col-sm-6">${order.productsTotal }</dd>
                      <dt class="col-sm-6">Phí vận chuyển:</dt>
                      <dd class="col-sm-6">${order.shippingFee }</dd>
                      <c:if test="${order.discountTotal > 0}">
	                      <dt class="col-sm-6">Voucher:</dt>
	                      <dd class="col-sm-6">- ${order.discountTotal }</dd>
                      </c:if>
                      <dt class="col-sm-6">Tổng cộng:</dt>
                      <dd class="col-sm-6">${order.finalTotal }</dd>
                    </dl>
                    <!-- End Row -->
                  </div>
                </div>
                <!-- End Row -->
              </div>
              <!-- End Body -->
            </div>
            <!-- End Card -->

            
          </div>
        	
        	<div class="col-lg-4 mb-3 mb-lg-0">
        		<!-- Card -->
            <div class="card">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">
                  Theo dõi đơn hàng
                </h4>
              </div>
              <!-- End Header -->
          
              <!-- Body -->
              <div class="card-body">
                <!-- Step -->
                <ul class="step step-icon-xs">
					<c:forEach items="${order.tracking }" var="track">						
	                  <!-- Step Item -->
	                  <li class="step-item">
	                    <div class="step-content-wrapper">
	                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>
	
	                      <div class="step-content">
	                        <h5 class="mb-1">
	                          <a class="text-dark" href="#">${track.status.getDescription() }</a>
	                        </h5>
	
	                        <p class="font-size-sm mb-0">${track.formattedTime }</p>
	                      </div>
	                    </div>
	                  </li>
	                  <!-- End Step Item -->
					</c:forEach>
					
                </ul>
                <!-- End Step -->

                <small>Times are shown in the local time zone.</small>
              </div>
              <!-- End Body -->
            </div>
            <!-- End Card -->
        	</div>
        
        
        </div>
</body>