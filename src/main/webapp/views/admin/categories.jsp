<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<title>Categories</title>

<body>
	<!-- Page Header -->
        <div class="page-header">
          <div class="row align-items-center mb-3">
            <div class="col-sm mb-2 mb-sm-0">
              <h1 class="page-header-title">Categories <span class="badge badge-soft-dark ml-2">${count }</span></h1>
            </div>

            <div class="col-sm-auto">
              <a class="btn btn-primary" href="javascript:;" data-toggle="modal" data-target="#categoryModal">
                  <i class="tio-user-add mr-1"></i> Add Category
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
        
        <div class="row justify-content-end mb-3">
          <div class="col-lg">
            <!-- Datatable Info -->
            <div id="datatableCounterInfo" style="display: none;">
              <div class="d-sm-flex justify-content-lg-end align-items-sm-center">
                <span class="d-block d-sm-inline-block font-size-sm mr-3 mb-2 mb-sm-0">
                  <span id="datatableCounter">0</span>
                  Selected
                </span>
                <a class="btn btn-sm btn-outline-danger mb-2 mb-sm-0 mr-2" href="javascript:;">
                  <i class="tio-delete-outlined"></i> Delete
                </a>
                <a class="btn btn-sm btn-white mb-2 mb-sm-0 mr-2" href="javascript:;">
                  <i class="tio-archive"></i> Không công khai
                </a>
                <a class="btn btn-sm btn-white mb-2 mb-sm-0 mr-2" href="javascript:;">
                  <i class="tio-publish"></i> Công khai
                </a>
              </div>
            </div>
            <!-- End Datatable Info -->
          </div>
        </div>
        <!-- End Row -->
        
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
                    <input id="datatableSearch" name="search" type="search" class="form-control" placeholder="Hoa ..." aria-label="Search categories">
                  </div>
                  <!-- End Search -->
                </form>
              </div>

              <div class="col-auto">
                <!-- Unfold -->
                <div class="hs-unfold mr-2">
                  <a class="js-hs-unfold-invoker btn btn-white" href="javascript:;" data-hs-unfold-options='{
                      "target": "#datatableFilterSidebar",
                      "type": "css-animation",
                      "animationIn": "fadeInRight",
                      "animationOut": "fadeOutRight",
                      "hasOverlay": true,
                      "smartPositionOff": true
                     }'>
                    <i class="tio-filter-list mr-1"></i> Filters
                  </a>
                </div>
                <!-- End Unfold -->

              </div>
            </div>
            <!-- End Row -->
          </div>
          <!-- End Header -->

          <!-- Table -->
          <div class="table-responsive datatable-custom">
            <table id="datatable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
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
                  <th scope="col" class="table-column-pr-0">
                    <div class="custom-control custom-checkbox">
                      <input id="datatableCheckAll" type="checkbox" class="custom-control-input">
                      <label class="custom-control-label" for="datatableCheckAll"></label>
                    </div>
                  </th>
                  <th class="table-column-pl-0">Name</th>
                  <th>Status</th>
                </tr>
              </thead>

              <tbody>
                <c:forEach items="${categories}" var="cate" varStatus="STT">
	                <tr>
	                	<th scope="col" class="table-column-pr-0">
		                   <div class="custom-control custom-checkbox">
		                     <input id="datatableCheckAll" type="checkbox" class="custom-control-input">
		                     <label class="custom-control-label" for="datatableCheckAll"></label>
		                   </div>
		                 </th>
	                  <td>${cate.name }</td>

	                  <td>
						<c:if test="${cate.isActivated() }">Công khai</c:if>
						<c:if test="${!cate.isActivated() }">Ẩn</c:if>
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
                    <h4 id="inviteUserModalTitle" class="modal-title">Add Category</h4>

                    <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
                        <i class="tio-clear tio-lg"></i>
                    </button>
                </div>
                <!-- End Header -->
                <!-- Body -->
                <div class="modal-body">
                <form action="<c:url value="/admin/add-category"/>" method="post">

	                <input name="name" type="text" class="form-control mb-3" placeholder="Tên" />

				     <!-- Toggle Switch -->
	                <label class="row toggle-switch" for="availabilitySwitch1">
	                  <span class="col-8 col-sm-9 toggle-switch-content">
	                    <span class="text-dark">Công khai</span>
	                  </span>
	                  <span class="col-4 col-sm-3">
	                    <input name="isActivated" type="checkbox" class="toggle-switch-input" id="availabilitySwitch1" value="true" checked>
	                    <span class="toggle-switch-label ml-auto">
	                      <span class="toggle-switch-indicator"></span>
	                    </span>
	                  </span>
	                </label>
	                <!-- End Toggle Switch -->
	                
	                <div class="d-flex justify-content-center">
                  		<button type="submit" class="btn btn-outline-primary">Add</button>	                
	                </div>
				 </form>
                </div>
                <!-- End Body -->
            </div>
        </div>
    </div>
</body>