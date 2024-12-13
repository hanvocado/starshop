<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<%@include file="/common/flash-message.jsp"%>

<c:if test="${not empty result}">
    <script>
        window.onload = function() {
            if (${showModal}) {
                toggleModal(); 
            }
        }
    </script>
</c:if>

<form action="/customer/cart/apply-voucher" method="post">
	<input type="hidden" name="totalPrice" id="totalPriceNotVoucher" />
   <!-- Voucher Modal -->
    <div id="voucherModal" tabindex="-1" class="fixed inset-0 bg-black bg-opacity-50 hidden">
        <div class="fixed inset-0 flex items-center justify-center">
            <div class="bg-white rounded-lg shadow-lg p-6 modal-content" style="max-height: 80vh; overflow-y: auto; width: 600px;">
                <h2 class="text-lg font-semibold text-gray-800 text-center">Chọn Voucher</h2>
                <div id="product-selection-message" class="color__main hidden">
				    Vui lòng chọn một sản phẩm
				</div>
				<hr class="mt-1"/>
				 
                <!-- Voucher Code Input -->
              <div class="modal-body p-3 overflow-auto" style="max-height: calc(80vh - 70px);">
                <div class="mt-3 mx-2">
                    <label class="block text-md font-semibold text-gray-600 mb-2">Mã Voucher</label>
                    <div class="row">
                    	<div class="col-lg-9 mr-0">
                    		<input id="voucherCodeInput" name="voucherCodeInput" type="text" class="p-2 border border-gray-300 rounded w-full bg-white" placeholder="Nhập mã voucher" />
                    	</div>
                    	<div class="col-lg-3 ml-0 d-flex justify-content-end">
                    		<button id="inputVoucherButton" type="submit" class="fs__button btn">Áp dụng</button>
                    	</div>
                    </div>
                </div>

                <!-- Free Shipping Vouchers -->
                <div class="mx-2 my-3">
                	<h3 class="text-md font-semibold text-gray-600 mb-2">Mã Miễn Phí Vận Chuyển</h3>
                    <c:forEach items="${freeShipVouchers}" var="voucher">
                        <div class="flex items-center justify-between p-2 border border-gray-300 rounded bg-white <c:if test='${productLinePrice < voucher.minOrderItemsTotal}'>voucher-disabled</c:if>'">
                            <div>
                                <span class="text-primary font-bold">FREE SHIP</span>
                                <p class="text-sm text-gray-700">${voucher.description}</p>
                                <p class="text-xs text-gray-500">HSD: ${voucher.getFormattedExpiredAt()}</p>
                            </div>
                            <input type="radio" name="voucherCode" value="${voucher.code}" <c:if test='${productLinePrice < voucher.minOrderItemsTotal}'>disabled</c:if>/>
                        </div>
                    </c:forEach>
                </div>
                <div class="mx-2 xy-3">
                    <!-- Discount Vouchers -->
                    <h3 class="text-md font-semibold text-gray-600 mb-2">Mã Giảm Giá</h3>
                    <div class="space-y-2">
                        <c:forEach items="${discountVouchers}" var="voucher">
                            <div class="flex items-center justify-between p-2 border border-gray-300 rounded bg-white <c:if test='${productLinePrice < voucher.minOrderItemsTotal || totalPrice == 0 }'>voucher-disabled</c:if>'">
                                <div>
                                    <span class="text-primary font-bold">${voucher.discountPercent}% OFF</span>
                                    <p class="text-sm text-gray-700">${voucher.description}</p>
                                    <p class="text-xs text-gray-500">HSD: ${voucher.getFormattedExpiredAt()}</p>
                                </div>
                                <input type="radio" name="voucherCode" value="${voucher.code}" <c:if test='${productLinePrice < voucher.minOrderItemsTotal}'>disabled</c:if>/>
                            </div>
                        </c:forEach>
                        <div id="minPriceMessage"></div>
                    </div>
                </div>
              </div>
                <!-- Modal Footer -->
                <div class="modal-footer d-flex justify-content-between bg-light border-top">
				    <button 
				        type="button"
				        class="btn btn-secondary"
				        onclick="toggleModal()">
				        Trở lại
				    </button>
				
				    <button type="submit" id="applyVoucherButton" class="fs__button custom-btn btn w-max ms-auto">
				        OK
				    </button>
				</div>
            </div>
        </div>
    </div>
</form>
    
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

        /* Thiết lập modal */
        #modal {
            @apply fixed inset-0 z-[9999]; /* Đặt modal toàn màn hình và z-index cao nhất */
            background-color: rgba(0, 0, 0, 0.5); /* Nền mờ */
            display: flex; /* Hiển thị flex để căn giữa nội dung */
            justify-content: center;
            align-items: center;
        }

        #modal .modal-content {
            @apply bg-white rounded-lg shadow-lg p-6 w-full max-w-lg; /* Thiết kế nội dung modal */
        }
    }
</style>

    <script>
        // JavaScript to toggle modal visibility
        function toggleModal() {
            const modal = document.getElementById('voucherModal');
            modal.classList.toggle('hidden'); 
        }
    </script>