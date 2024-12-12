package com.starshop.services;

import jakarta.servlet.http.HttpServletRequest;

public interface VNPayService {

	int orderReturn(HttpServletRequest request);

	String createOrder(int total, String orderInfor, String urlReturn);

}
