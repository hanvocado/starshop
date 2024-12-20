<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<title>Orders</title>

<body>	
	<!-- Flash message -->
  <%@include file="/common/flash-message.jsp"%>
	
	<!-- Page Header -->
    <div class="page-header">
      <div class="row align-items-center mb-3">
        <div class="col-sm mb-2 mb-sm-0">
          <h1 class="page-header-title">Orders </h1>
        </div>
	<!-- Nav -->
	<div class="col-md-auto">
	
	<div class="col-md-auto text-center">
	  <ul class="nav nav-segment nav-pills" role="tablist">
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'ready_for_ship'}">active</c:if>" id="s2" href="<c:url value="/shipper/orders?status=ready_for_ship" />">Chờ giao hàng</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'shipping'}">active</c:if>" id="s3" href="<c:url value="/shipper/orders?status=shipping" />" >Đang giao</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'delivered'}">active</c:if>" id="s4" href="<c:url value="/shipper/orders?status=delivered" />">Đã giao</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link <c:if test="${status == 'ship_failed'}">active</c:if>" id="s6" href="<c:url value="/shipper/orders?status=ship_failed" />" >Giao thất bại</a>
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
					<form action="<c:url value="/shipper/orders"/>" method="get">
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
               <th class="text-center">Số điện thoại</th>
               <th class="text-center">Phải thu</th>
                              
               <th></th>
             </tr>
           </thead>

           <tbody>
             <c:forEach items="${orders}" var="order" varStatus="STT">
              <tr>
                <td class="table-column-pr-0 text-center">
                	<a href="<c:url value="/shipper/orders/details/${order.id}"/>">
					          	<h5 class="text-primary mb-0">#${order.id }</h5></a>
                     
                </td>
                
                <td>${order.formattedOrderDate }</td>
                
				<td class="text-center">${order.user.getFullName() }</td>
				
				<td class="text-center">${order.user.phoneNumber }</td>
								
                <td>
                	<c:choose>
                		<c:when test="${order.payMethod != 'CASH'}">
		                	0
                		</c:when>
                		<c:otherwise>
                			<fmt:formatNumber value = "${order.finalTotal }" type = "currency"/>
                		</c:otherwise>
                	</c:choose>
                </td>
                				                	
				<td class="text-end">
				  <c:choose>
					<c:when test="${status == 'ready_for_ship' }">
				       <a href="<c:url value="/shipper/orders/update/${order.id}/shipping"/>" class="btn btn-primary btn-xs">
					          	<i class="tio-archive"></i> Bắt đầu giao</a> 					
					</c:when>
					<c:when test="${status == 'shipping' }">
				       <a href="<c:url value="/shipper/orders/update/${order.id}/delivered"/>" class="btn btn-success btn-xs" >
					          	<i class="tio-archive"></i> Đã giao</a> 					
					</c:when>
					
				  </c:choose>
				  <a class="btn btn-sm btn-white" href="<c:url value="/shipper/orders/details/${order.id}"/>">
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
                        <a class="page-link" href="<c:url value='/shipper/orders?status=${status}&pageNo=${pageNo-1}' />" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                            <span class="sr-only">Previous</span>
                        </a>
                        </li>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item <c:if test=" ${pageNo==i-1}">active</c:if>">
                                <a class="page-link" href="<c:url value='/shipper/orders?status=${status}&pageNo=${i-1}' />">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${isLast }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isLast }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/shipper/orders?status=${status}&pageNo=${pageNo+1}' />" aria-label="Next">
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
  
	  <script>	   
	   setTimeout(function() {
	       document.getElementById('message-alert').style.display = 'none';
	     }, 6000);
	  </script>
</body>