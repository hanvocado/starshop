package com.starshop.utils;

public enum OrderStatus {
	PENDING("Đặt hàng thành công"),
	PREPARING("Shop đang chuẩn bị hàng"),
	READY_FOR_SHIP("Chờ shipper lấy hàng"),
	SHIPPING("Đang giao hàng"),
	DELIVERED("Giao hàng thành công"),
	CANCELLED("Hủy đơn thành công"),
	SHIP_FAILED("Giao hàng thất bại");
	
	private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}