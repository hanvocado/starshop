<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/shop/js/bootoast.min.js"></script>

<script>
    // Hàm xử lý yêu cầu AJAX
    function handleAjaxRequest(url, message) {
    	const bootoast = window.Bootoast;
        $.ajax({
            url: url,
            method: 'POST',
            success: function (response) {
                bootoast.toast({
                    message: response.content,
                    type: response.type, 
                    position: 'top-right',
                    timeout: 3,
                    dismissable: true,
                });
            },
            error: function (xhr) {
                bootoast.toast({
                    message: message || 'Có lỗi xảy ra. Vui lòng thử lại.',
                    type: 'danger',
                    position: 'top-right',
                    timeout: 3,
                    dismissable: true,
                });
            }
        });
    }

    // Xử lý sự kiện thêm vào wishlist
    $(document).on('click', '.add-to-wishlist', function (e) {
        e.preventDefault();
        const productId = $(this).data('product-id'); 
        const url = '/customer/add-wishlist/' + productId; 

        handleAjaxRequest(url, 'Có lỗi xảy ra khi thêm vào wishlist.');
    });

    // Xử lý sự kiện thêm vào giỏ hàng
    $(document).on('click', '.add-to-cart', function (e) {
        e.preventDefault();
        const productId = $(this).data('product-id'); 
        const url = '/customer/cart/add/' + productId; 
        const bootoast = window.Bootoast;

        handleAjaxRequest(url, 'Có lỗi xảy ra khi thêm vào giỏ hàng.');
    });
</script>


<c:if test="${not empty message}">
    <script>
        const toastMessage = "${message.content}";
        const toastType = "${message.type}";
    </script>
</c:if>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        if (typeof toastMessage !== 'undefined' && typeof toastType !== 'undefined') {
        	const bootoast = window.Bootoast;
            bootoast.toast({
                message: toastMessage,
                type: toastType,
                position: "top-right",
                timeout: 3,
                animationDuration: 300,
                dismissable: true
            });
        }
    });
</script>


