<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
    
<title>Địa chỉ của tôi</title>
    
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <h3>Địa chỉ của tôi</h3>
        </div>
        <div class="col-sm-4 text-right">
	        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#addAddressModal">
		        + Thêm địa chỉ mới
		    </button>
		</div>
    </div>
    <div class="clearfix"></div>
    <!-- Modal -->
	<div class="modal fade" id="addAddressModal" tabindex="-1" aria-labelledby="addAddressModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="addAddressModalLabel">Địa chỉ mới</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <form action="<c:url value='/customer/account/addresses/new' />" method="post">
	                    <!-- Trường Số nhà -->
	                    <div class="form-group">
	                        <label for="houseNumber">Số nhà</label>
	                        <input type="text" class="form-control" id="houseNumber" name="houseNumber" placeholder="Nhập số nhà">
	                    </div>
	                    
	                    <!-- Trường Tên đường -->
	                    <div class="form-group">
	                        <label for="street">Tên đường</label>
	                        <input type="text" class="form-control" id="street" name="street" placeholder="Nhập tên đường">
	                    </div>
	                    
	                    <!-- Trường Phường/Xã -->
	                    <div class="form-group">
	                        <label for="ward">Phường/Xã</label>
	                        <input type="text" class="form-control" id="ward" name="ward" placeholder="Nhập phường/xã">
	                    </div>
	                    
	                    <!-- Trường Quận/Huyện -->
	                    <div class="form-group">
	                        <label for="district">Quận/Huyện</label>
	                        <input type="text" class="form-control" id="district" name="district" placeholder="Nhập quận/huyện">
	                    </div>
	                    
	                    <!-- Trường Tỉnh/Thành phố -->
	                    <div class="form-group">
	                        <label for="city">Tỉnh/Thành phố</label>
	                        <input type="text" class="form-control" id="city" name="city" placeholder="Nhập tỉnh/thành phố">
	                    </div>
	                    <div class="modal-footer">
			                <button type="button" class="btn btn-secondary" data-dismiss="modal">Trở Lại</button>
			                <button type="submit" class="btn btn-danger">Hoàn Thành</button>
			            </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>

    <hr>

    <c:if test="${defaultAddress.isEmpty() && (otherAddresses == null || otherAddresses.isEmpty())}">
        <p class="text-center">Chưa có địa chỉ</p>
    </c:if>

    <!-- Địa chỉ mặc định -->
	<c:if test="${defaultAddress.isPresent()}">
	    <c:set var="address" value="${defaultAddress.get()}" />
	    <div class="panel panel-default">
	        <div class="panel-body">
	            <div class="row address-item">
	                <div class="col-sm-8">
	                    <h4><strong>Địa chỉ mặc định</strong></h4>
	                    <p><strong>${address.customer.lastName} ${address.customer.firstName}</strong> (${address.customer.phoneNumber})</p>
	                    <p>${address.houseNumber}, ${address.street}<br>
	                        ${address.ward}, ${address.district}, ${address.city}</p>
	                    <button class="btn btn-outline-danger btn-xs">Mặc định</button>
	                    <button class="btn btn-outline-dark btn-xs">Địa chỉ lấy hàng</button>
	                    <button class="btn btn-outline-dark btn-xs">Địa chỉ trả hàng</button>
	                </div>
	                <div class="col-sm-4 text-right">
	                  <div>
	                    <p>
                           <a href="#" class="btn btn-link" onclick="openEditAddressModal(${address.id}, '${address.houseNumber}', '${address.street}', '${address.ward}', '${address.district}', '${address.city}')">Cập nhật</a>
	                    </p>
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
	    <hr>
	</c:if>


<!-- Danh sách địa chỉ khác -->
<c:if test="${otherAddresses != null && !otherAddresses.isEmpty()}">
    <div class="panel panel-default">
        <div class="panel-body">
            <h4><strong>Các địa chỉ khác</strong></h4>
            <c:forEach var="address" items="${otherAddresses}">
                <div class="row address-item">
                    <div class="col-sm-8">
                        <p><strong>${address.customer.lastName} ${address.customer.firstName}</strong> (${address.customer.phoneNumber})</p>
                        <p>${address.houseNumber}, ${address.street}<br>
                            ${address.ward}, ${address.district}, ${address.city}</p>
                    </div>
                    <div class="col-sm-4 text-right">
                    	<div>
                        <p>
                           <a href="#" class="btn btn-link" onclick="openEditAddressModal(${address.id}, '${address.houseNumber}', '${address.street}', '${address.ward}', '${address.district}', '${address.city}')">Cập nhật</a>
                            <a href="#" onclick="confirmDelete('${address.id}')" class="btn btn-link text-danger">Xóa</a>
                        </p>
                        </div>
						<form action="<c:url value='/customer/account/addresses/set-default' />" method="post" style="display: inline;">
						    <input type="hidden" name="addressId" value="${address.id}" />
						    <button type="submit" class="btn btn-outline-dark btn-xs">Thiết lập mặc định</button>
						</form>
                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>
</c:if>

<!-- Modal cập nhật địa chỉ -->
<div class="modal fade" id="editAddressModal" tabindex="-1" aria-labelledby="editAddressModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editAddressModalLabel">Cập nhật địa chỉ</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editAddressForm" action="<c:url value='/customer/account/addresses/update' />" method="post">
                    <input type="hidden" id="editAddressId" name="id">

                    <!-- Trường Số nhà -->
                    <div class="form-group">
                        <label for="editHouseNumber">Số nhà</label>
                        <input type="text" class="form-control" id="editHouseNumber" name="houseNumber" placeholder="Nhập số nhà">
                    </div>

                    <!-- Trường Tên đường -->
                    <div class="form-group">
                        <label for="editStreet">Tên đường</label>
                        <input type="text" class="form-control" id="editStreet" name="street" placeholder="Nhập tên đường">
                    </div>

                    <!-- Trường Phường/Xã -->
                    <div class="form-group">
                        <label for="editWard">Phường/Xã</label>
                        <input type="text" class="form-control" id="editWard" name="ward" placeholder="Nhập phường/xã">
                    </div>

                    <!-- Trường Quận/Huyện -->
                    <div class="form-group">
                        <label for="editDistrict">Quận/Huyện</label>
                        <input type="text" class="form-control" id="editDistrict" name="district" placeholder="Nhập quận/huyện">
                    </div>

                    <!-- Trường Tỉnh/Thành phố -->
                    <div class="form-group">
                        <label for="editCity">Tỉnh/Thành phố</label>
                        <input type="text" class="form-control" id="editCity" name="city" placeholder="Nhập tỉnh/thành phố">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function openEditAddressModal(id, houseNumber, street, ward, district, city) {
        // Set giá trị vào form trong modal
        document.getElementById('editAddressId').value = id;
        document.getElementById('editHouseNumber').value = houseNumber;
        document.getElementById('editStreet').value = street;
        document.getElementById('editWard').value = ward;
        document.getElementById('editDistrict').value = district;
        document.getElementById('editCity').value = city;

        // Hiển thị modal
        $('#editAddressModal').modal('show');
    }
</script>

<script>
    function confirmDelete(addressId) {
        if (confirm("Bạn có chắc chắn muốn xóa địa chỉ này không?")) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = `<c:url value='/customer/account/addresses/delete' />`;
            
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'id';
            input.value = addressId;
            
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        }
    }
</script>

</div>
</body>

