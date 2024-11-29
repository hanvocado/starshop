<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

   <!-- Voucher Modal -->
    <div id="voucherModal" class="fixed inset-0 bg-black bg-opacity-50 hidden">
        <div class="fixed inset-0 flex items-center justify-center">
            <div class="bg-white rounded-lg shadow-lg p-6 modal-content" style="max-height: 80vh; overflow-y: auto; width: 600px;">
                <h2 class="text-lg font-semibold text-gray-800 text-center">Chọn Voucher</h2>
                
                <!-- Voucher Code Input -->
                <div class="mt-3 mx-2">
                    <label class="block text-md font-semibold text-gray-600 mb-2">Mã Voucher</label>
                    <div class="row">
                    	<div class="col-lg-9 mr-0">
                    		<input id="voucherCodeInput" type="text" class="p-2 border border-gray-300 rounded w-full bg-white" placeholder="Nhập mã voucher" />
                    	</div>
                    	<div class="col-lg-3 ml-0 d-flex justify-content-end">
                    		<button class="btn btn-primary" onclick="applyVoucherCode()">Áp dụng</button>
                    	</div>
                    </div>
                </div>

                <!-- Free Shipping Vouchers -->
                <div class="mx-2 my-3">
                	<h3 class="text-md font-semibold text-gray-600 mb-2">Mã Miễn Phí Vận Chuyển</h3>
                    <c:forEach items="${freeShipVouchers}" var="voucher">
                        <div class="flex items-center justify-between p-2 border border-gray-300 rounded bg-white <c:if test='${grandTotal < voucher.minOrderItemsTotal}'>voucher-disabled</c:if>'">
                            <div>
                                <span class="text-primary font-bold">FREE SHIP</span>
                                <p class="text-sm text-gray-700">${voucher.description}</p>
                                <p class="text-xs text-gray-500">HSD: ${voucher.getFormattedExpiredAt()}</p>
                                <c:if test="${grandTotal < voucher.minOrderItemsTotal}">
                                    <p class="text-danger">Giá trị đơn hàng không đủ</p>
                                </c:if>
                            </div>
                            <input type="radio" name="voucherCode" value="${voucher.code}" <c:if test='${grandTotal < voucher.minOrderItemsTotal}'>disabled</c:if>/>
                        </div>
                    </c:forEach>
                </div>
                <div class="mx-2 xy-3">
                    <!-- Discount Vouchers -->
                    <h3 class="text-md font-semibold text-gray-600 mb-2">Mã Giảm Giá</h3>
                    <div class="space-y-2">
                        <c:forEach items="${discountVouchers}" var="voucher">
                            <div class="flex items-center justify-between p-2 border border-gray-300 rounded bg-white <c:if test='${grandTotal < voucher.minOrderItemsTotal}'>voucher-disabled</c:if>'">
                                <div>
                                    <span class="text-primary font-bold">${voucher.discountPercent}% OFF</span>
                                    <p class="text-sm text-gray-700">${voucher.description}</p>
                                    <p class="text-xs text-gray-500">HSD: ${voucher.getFormattedExpiredAt()}</p>
                                    <c:if test="${grandTotal < voucher.minOrderItemsTotal}">
                                        <p class="text-danger">Giá trị đơn hàng không đủ</p>
                                    </c:if>
                                </div>
                                <input type="radio" name="voucherCode" value="${voucher.code}" <c:if test='${grandTotal < voucher.minOrderItemsTotal}'>disabled</c:if>/>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- Modal Footer -->
                <div class="flex justify-between mt-4">
                    <button 
                        class="bg-gray-200 text-gray-800 hover:bg-gray-300 p-2 rounded"
                        onclick="toggleModal()">
                        Trở lại
                    </button>
                    <button class="bg-secondary text-secondary-foreground hover:bg-secondary/80 p-2 rounded" onclick="submitVoucher()">OK</button>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.tailwindcss.com?plugins=forms,typography"></script>
    <style type="text/tailwindcss">
        @layer base {
            :root {
                --background: 0 0% 100%;
                --foreground: 240 10% 3.9%;
                --card: 0 0% 100%;
                --card-foreground: 240 10% 3.9%;
                --popover: 0 0% 100%;
                --popover-foreground: 240 10% 3.9%;
                --primary: 240 5.9% 10%;
                --primary-foreground: 0 0% 98%;
                --secondary: 240 4.8% 95.9%;
                --secondary-foreground: 240 5.9% 10%;
                --muted: 240 4.8% 95.9%;
                --muted-foreground: 240 3.8% 46.1%;
                --destructive: 0 84.2% 60.2%;
                --destructive-foreground: 0 0% 98%;
                --border: 240 5.9% 90%;
                --input: 240 5.9% 90%;
                --ring: 240 5.9% 10%;
                --radius: 0.5rem;
            }
        }
    </style>
    <script>
        // JavaScript to toggle modal visibility
        function toggleModal() {
            const modal = document.getElementById('voucherModal');
            modal.classList.toggle('hidden'); 
        }
        
        function applyVoucherCode() {
        	const voucherCode = document.getElementById('voucherCodeInput').value.trim();
            const totalPriceNotApply = parseFloat(document.getElementById('total-price').innerText.replace('đ', '').replace(/,/g, ''));
            
            if (!voucherCode) {
                alert("Vui lòng nhập mã voucher!");
                return;
            }
            
            const voucherRequest = {
                    voucherCode: voucherCode,
                    totalPriceNotApply: totalPriceNotApply
                };

            fetch('/apply-voucher', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(voucherRequest), 
            })
            .then(response => response.text()) 
            .then(message => {
                alert(message);
                const newTotalPrice = parseFloat(message.split(": ")[1].replace(/,/g, ''));
                document.getElementById('total-price').innerText = 'đ' + newTotalPrice.toLocaleString('vi-VN');
                toggleModal();
            })
            .catch(error => console.error('Error:', error));
        }

        // Gửi mã voucher được chọn
        function submitVoucher() {
		    const selectedVoucher = document.querySelector('input[name="voucherCode"]:checked');
		
		    if (!selectedVoucher) {
		        alert("Vui lòng chọn một mã voucher!");
		        return;
		    }
		
		    const voucherCode = selectedVoucher.value;
		    const totalPrice = parseFloat(document.getElementById('total-price').innerText.replace('đ', '').replace(/,/g, ''));
		
		    const voucherRequest = {
		        voucherCode: voucherCode,
		        totalPrice: totalPrice
		    };
		
		    fetch('/apply-voucher', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/json',
		        },
		        body: JSON.stringify(voucherRequest),  
		    })
		    .then(response => response.text()) 
		    .then(message => {
		        alert(message);  
				const newTotalPrice = parseFloat(message.split(": ")[1].replace(/,/g, ''));
		        document.getElementById('total-price').innerText = 'đ' + newTotalPrice.toLocaleString('vi-VN');
		        toggleModal(); 
		    })
		    .catch(error => console.error('Error:', error));
		}
    </script>