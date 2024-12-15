<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
    

<title>Vouchers</title>

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
          <h1 class="page-header-title">Vouchers <span class="badge badge-soft-dark ml-2">${count }</span></h1>
        </div>

        <div class="col-sm-auto">
          <a class="btn btn-primary" href="<c:url value="/admin/vouchers/add"/>">
              <i class="tio-add mr-1"></i> Tạo mới
          </a>
        </div>
      </div>
      <!-- End Row -->

      <!-- Nav Scroller -->
      <div class="js-nav-scroller hs-nav-scroller-horizontal">
        <span class="hs-nav-scroller-arrow-prev" style="display: none;">
          <a class="hs-nav-scroller-arrow-link" href="javascript:;">
            <i class="tio-chevron-left"></i>
          </a>
        </span>

        <span class="hs-nav-scroller-arrow-next" style="display: none;">
          <a class="hs-nav-scroller-arrow-link" href="javascript:;">
            <i class="tio-chevron-right"></i>
          </a>
        </span>

      </div>
      <!-- End Nav Scroller -->
    </div>
    <!-- End Page Header -->
        
        
     <!-- Card -->
     <div class="card">
       <!-- Header -->
       <div class="card-header">
         <div class="row justify-content-between align-items-center flex-grow-1">
           <div class="col-md-4 mb-3 mb-md-0">
             <form action="<c:url value="/admin/vouchers"/>" method="get">
               <!-- Search -->
               <div class="input-group input-group-merge input-group-flush">
                 <div class="input-group-prepend">
                   <div class="input-group-text">
                     <i class="tio-search"></i>
                   </div>
                 </div>
                 <input id="datatableSearch" value='${search}' name="search" type="search" class="form-control" placeholder="Tìm bằng tên hoặc mã code" aria-label="Search vouchers">
               </div>
               <!-- End Search -->
             </form>
           </div>

           <div class="col-auto">
           <!-- Dropleft FILTER -->
		<div class="btn-group dropleft">
		  <button type="button" id="filter-btn" class="btn btn-white dropdown-toggle" 
		  		data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    ${status }
		  </button>
		  <div class="dropdown-menu">
		    <a class="dropdown-item" href="#" onclick="filterTable('Tất cả')">Tất cả</a>
		    <div class="dropdown-divider"></div>
		    <a class="dropdown-item" href="#" onclick="filterTable('Còn hạn')">Còn hạn</a>
		    <a class="dropdown-item" href="#" onclick="filterTable('Hết hạn')">Hết hạn</a>
		  </div>
		</div>
		<!-- End Dropleft FILTER -->
	</div>
           
         </div>
         <!-- End Row -->
       </div>
       <!-- End Header -->

       <!-- Table -->
       <div class="table-responsive datatable-custom">
         <table id="voucherTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
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

               <th class="table-column-pr-0">Phiếu giảm giá</th>
               <th>Đơn tối thiểu</th>
               <th>Giá trị giảm</th>
               <th>Giảm tối đa</th>
               <th>Ngày hết hạn</th>
               <th>Hành động</th>
             </tr>
           </thead>

           <tbody>
             <c:forEach items="${vouchers}" var="voucher" varStatus="STT">
              <tr>
                <td>${voucher.name }
                	<c:if test="${voucher.isFreeship()}">
                		<span class="badge badge-dark badge-pill ml-1">Freeship</span>
                	</c:if>
                </td>
                
                <td><fmt:formatNumber value = "${voucher.minOrderItemsTotal }" type = "currency"/></td>
                
                <td>${voucher.discountPercent }%</td>
                
                <td><fmt:formatNumber value = "${voucher.maxDiscountAmount }" type = "currency"/></td>
                

                <td>
                	${voucher.formattedExpiredAt }
                	<c:choose>         
				       <c:when test = "${voucher.isExpired()}">
				          <span class="badge badge-secondary">Hết hạn</span>
				       </c:when>
				       
				       <c:otherwise>
				          <span class="badge badge-info">Còn hạn</span>
				       </c:otherwise>
				    </c:choose>
				</td>
				<!-- End Status -->
			
			<td>
		       <a class="btn btn-soft-danger btn-xs" data-toggle="modal" data-target="#voucherModal" onclick="deleteVoucher(${voucher.code})">
		          	<i class="tio-delete-outlined"></i> Xóa</a>
		       <a href="<c:url value="/admin/vouchers/update/${voucher.code }"/>" class="btn btn-soft-warning btn-xs">
			          	<i class="tio-archive"></i> Cập nhật</a> 
		          
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
	                        <a class="page-link" href="<c:url value='/admin/vouchers?pageNo=${pageNo-1}' />" aria-label="Previous">
	                            <span aria-hidden="true">«</span>
	                            <span class="sr-only">Previous</span>
	                        </a>
	                        </li>
	
	                        <c:forEach var="i" begin="1" end="${totalPages}">
	                            <li class="page-item <c:if test=" ${pageNo==i-1}">active</c:if>">
	                                <a class="page-link" href="<c:url value='/admin/vouchers?pageNo=${i-1}' />">${i}</a>
	                            </li>
	                        </c:forEach>
	
	                        <c:if test="${isLast }">
	                            <li class="page-item disabled">
	                        </c:if>
	                        <c:if test="${!isLast }">
	                            <li class="page-item">
	                        </c:if>
	                        <a class="page-link" href="<c:url value='/admin/vouchers?pageNo=${pageNo+1}' />" aria-label="Next">
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
     
     <div class="modal fade" id="voucherModal" tabindex="-1" role="dialog" aria-labelledby="inviteUserModalTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
              <!-- Header -->
              <div class="modal-header">
                  <h4 id="inviteUserModalTitle" class="modal-title">Phiếu giảm giá</h4>

                  <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
                      <i class="tio-clear tio-lg"></i>
                  </button>
              </div>
              <!-- End Header -->
              <!-- Body -->
              <div class="modal-body">
              <form id="voucherForm" action="" method="post">
				<div id="voucher-modal-text">Bạn chắc chắn muốn xóa phiếu giảm giá này?</div>

               
               <div class="d-flex justify-content-center mt-3">
                		<button id="modalSubmitButton" type="submit" class="btn btn-outline-primary">Xóa</button>	                
               </div>
		 </form>
              </div>
              <!-- End Body -->
          </div>
      </div>
  </div>
  	 <!-- End Modal -->
  
	<script>
	  
	  function filterTable(status) {
		    var rows = document.querySelectorAll("#voucherTable tbody tr");
		    document.getElementById('filter-btn').innerText = status;
		    rows.forEach(function(row) {
		      var cellStatus = row.cells[4].innerText.trim();
		      if (status === 'Tất cả' || cellStatus.includes(status)) {
		        row.style.display = '';
		      } else {
		        row.style.display = 'none';
		      }
		    });
		  }
	  
	   function deleteVoucher(id) {
	       document.getElementById('voucherForm').action = '<c:url value="/admin/vouchers/delete/' + id + '"/>';
	   }
	   
	   setTimeout(function() {
	       document.getElementById('message-alert').style.display = 'none';
	     }, 6000);
	</script>
</body>