<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<title>Tạo phiếu giả giám</title>

<body>
	<!-- Page Header -->
    <div class="page-header">
      <div class="row align-items-center">
        <div class="col-sm mb-2 mb-sm-0">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-no-gutter">
              <li class="breadcrumb-item"><a class="breadcrumb-link" href="<c:url value="/admin/voucher"/>">Vouchers</a></li>
              <li class="breadcrumb-item active" aria-current="page">Add voucher</li>
            </ol>
          </nav>

          <h1 class="page-header-title">Tạo phiếu giảm giá</h1>
        </div>
      </div>
      <!-- End Row -->
    </div>
    <!-- End Page Header -->
	
	  <form action="<c:url value="/admin/vouchers/save"/>" method="post" enctype="multipart/form-data">
        <div class="row">
          <div class="col-lg-8">
            <!-- Card -->
            <div class="card mb-3 mb-lg-5">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">Thông tin phiếu giảm giá</h4>
              </div>
              <!-- End Header -->

              <!-- Body -->
              <div class="card-body">
                <!-- Form Group -->
                <div class="form-group">
                	<label for="voucherNameLabel" class="input-label">Tên</label>
                  <input type="text" class="form-control" name="name" id="voucherNameLabel" placeholder="" required>
                </div>
                <!-- End Form Group -->

				<div class="row">
                  <div class="col-sm-6">
	                <!-- Form Group -->
	                <div class="form-group">
	                	<label for="voucherNameLabel" class="input-label">Code</label>
	                  <input type="text" class="form-control" name="code" id="voucherNameLabel" placeholder="20102024" required>
	                </div>
	                <!-- End Form Group -->
	               </div>
	               <div class="col-sm-6">
	                <!-- Form Group -->
	                <div class="form-group">
	                	<label for="voucherNameLabel" class="input-label">Hết hạn vào</label>
						<input name="expiredAt" type="text" class="form-control" placeholder="31/12/2024 23:59:59" required/>	                
					</div>
	                <!-- End Form Group -->
	               </div>
	             </div>
                
                <label class="input-label">Mô tả <span class="input-label-secondary">(Optional)</span></label>
 				<textarea class="form-control" name="description" aria-label="With textarea"></textarea>
              </div>
              <!-- Body -->
            </div>
            <!-- End Card -->


          </div>

          <div class="col-lg-4">
            <!-- Card -->
            <div class="card mb-3 mb-lg-5">

              <!-- Body -->
              <div class="card-body">
                <!-- Form Group -->
                <div class="form-group">
                  <label for="withAppendLabel" class="input-label">Phần trăm giảm giá</label>
  
                  <div class="input-group">
				   <input name="discountPercent" required type="text" value="5" class="form-control" id="withAppendLabel" placeholder="" aria-describedby="basic-addon2">
				   <div class="input-group-append">
				     <span class="input-group-text" id="basic-addon2">%</span>
				   </div>
				 </div>
                </div>
                <!-- End Form Group -->

                
                <!-- Form Group -->
                <div class="form-group">
                  <label for="withAppendLabel" class="input-label">Giảm tối đa</label>
  
                  <div class="input-group">
				   <input name="maxDiscountAmount" required type="number" value="20000" class="form-control" id="withAppendLabel" placeholder="" aria-describedby="basic-addon2">
				   <div class="input-group-append">
				     <span class="input-group-text" id="basic-addon2">VND</span>
				   </div>
				 </div>
                </div>
                <!-- End Form Group -->

                
                <!-- Form Group -->
                <div class="form-group">
                  <label for="withAppendLabel" class="input-label">Đơn tối thiểu</label>
  
                  <div class="input-group">
				   <input name="minOrderItemsTotal" value="0" type="text" class="form-control" id="withAppendLabel" placeholder="10" aria-describedby="basic-addon2">
				   <div class="input-group-append">
				     <span class="input-group-text" id="basic-addon2">VND</span>
				   </div>
				 </div>
                </div>
                <!-- End Form Group -->

                <hr class="my-4">

                <!-- Toggle Switch -->
                <label class="row toggle-switch" for="availabilitySwitch1">
                  <span class="col-8 col-sm-9 toggle-switch-content">
                    <span class="text-dark">Là phiếu miễn phí vận chuyển</span>
                  </span>
                  <span class="col-4 col-sm-3">
                    <input name="freeship" value="true" checked type="checkbox" class="toggle-switch-input" id="availabilitySwitch1">
                    <span class="toggle-switch-label ml-auto">
                      <span class="toggle-switch-indicator"></span>
                    </span>
                  </span>
                </label>
                <!-- End Toggle Switch -->
              </div>
              <!-- Body -->
            </div>
            <!-- End Card -->
          </div>
        </div>
        <!-- End Row -->
        
        <div class="position-fixed bottom-0 content-centered-x w-100 z-index-99 mb-3" style="max-width: 40rem;">
          <!-- Card -->
          <div class="card card-sm bg-dark border-dark mx-2">
            <div class="card-body">
              <div class="row justify-content-center justify-content-sm-between">
                <div class="col">
                  <button type="submit" class="btn btn-primary">Save</button>
                </div>
                <div class="col-auto">
                  <a type="button" href="<c:url value="/admin/voucher"/>" class="btn btn-ghost-light mr-2">Discard</a>
                </div>
              </div>
              <!-- End Row -->
            </div>
          </div>
          <!-- End Card -->
        </div>
        
	<!-- Add Image from URL Modal -->
    <div class="modal fade" id="addImageFromURLModal" tabindex="-1" role="dialog" aria-labelledby="addImageFromURLModalTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <!-- Header -->
          <div class="modal-header">
            <h4 id="addImageFromURLModalTitle" class="modal-title">Thêm ảnh bằng URL</h4>

            <button type="button" class="btn btn-icon btn-sm btn-ghost-secondary" data-dismiss="modal" aria-label="Close">
              <i class="tio-clear tio-lg"></i>
            </button>
          </div>
          <!-- End Header -->

          <!-- Body -->
          <div class="modal-body">
            <label for="pasteImageURLNameLabel" class="input-label">Paste image URL</label>
            <input type="text" class="form-control" name="image" id="pasteImageURLNameLabel" placeholder="https://" aria-label="https://">
          </div>
          <!-- End Body -->

          <!-- Footer -->
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal" aria-label="Close">OK</button>
          </div>
          <!-- End Footer -->
        </div>
      </div>
    </div>
    <!-- End Add Image from URL Modal -->
    
    </form>  
</body>