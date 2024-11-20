package com.starshop.configs;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String> customHeaders;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<>();
    }

    public void addHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String headerValue = customHeaders.get(name);
        return headerValue != null ? headerValue : super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Map<String, String> allHeaders = new HashMap<>();
        allHeaders.putAll(customHeaders);

        Enumeration<String> originalHeaders = super.getHeaderNames();
        while (originalHeaders.hasMoreElements()) {
            String headerName = originalHeaders.nextElement();
            allHeaders.putIfAbsent(headerName, super.getHeader(headerName));
        }

        return Collections.enumeration(allHeaders.keySet());
    }
}
