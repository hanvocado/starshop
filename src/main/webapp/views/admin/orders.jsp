<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Orders</title>

<body>	
	<!-- Display Flash Messages -->
	<c:if test="${not empty message }">
	
		<c:if test="${message.type == 'success'}">	
		    <c:set var="icon_class" value="tio-checkmark-circle-outlined" />
		</c:if>
		<c:if test="${message.type == 'danger'}">
		    <c:set var="icon_class" value="tio-message-failed-outlined" />
		</c:if>
		
		<!-- ALERT -->
		<div id="message-alert" class="alert alert-soft-${message.type} alert-dismissible fade show" role="alert" style="position:fixed; top:50px; right:70px; z-index:9999;">
			  <i class="${icon_class }"></i>
		  <strong>${message.content }</strong> 
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <i class="tio-clear tio-lg"></i>
		  </button>
		</div>

	</c:if>
	
	<!-- Page Header -->
    <div class="page-header">
      <div class="row align-items-center mb-3">
        <div class="col-sm mb-2 mb-sm-0">
          <h1 class="page-header-title">Orders </h1>
          <span>${status }<span class="badge badge-soft-dark ml-2">${count }</span></span>
        </div>
	<!-- Nav -->
	<div class="col-md-auto">
	
	<div class="col-md-auto text-center">
	  <ul class="nav nav-segment nav-pills" role="tablist">
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'pending'}">active</c:if>" id="pending" href="<c:url value="/admin/orders?status=pending" />">Chờ xác nhận</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'processing'}">active</c:if>" id="processing" href="<c:url value="/admin/orders?status=processing" />">Đang chuẩn bị hàng</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'readyforship'}">active</c:if>" id="s2" href="<c:url value="/admin/orders?status=readyforship" />">Chờ giao hàng</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'shipping'}">active</c:if>" id="s3" href="<c:url value="/admin/orders?status=shipping" />" >Đang giao</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'delivered'}">active</c:if>" id="s4" href="<c:url value="/admin/orders?status=delivered" />">Đã giao</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'cancelled'}">active</c:if>" id="s5" href="<c:url value="/admin/orders?status=cancelled" />" >Đã hủy</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'shipfailed'}">active</c:if>" id="s6" href="<c:url value="/admin/orders?status=shipfailed" />" >Giao thất bại</a>
	    </li>
	  </ul>
	</div>
	</div>
	<!-- End Nav -->
      </div>
      <!-- End Row -->

      
    </div>
    <!-- End Page Header -->
	
	
        
     <!-- Card -->
     <div class="card">
       <!-- Header -->
		<div class="card-header">
			<div class="row justify-content-between align-items-center flex-grow-1">
				<div class="col-md-4 mb-3 mb-md-0">
					<form action="<c:url value="/admin/orders"/>" method="get">
						<!-- Search -->
						<div class="input-group input-group-merge input-group-flush">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="tio-search"></i>
								</div>
							</div>
							<input id="datatableSearch" value='${search}' name="searchId"
								type="search" class="form-control" placeholder="Tìm bằng mã đơn hàng"
								aria-label="Search orders">
						</div>
						<!-- End Search -->
					</form>
				</div>
			</div>
			<!-- End Row -->
		</div>
		<!-- End Header -->

       <!-- Table -->
       <div class="table-responsive datatable-custom">
         <table id="orderTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
                  "columnDefs": [{
                     "targets": [0, 4, 9],
                     "width": "5%",
                     "orderable": false
                   }],
                  "order": [],
                  "info": {
                    "totalQty": ${count }
                  },
                  "search": "#datatableSearch",
                  "entries": "#datatableEntries",
                  "pageLength": ${pageSize },
                  "isResponsive": false,
                  "isShowPaging": false,
                  "pagination": "datatablePagination"
                }'>
           <thead class="thead-light">
             <tr>

               <th class="table-column-pr-0">Đơn đặt hàng</th>
               <th>Ngày đặt hàng</th>
               <th class="text-center">Khách hàng</th>
               <th class="text-center">Số tiền</th>
               
               <c:if test="${status != 'pending' && status != 'processing' }"><th>Shipper</th></c:if>
               
               <th>Hành động</th>
             </tr>
           </thead>

           <tbody>
             <c:forEach items="${orders}" var="order" varStatus="STT">
              <tr>
                <td class="table-column-pr-0 text-center">
                	<a href="<c:url value="/admin/orders/details/${order.id}"/>">
					          	<h5 class="text-primary mb-0">#${order.id }</h5></a>
                     
                </td>
                
                <td>${order.formattedOrderDate }</td>
                
				<td class="text-center">${order.user.userName }</td>
								
                <td><fmt:formatNumber value = "${order.finalTotal }" type = "currency"/>
                	<c:choose>
                		<c:when test="${order.isPayed()}">
		                	<span class="badge badge-soft-success">
		                      <span class="legend-indicator bg-success"></span>Paid
		                    </span>
                		</c:when>
                		<c:otherwise>
                			<span class="badge badge-soft-warning">
		                      <span class="legend-indicator bg-warning"></span>Pending
		                    </span>
                		</c:otherwise>
                	</c:choose>
                </td>
				                	
				<c:if test="${not empty order.shipper }"><th>${order.shipper.userName }</th></c:if>               				
				<td class="text-end">
				  <c:choose>
					<c:when test="${status == 'pending' }">
				       <a href="<c:url value="/admin/orders/update/${order.id}/processing"/>" class="btn btn-primary btn-xs">
					          	<i class="tio-archive"></i> Xác nhận</a> 					
					</c:when>
					<c:when test="${status == 'processing' }">
				       <a data-toggle="modal" data-target="#shippersModal" class="btn btn-info btn-xs"
				       		onclick="assignShipperFor(${order.id})">
					          	<i class="tio-archive"></i> Giao shipper</a> 					
					</c:when>
					
				  </c:choose>
				  <a class="btn btn-sm btn-white" href="<c:url value="/admin/orders/details/${order.id}"/>">
                        <i class="tio-visible-outlined"></i> Chi tiết
                      </a>	          
	            </td>
			
              </tr>
             </c:forEach>
           </tbody>
         </table>            
       </div>
       <!-- End Table -->
       
       <!-- Footer -->
       <div class="card-footer">
         <!-- Pagination -->
    <div class="row justify-content-center justify-content-sm-between align-items-sm-center">
        <div class="col-md mb-2 mb-sm-0">
            <div class="d-flex justify-content-end justify-content-sm-end align-items-end">
				            <div class="d-flex justify-content-center justify-content-sm-end">
                <!-- footer Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${isFirst }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isFirst }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/admin/orders?status=${status}&pageNo=${pageNo-1}' />" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                            <span class="sr-only">Previous</span>
                        </a>
                        </li>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item <c:if test=" ${pageNo==i-1}">active</c:if>">
                                <a class="page-link" href="<c:url value='/admin/orders?status=${status}&pageNo=${i-1}' />">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${isLast }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isLast }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/admin/orders?status=${status}&pageNo=${pageNo+1}' />" aria-label="Next">
                            <span aria-hidden="true">»</span>
                            <span class="sr-only">Next</span>
                        </a>
                        </li>
                    </ul>
                </nav>
                <!-- End footer Pagination -->
            </div>
				

        	</div>
        </div>
    </div>
    <!-- End Pagination -->
       
       </div>
       <!-- End Footer -->

     </div>
     <!-- End Card -->
     
     <div class="modal fade" id="shippersModal" tabindex="-1" role="dialog" aria-labelledby="inviteUserModalTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
              <!-- Header -->
              <div class="modal-header">
                  <h4 id="inviteUserModalTitle" class="modal-title">Chọn shipper</h4>

                  <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
                      <i class="tio-clear tio-lg"></i>
                  </button>
              </div>
              <!-- End Header -->
              <!-- Body -->
              <div class="modal-body">
              <form id="orderForm" action="<c:url value="/admin/orders/assignshipper"/>" method="post">
				<input name="orderId" id="orderId" type="hidden" />
				<!-- Dropdown -->
				<div class="btn-group">
				  <select name="shipperId" class="form-control">
				  	<c:forEach var="shipper" items="${shippers }">
				  		 <option value="${shipper.id}">${shipper.userName }</option>
				  	</c:forEach>
				  </select>
				</div>
				<!-- End Dropdown -->
				<div id="order-modal-text">Bạn chắc chắn muốn giao đơn hàng cho shipper này?</div>
               
	               <div class="d-flex justify-content-center">
	                	<button id="modalSubmitButton" type="submit" class="btn btn-outline-primary">OK</button>	                
	               </div>
		 		</form>
              </div>
              <!-- End Body -->
          </div>
      </div>
  </div>
  	 <!-- End Modal -->
  
	  <script>	   
	   setTimeout(function() {
	       document.getElementById('message-alert').style.display = 'none';
	     }, 6000);
	   
	   function assignShipperFor(orderId) {
		   document.getElementById('orderId').value = orderId;
	   }
	  </script>
</body>