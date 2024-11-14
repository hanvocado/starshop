<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

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
                  <span class="legend-indicator bg-info"></span>${order.status }
                </span>
                <span class="ml-2 ml-sm-3">
                  <i class="tio-date-range"></i> ${order.formattedOrderDate }
                </span>
              </div>

            </div>

            <div class="col-sm-auto">
              <a class="btn btn-icon btn-sm btn-ghost-secondary rounded-circle mr-1" href="#" data-toggle="tooltip" data-placement="top" title="Previous order">
                <i class="tio-arrow-backward"></i>
              </a>
              <a class="btn btn-icon btn-sm btn-ghost-secondary rounded-circle" href="#" data-toggle="tooltip" data-placement="top" title="Next order">
                <i class="tio-arrow-forward"></i>
              </a>
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
                <h4 class="card-header-title">Order details <span class="badge badge-soft-dark rounded-circle ml-1">4</span></h4>
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
	                          <span>Category:</span>
	                          <span class="font-weight-bold">${line.product.categoryNames }</span>
	                        </div>
	                        <div class="font-size-sm text-body">
	                          <span>Description:</span>
	                          <span class="font-weight-bold">${line.product.description }</span>
	                        </div>
	                        <div class="font-size-sm text-body">
	                          <span>Đơn giá gốc:</span>
	                          <span class="font-weight-bold">${line.product.salePrice }</span>
	                        </div>
	                      </div>
	
	                      <div class="col col-md-2 align-self-center">
	                        <h5>${line.product.displayPrice }</h5>
	                      </div>
	
	                      <div class="col col-md-2 align-self-center">
	                        <h5>${line.quantity }</h5>
	                      </div>
	
	                      <div class="col col-md-2 align-self-center text-right">
	                        <h5>subtotal</h5>
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
                      <dt class="col-sm-6">Subtotal:</dt>
                      <dd class="col-sm-6">$65.00</dd>
                      <dt class="col-sm-6">Shipping fee:</dt>
                      <dd class="col-sm-6">$0.00</dd>
                      <dt class="col-sm-6">Tax:</dt>
                      <dd class="col-sm-6">$7.00</dd>
                      <dt class="col-sm-6">Total:</dt>
                      <dd class="col-sm-6">$65.00</dd>
                      <dt class="col-sm-6">Amount paid:</dt>
                      <dd class="col-sm-6">$65.00</dd>
                    </dl>
                    <!-- End Row -->
                  </div>
                </div>
                <!-- End Row -->
              </div>
              <!-- End Body -->
            </div>
            <!-- End Card -->

            <!-- Card -->
            <div class="card">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">
                  Shipping activity
                  <span class="badge badge-soft-dark ml-1">
                    <span class="legend-indicator bg-dark"></span>Marked as fulfilled
                  </span>
                </h4>
              </div>
              <!-- End Header -->
          
              <!-- Body -->
              <div class="card-body">
                <!-- Step -->
                <ul class="step step-icon-xs">
                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <small class="step-divider">Wednesday, 19 August</small>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>

                      <div class="step-content">
                        <h5 class="mb-1">
                          <a class="text-dark" href="#">Delivered</a>
                        </h5>

                        <p class="font-size-sm mb-0">4:17 AM</p>
                      </div>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>

                      <div class="step-content">
                        <h5 class="mb-1">
                          <a class="text-dark" href="#">Out for delivery</a>
                        </h5>

                        <p class="font-size-sm mb-0">2:38 AM</p>
                      </div>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>

                      <div class="step-content">
                        <h5 class="mb-1">
                          <a class="text-dark" href="#">Package arrived at the final delivery station</a>
                        </h5>

                        <p class="font-size-sm mb-0">2:00 AM</p>
                      </div>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <small class="step-divider">Tuesday, 18 August</small>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>

                      <div class="step-content">
                        <h5 class="mb-1">
                          <a class="text-dark" href="#">Tracking number</a>
                        </h5>

                        <a class="link" href="#">3981241023109293</a>
                        <p class="font-size-sm mb-0">6:29 AM</p>
                      </div>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>

                      <div class="step-content">
                        <h5 class="mb-1">
                          <a class="text-dark" href="#">Package has dispatched</a>
                        </h5>

                        <p class="font-size-sm mb-0">6:29 AM</p>
                      </div>
                    </div>
                  </li>
                  <!-- End Step Item -->

                  <!-- Step Item -->
                  <li class="step-item">
                    <div class="step-content-wrapper">
                      <span class="step-icon step-icon-soft-dark step-icon-pseudo"></span>

                      <div class="step-content">
                        <h5 class="mb-1">
                          <a class="text-dark" href="#">Order was placed</a>
                        </h5>

                        <p class="font-size-sm mb-0">Order #32543</p>
                      </div>
                    </div>
                  </li>
                  <!-- End Step Item -->
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