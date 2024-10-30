<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<title>Cập nhật sản phẩm</title>

<body>
	<!-- Page Header -->
    <div class="page-header">
      <div class="row align-items-center">
        <div class="col-sm mb-2 mb-sm-0">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-no-gutter">
              <li class="breadcrumb-item"><a class="breadcrumb-link" href="<c:url value="/admin/product"/>">Products</a></li>
              <li class="breadcrumb-item active" aria-current="page">Update product</li>
            </ol>
          </nav>

          <h1 class="page-header-title">Cập nhật sản phẩm</h1>
        </div>
      </div>
      <!-- End Row -->
    </div>
    <!-- End Page Header -->
	
	  <form action="<c:url value="/admin/products/save"/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${product.id}" />
        <div class="row">
          <div class="col-lg-8">
            <!-- Card -->
            <div class="card mb-3 mb-lg-5">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">Thông tin sản phẩm</h4>
              </div>
              <!-- End Header -->

              <!-- Body -->
              <div class="card-body">
                <!-- Form Group -->
                <div class="form-group">
                	<label for="productNameLabel" class="input-label">Tên</label>
                  <input type="text" class="form-control" name="name" value="${product.name }" id="productNameLabel" placeholder="" required>
                </div>
                <!-- End Form Group -->

                <div class="row">
                  <div class="col-sm-6">
                    <!-- Form Group -->
                  <div class="form-group">
                    <label for="SKULabel" class="input-label">Số lượng</label>

                    <input type="number" class="form-control" name="currentQuantity" value="${product.currentQuantity }" id="SKULabel" value="0" placeholder="" aria-label="">
                  </div>
                  <!-- End Form Group -->
                  </div>

                  <div class="col-sm-6">
                    <!-- Form Group -->
                    <div class="form-group">
                      <label for="weightLabel" class="input-label">Khối lượng (kg)</label>
      
                      <div class="input-group input-group-merge">
                        <input type="text" class="form-control" name="weight" value="${product.weight }" id="weightLabel" placeholder="0.0" aria-label="0.0" required>
                      </div>
                      
                      <small class="form-text">Dùng để tính phí vận chuyển.</small>
                    </div>
                    <!-- End Form Group -->
                  </div>
                </div>
                <!-- End Row -->
                
                <label class="input-label">Mô tả <span class="input-label-secondary">(Optional)</span></label>
 				<textarea class="form-control" name="description" aria-label="With textarea"></textarea>
              </div>
              <!-- Body -->
            </div>
            <!-- End Card -->

            <!-- Card -->
            <div class="card mb-3 mb-lg-5">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">Ảnh</h4>

                <!-- Unfold -->
                <div class="hs-unfold">
                	<a class="btn btn-soft-info" data-toggle="modal" data-target="#addImageFromURLModal">
                		<i class="tio-link dropdown-item-icon"></i> Thêm ảnh bằng URL</a>
                </div>
                <!-- End Unfold -->
              </div>
              <!-- End Header -->

              <!-- Body -->
              <div class="card-body">
                <!-- Dropzone -->
                <div id="attachFilesNewProjectLabel" class="dropzone-custom custom-file-boxed">
                  <div class="dz-message custom-file-boxed-label">
                  	<c:if test="${product.image.substring(0,5)=='https'}">
						<c:url value="${product.image }" var="imgUrl"></c:url>
					  </c:if>
					  <c:if test="${product.image.substring(0,5)!='https'}">
						<c:url value="/img_product/${product.image }" var="imgUrl"></c:url>
					  </c:if>
                    <img class="avatar avatar-xl avatar-4by3 mb-3" src="${imgUrl }" alt="Image Description">
<!--                     <span class="btn btn-sm btn-primary">Browse files</span>
 -->                    <input type="file" name="file"  value="${product.image }" class="form-control form-control-borderless form-control-flush">
                  </div>
                </div>
                <!-- End Dropzone -->
              </div>
              <!-- Body -->
            </div>
            <!-- End Card -->

			<!-- variants -->
          </div>

          <div class="col-lg-4">
            <!-- Card -->
            <div class="card mb-3 mb-lg-5">

              <!-- Body -->
              <div class="card-body">
                <!-- Form Group -->
                <div class="form-group">
                  <label for="withAppendLabel" class="input-label">Giá vốn</label>
  
                  <div class="input-group">
				   <input name="costPrice"  value="${product.costPrice }" required type="text" class="form-control" id="withAppendLabel" placeholder="" aria-describedby="basic-addon2">
				   <div class="input-group-append">
				     <span class="input-group-text" id="basic-addon2">VND</span>
				   </div>
				 </div>
                </div>
                <!-- End Form Group -->

                
                <!-- Form Group -->
                <div class="form-group">
                  <label for="withAppendLabel" class="input-label">Giá bán</label>
  
                  <div class="input-group">
				   <input name="salePrice" value="${product.salePrice }" required type="text" class="form-control" id="withAppendLabel" placeholder="" aria-describedby="basic-addon2">
				   <div class="input-group-append">
				     <span class="input-group-text" id="basic-addon2">VND</span>
				   </div>
				 </div>
                </div>
                <!-- End Form Group -->

                
                <!-- Form Group -->
                <div class="form-group">
                  <label for="withAppendLabel" class="input-label">Giảm giá</label>
  
                  <div class="input-group">
				   <input name="discountPercent" value="${product.discountPercent }" type="text" class="form-control" id="withAppendLabel" placeholder="10" aria-describedby="basic-addon2">
				   <div class="input-group-append">
				     <span class="input-group-text" id="basic-addon2">%</span>
				   </div>
				 </div>
                </div>
                <!-- End Form Group -->

                <hr class="my-4">

                <!-- Toggle Switch -->
                <label class="row toggle-switch" for="availabilitySwitch1">
                  <span class="col-8 col-sm-9 toggle-switch-content">
                    <span class="text-dark">Công khai</span>
                  </span>
                  <span class="col-4 col-sm-3">
                    <input name="published" value="true" <c:if test="${product.isPublished() }">checked</c:if> type="checkbox" class="toggle-switch-input" id="availabilitySwitch1">
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

            <!-- Card -->
            <div class="card">
              <!-- Header -->
              <div class="card-header">
                <h4 class="card-header-title">Danh mục</h4>
              </div>
              <!-- End Header -->

              <!-- Body -->
              <div class="card-body">

                <!-- Form Group --> 
				<div class="form-group">                  
				   <c:forEach items="${categories}" var="cate" varStatus="STT">
				      <!-- Form Check -->
				      <div class="form-check form-check-inline">
				         <div class="custom-control custom-checkbox">
				            <input 
				               type="checkbox" 
				               name="categoryIds" 
				               value="${cate.id}" 
				               id="customInlineCheck${cate.id}" 
				               class="custom-control-input"
				               <c:if test="${product.categories.contains(cate)}">checked</c:if> 
				            >
				            <label class="custom-control-label" for="customInlineCheck${cate.id}">${cate.name}</label>
				         </div>
				      </div>
				      <!-- End Form Check -->
				   </c:forEach>
				</div>
				<!-- Form Group -->
				                

              </div>
              <!-- End Body -->
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
                  <a type="button" href="<c:url value="/admin/products"/>" class="btn btn-ghost-light mr-2">Discard</a>
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
            <input type="text" class="form-control" name="image" value="${product.image }" id="pasteImageURLNameLabel" placeholder="https://" aria-label="https://">
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