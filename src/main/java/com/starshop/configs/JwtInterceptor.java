package com.starshop.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Lấy JWT từ cookie
		String jwt = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("jwt".equals(cookie.getName())) {
					jwt = cookie.getValue();
					break;
				}
			}
		}

		// Nếu tìm thấy JWT, đóng gói request với header Authorization
		if (jwt != null) {
			CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper(request);
			wrappedRequest.addHeader("Authorization", "Bearer " + jwt);

			// Tiếp tục chuỗi xử lý với wrappedRequest
			request.setAttribute("wrappedRequest", wrappedRequest);
		}

		return true;
	}
}