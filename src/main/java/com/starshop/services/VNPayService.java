package com.starshop.services;

import java.net.http.HttpRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface VNPayService {

	int orderReturn(HttpServletRequest request);

	String createOrder(int total, String orderInfor, String urlReturn, HttpServletRequest request);

}
