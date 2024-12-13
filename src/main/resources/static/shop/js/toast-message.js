// Hàm tạo thông báo Toast
const notifications = document.querySelector(".notifications");
const createToast = (type, content) => {
	const toastDetails = {
		timer: 5000,
		success: { icon: 'fa-circle-check', text: content || 'Success: This is a success toast.' },
		error: { icon: 'fa-circle-xmark', text: content || 'Error: This is an error toast.' },
		warning: { icon: 'fa-triangle-exclamation', text: content || 'Warning: This is a warning toast.' },
		info: { icon: 'fa-circle-info', text: content || 'Info: This is an information toast.' }
	};

	// Lấy icon và nội dung từ đối tượng toastDetails
	const { icon, text } = toastDetails[type];

	// Tạo phần tử toast
	const toast = document.createElement("li");
	toast.className = `toast ${type}`;  // Áp dụng class cho toast

	// Đặt nội dung HTML cho toast
	toast.innerHTML = `<div class="column">
                         <i class="fa-solid ${icon}"></i>
                         <span>${text}</span>
                      </div>
                      <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>`;

	// Thêm toast vào phần tử notifications
	notifications.appendChild(toast);

	// Đặt thời gian để tự động ẩn toast sau 5 giây
	toast.timeoutId = setTimeout(() => removeToast(toast), toastDetails.timer);  // Remove after 5 seconds
};

// Hàm để ẩn toast khi người dùng nhấn vào biểu tượng x
const removeToast = (toast) => {
	toast.classList.add("hide");
	if (toast.timeoutId) clearTimeout(toast.timeoutId); // Hủy bỏ timeout của toast
	setTimeout(() => toast.remove(), 500); // Xóa toast sau 500ms
};

