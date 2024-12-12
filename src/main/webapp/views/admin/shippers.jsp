<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
    

<title>Quản lý nhân viên giao hàng</title>

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
          <h1 class="page-header-title">Nhân viên giao hàng <span class="badge badge-soft-dark ml-2">${count }</span></h1>
        </div>

        <div class="col-sm-auto">
          <a class="btn btn-primary" data-toggle="modal" data-target="#shipperModal">
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
             <form action="<c:url value="/admin/shippers"/>" method="get">
               <!-- Search -->
               <div class="input-group input-group-merge input-group-flush">
                 <div class="input-group-prepend">
                   <div class="input-group-text">
                     <i class="tio-search"></i>
                   </div>
                 </div>
                 <input id="searchInput" type="search" class="form-control" placeholder="Tên" aria-label="Search">
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
         <table id="shipperTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
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
               <th class="table-column-pr-0">Shipper</th>
               <th>Email</th>
               <th>Hoạt động từ</th>
               <th class="text-center">Tổng số đơn</th>
               <th class="text-center">Tỷ lệ thành công (%)</th>
               <th class="text-center">Hành động</th>
             </tr>
           </thead>

           <tbody>
             <c:forEach items="${shippers}" var="shipper" varStatus="STT">
              <tr class="shipper">
                <td class="shipper-name">
                	${shipper.getFullName() }
                	<c:if test="${!shipper.isActive()}">
                		<span class="badge badge-danger badge-pill ml-1">Disabled</span>
                	</c:if>
                </td>
                
                <td>${shipper.email }</td>
                
                <td>${shipper.formattedCreatedAt }</td>
                
                <td class="text-center">${shipper.getNumberOfOrders() }</td>
                
                <td class="text-center">${shipper.getSuccessRate() }</td>
			
				<td class="text-center">
				  <a class="btn btn-soft-danger btn-xs" href="<c:url value="/admin/shippers/change-status/${shipper.id }"/>">
				  <c:choose>
				  	<c:when test="${shipper.isActive()}">
			          	<i class="tio-archieve"></i> Disable
				  	</c:when>
				  	<c:otherwise>
			          	<i class="tio-active"></i> Enable
				  	</c:otherwise>
				  </c:choose>
				  </a>
			          
		        </td>
			
              </tr>
             </c:forEach>
           </tbody>
         </table>            
       </div>
       <!-- End Table -->
       
       <!-- Footer -->
       <!-- End Footer -->

     </div>
     <!-- End Card -->
     
     <div class="modal fade" id="shipperModal" tabindex="-1" role="dialog" aria-labelledby="inviteUserModalTitle" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered" role="document">
	            <div class="modal-content">
	                <!-- Header -->
	                <div class="modal-header">
	                    <h4 id="inviteUserModalTitle" class="modal-title">Nhân viên giao hàng</h4>
	
	                    <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
	                        <i class="tio-clear tio-lg"></i>
	                    </button>
	                </div>
	                <!-- End Header -->
	                <!-- Body -->
	                <div class="modal-body">
	                <form id="shipperForm" action="<c:url value="/admin/shippers/add"/>" method="post">
						<input name="id" id="shipperId" type="hidden" />
						<div class="row">
							<div class="col-md-6">
								<input name="lastName" type="text" class="form-control mb-3" placeholder="Họ" />
							</div>
							<div class="col-md-6">							
				                <input name="firstName" type="text" class="form-control mb-3" placeholder="Tên" />					     
							</div>
						</div>
						<input name="email" type="text" class="form-control mb-3" placeholder="Email" />
						<input name="password" type="password" value="12345fast" class="form-control mb-3" placeholder="Mật khẩu" />	
		                
		                <div class="d-flex justify-content-center">
	                  		<button id="modalSubmitButton" type="submit" class="btn btn-outline-primary">Submit</button>	                
		                </div>
					 </form>
	                </div>
	                <!-- End Body -->
	            </div>
	        </div>
	    </div>
	    <!-- End Modal -->
     
  
	<script src="/exec/js/shippers.js"></script>
</body>