<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<title>Quản lý danh mục</title>

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
		<div id="message-alert" class="alert alert-soft-${message.type} alert-dismissible fade show" role="alert" style="position:fixed; top:20px; right:60px; z-index:9999;">
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
              <h1 class="page-header-title">Categories <span class="badge badge-soft-dark ml-2">${count }</span></h1>
            </div>

            <div class="col-sm-auto">
              <a class="btn btn-primary" href="javascript:;" data-toggle="modal" data-target="#categoryModal"
              		onclick="addCategory()">
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
                <form action="<c:url value="/admin/categories"/>" method="get">
                  <!-- Search -->
                  <div class="input-group input-group-merge input-group-flush">
                    <div class="input-group-prepend">
                      <div class="input-group-text">
                        <i class="tio-search"></i>
                      </div>
                    </div>
                    <input id="datatableSearch" value='${search}' name="search" type="search" class="form-control" placeholder="Tìm bằng tên" aria-label="Search categories">
                  </div>
                  <!-- End Search -->
                </form>
              </div>

              <div class="col-auto">
		            <!-- Dropleft FILTER -->
					<div class="btn-group dropleft">
					  <button type="button" id="filter-btn" class="btn btn-white dropdown-toggle" 
					  		data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    Lọc
					  </button>
					  <div class="dropdown-menu">
					    <a class="dropdown-item" href="#" onclick="filterTable('Tất cả')">Tất cả</a>
					    <div class="dropdown-divider"></div>
					    <a class="dropdown-item" href="#" onclick="filterTable('Công khai')">Công khai</a>
					    <a class="dropdown-item" href="#" onclick="filterTable('Đã ẩn')">Đã ẩn</a>
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
            <table id="categoryTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
                     "columnDefs": [{
                        "targets": [0, 4, 9],
                        "width": "5%",
                        "orderable": false
                      }],
                     "order": [],
                     "info": {
                       "totalQty": "#datatableWithPaginationInfoTotalQty"
                     },
                     "search": "#datatableSearch",
                     "entries": "#datatableEntries",
                     "pageLength": 12,
                     "isResponsive": false,
                     "isShowPaging": false,
                     "pagination": "datatablePagination"
                   }'>
              <thead class="thead-light">
                <tr>
                  <th class="text-center">Tên</th>
                  <th>Trạng thái</th>
                  <th>Hành động</th>
                </tr>
              </thead>

              <tbody>
                <c:forEach items="${categories}" var="cate" varStatus="STT">
	                <tr>
	                  <td>${cate.name }</td>

	                  <td>
	                  	<c:choose>         
					       <c:when test = "${cate.isPublished()}">
					          <span class="badge badge-info">Công khai</span>
					       </c:when>
					       
					       <c:otherwise>
					          <span class="badge badge-secondary">Đã ẩn</span>
					       </c:otherwise>
					    </c:choose>
						</td>
						<!-- End Status -->
						
						<td>
					       <a class="btn btn-soft-danger btn-xs" data-toggle="modal" data-target="#categoryModal" onclick="deleteCategory(${cate.id})">
					          	<i class="tio-delete-outlined"></i> Xóa</a>
					       <a class="btn btn-soft-warning btn-xs" data-toggle="modal" data-target="#categoryModal" onclick="editCategory(${cate.id}, '${cate.name}', ${cate.isPublished()})">
						          	<i class="tio-archive"></i> Cập nhật</a> 
					          
			            </td>
						
	                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <!-- End Table -->

        </div>
        <!-- End Card -->
        
        <div class="modal fade" id="categoryModal" tabindex="-1" role="dialog" aria-labelledby="inviteUserModalTitle" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered" role="document">
	            <div class="modal-content">
	                <!-- Header -->
	                <div class="modal-header">
	                    <h4 id="inviteUserModalTitle" class="modal-title">Danh mục</h4>
	
	                    <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
	                        <i class="tio-clear tio-lg"></i>
	                    </button>
	                </div>
	                <!-- End Header -->
	                <!-- Body -->
	                <div class="modal-body">
	                <form id="categoryForm" action="<c:url value="/admin/add-category"/>" method="post">
						<input name="id" id="categoryId" type="hidden" />
		                <input name="name" id="categoryName" type="text" class="form-control mb-3" placeholder="Tên" />
						<div id="cate-modal-text"></div>
					     <!-- Toggle Switch -->
					     <div id="switchContainer">
			                <label class="row toggle-switch" for="categorySwitch">
			                  <span class="col-8 col-sm-9 toggle-switch-content">
			                    <span class="text-dark">Công khai</span>
			                  </span>
			                  <span class="col-4 col-sm-3">
			                    <input name="isPublished" type="checkbox" class="toggle-switch-input" id="categorySwitch" value="true" checked>
			                    <span class="toggle-switch-label ml-auto">
			                      <span class="toggle-switch-indicator"></span>
			                    </span>
			                  </span>
			                </label>
					     </div>
		                <!-- End Toggle Switch -->
		                
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
    
    	
    <script>
	  function filterTable(status) {
	    var rows = document.querySelectorAll("#categoryTable tbody tr");
	    document.getElementById('filter-btn').innerText = status;
	    rows.forEach(function(row) {
	      var statusText = row.cells[1].innerText.trim();
	      if (status === 'Tất cả' || statusText === status) {
	        row.style.display = '';
	      } else {
	        row.style.display = 'none';
	      }
	    });
	  }
	  
	  function addCategory() {
	        document.getElementById('modalSubmitButton').innerText = 'Tạo';
	        document.getElementById('categoryName').style.display = '';
	        document.getElementById('modalSubmitButton').className = 'btn btn-outline-primary';
	  }
	  
	  function editCategory(id, name, isPublished) {
	        document.getElementById('categoryForm').action = '/admin/update-category';
	        document.getElementById('categoryName').style.display = '';
	        document.getElementById('switchContainer').style.display = '';
	        document.getElementById('cate-modal-text').innerText = '';
	        document.getElementById('categoryId').value = id;
	        document.getElementById('categoryName').value = name;
	        document.getElementById('categorySwitch').checked = isPublished;
	        document.getElementById('modalSubmitButton').innerText = 'Cập nhật';
	        document.getElementById('modalSubmitButton').className = 'btn btn-soft-primary';
	    }

	    function deleteCategory(id) {
	        document.getElementById('categoryForm').action = '/admin/delete-category';
	        document.getElementById('categoryId').value = id;
	        document.getElementById('categoryName').style.display = 'none';
	        document.getElementById('switchContainer').style.display = 'none';
	        document.getElementById('cate-modal-text').innerText = 'Bạn chắn chắn muốn xóa?';
	        document.getElementById('modalSubmitButton').innerText = 'Xóa';
	        document.getElementById('modalSubmitButton').className = 'btn btn-danger';
	    }
	    
	    setTimeout(function() {
	        document.getElementById('message-alert').style.display = 'none';
	      }, 6000);
	</script>
    
</body>