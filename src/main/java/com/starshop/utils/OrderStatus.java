package com.starshop.utils;

public enum OrderStatus {
	PENDING, // chờ xác nhận
	PROCESSING, // đang chuẩn bị hàng
	READYFORSHIP, // chờ shipper lấy hàng
	SHIPPING, // đang giao hàng
	DELIVERED, // đã giao thành công
	CANCELLED, // đã hủy
	SHIPFAILED // giao thất bại
}