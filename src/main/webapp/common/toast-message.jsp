<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<ul class="notifications">
<li>
<div class="column">
                         <i class="fa-solid fa-circle-info"></i>
                         <span>kkkfffffffffffff</span>
                      </div>
                      <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
</li></ul>

<script>
    document.addEventListener("DOMContentLoaded", function() {
    	const notifications = document.querySelector(".notifications");
        if (!notifications) {
            console.error(".notifications không tồn tại trong DOM.");
            return;
        }
        const messageType = "${not empty message ? message.type : ''}";
        const messageContent = "${not empty message ? message.content : ''}";

        // Nếu messageType và messageContent hợp lệ, tạo thông báo Toast
        if (messageType && messageContent) {
            if (messageType === 'success') {
                createToast('success', messageContent);
            } else if (messageType === 'danger') {
                createToast('error', messageContent);
            } else if (messageType === 'info') {
                createToast('info', messageContent);
            } else if (messageType === 'warning') {
                createToast('warning', messageContent);
            }
        }
    });
    
</script>


<!-- JS Toast Message -->
<script src="/shop/js/toast-message.js"></script>

