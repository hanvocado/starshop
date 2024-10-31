<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>


<title>Products</title>

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
              <h1 class="page-header-title">Products </h1>
              <span>${status }<span class="badge badge-soft-dark ml-2">${count }</span></span>
            </div>

            <div class="col-sm-auto">
              <a class="btn btn-primary" href="<c:url value="/admin/products/add"/>">
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
                <form action="<c:url value="/admin/product"/>" method="get">
                  <!-- Search -->
                  <div class="input-group input-group-merge input-group-flush">
                    <div class="input-group-prepend">
                      <div class="input-group-text">
                        <i class="tio-search"></i>
                      </div>
                    </div>
                    <input id="datatableSearch" value='${search}' name="search" type="search" class="form-control" placeholder="Tìm bằng tên" aria-label="Search products">
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
					    <a class="dropdown-item" href="<c:url value="/admin/products?status=all"/>">Tất cả</a>
					    <div class="dropdown-divider"></div>
					    <a class="dropdown-item" href="<c:url value="/admin/products?status=published"/>">Công khai</a>
					    <a class="dropdown-item" href="<c:url value="/admin/products?status=unpublished"/>">Đã ẩn</a>
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
            <table id="productTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
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

                  <th class="table-column-pr-0">Sản phẩm</th>
                  <th>Giá bán</th>
                  <th>Trạng thái</th>
                  <th>Hành động</th>
                </tr>
              </thead>

              <tbody>
                <c:forEach items="${products}" var="product" varStatus="STT">
	                <tr>
	                  <c:if test="${product.image.substring(0,5)=='https'}">
						<c:url value="${product.image }" var="imgUrl"></c:url>
					  </c:if>
					  <c:if test="${product.image.substring(0,5)!='https'}">
						<c:url value="/img_product/${product.image }" var="imgUrl"></c:url>
					  </c:if>
	                  <td class="table-column-pr-0">
	                    <a class="media align-items-center" href="ecommerce-product-details.html">
	                      <img class="avatar avatar-lg mr-3" src="${imgUrl }" alt="Image ${product.name }">
	                      <div class="media-body">
	                        <h5 class="text-hover-primary mb-0">${product.name }</h5>
	                      </div>
	                    </a>
	                  </td>
	                  <td>${product.name }</td>

	                  <td>
	                  	<c:choose>         
					       <c:when test = "${product.isPublished()}">
					          <span class="badge badge-info">Công khai</span>
					       </c:when>
					       
					       <c:otherwise>
					          <span class="badge badge-secondary">Đã ẩn</span>
					       </c:otherwise>
					    </c:choose>
						</td>
						<!-- End Status -->
						
						<td>
					       <a class="btn btn-soft-danger btn-xs" data-toggle="modal" data-target="#productModal" onclick="deleteProduct(${product.id})">
					          	<i class="tio-delete-outlined"></i> Xóa</a>
					       <a href="<c:url value="/admin/products/update/${product.id }"/>" class="btn btn-soft-warning btn-xs">
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
            <%@include file="/common/pagination.jsp"%>
          </div>
          <!-- End Footer -->

        </div>
        <!-- End Card -->
        
        <div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="inviteUserModalTitle" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered" role="document">
	            <div class="modal-content">
	                <!-- Header -->
	                <div class="modal-header">
	                    <h4 id="inviteUserModalTitle" class="modal-title">Sản phẩm</h4>
	
	                    <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
	                        <i class="tio-clear tio-lg"></i>
	                    </button>
	                </div>
	                <!-- End Header -->
	                <!-- Body -->
	                <div class="modal-body">
	                <form id="productForm" action="" method="post">
						<div id="product-modal-text">Bạn chắc chắn muốn xóa sản phẩm này?</div>

		                
		                <div class="d-flex justify-content-center">
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
		    function deleteProduct(id) {
		        document.getElementById('productForm').action = '<c:url value="/admin/products/delete/' + id + '"/>';
		    }
		    
		    setTimeout(function() {
		        document.getElementById('message-alert').style.display = 'none';
		      }, 6000);
	    </script>
</body>