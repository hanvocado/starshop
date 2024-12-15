<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<title>${type } phiếu giả giám</title>

<body>
	<!-- Page Header -->
    <div class="page-header">
      <div class="row align-items-center">
        <div class="col-sm mb-2 mb-sm-0">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-no-gutter">
              <li class="breadcrumb-item"><a class="breadcrumb-link" href="<c:url value="/admin/vouchers"/>">Vouchers</a></li>
              <li class="breadcrumb-item active" aria-current="page">${type } voucher</li>
            </ol>
          </nav>

          <h1 class="page-header-title">${type } phiếu giảm giá</h1>
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
                  <input type="text" class="form-control" name="name" value="${voucher.name }" id="voucherNameLabel" placeholder="" required>
                </div>
                <!-- End Form Group -->

				<div class="row">
                  <div class="col-sm-6">
	                <!-- Form Group -->
	                <div class="form-group">
	                	<label for="voucherNameLabel" class="input-label">Code</label>
	                  <input type="text" class="form-control" name="code" value="${voucher.code }" id="voucherNameLabel" placeholder="20102024" required>
	                </div>
	                <!-- End Form Group -->
	               </div>
	               <div class="col-sm-6">
	                <!-- Form Group -->
	                <div class="form-group">
	                	<label for="voucherNameLabel" class="input-label">Hết hạn vào</label>
						<input name="expiredAt" value="${voucher.formattedExpiredAt }" type="text" class="form-control" placeholder="31/12/2024 23:59:59" pattern="\d{2}/\d{2}/\d{4} \d{2}:\d{2}:\d{2}" required/>	                
					</div>
	                <!-- End Form Group -->
	               </div>
	             </div>
                
                <label class="input-label">Mô tả <span class="input-label-secondary">(Optional)</span></label>
 				<textarea class="form-control" name="description" value="${voucher.description }" aria-label="With textarea"></textarea>
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
				   <input name="discountPercent" value="${voucher.discountPercent }" required type="number" class="form-control" id="withAppendLabel" placeholder="" aria-describedby="basic-addon2">
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
				   <input name="maxDiscountAmount" value="${voucher.maxDiscountAmount }" required type="number" value="20000" class="form-control" id="withAppendLabel" placeholder="" aria-describedby="basic-addon2">
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
				   <input name="minOrderItemsTotal" value="${voucher.minOrderItemsTotal }" required type="number" class="form-control" id="withAppendLabel" placeholder="10" aria-describedby="basic-addon2">
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
                    <input name="freeship" value="true" <c:if test="${voucher.isFreeship() }">checked</c:if> type="checkbox" class="toggle-switch-input" id="availabilitySwitch1">
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
    
    </form>  
</body>