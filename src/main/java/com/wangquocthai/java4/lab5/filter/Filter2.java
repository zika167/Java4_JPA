package com.wangquocthai.java4.lab5.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Bài 4: Filter2
 * - In ra giá trị của attribute "hello" từ Filter1
 */
public class Filter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // Lấy attribute từ request (đã được set bởi Filter1)
        String helloValue = (String) request.getAttribute("hello");
        
        System.out.println("🟢 Filter2 BEFORE chain.doFilter() - URI: " + httpRequest.getRequestURI());
        System.out.println("   → Get attribute: hello = '" + helloValue + "'");
        
        // Cho phép request tiếp tục đến servlet/JSP
        chain.doFilter(request, response);
        
        System.out.println("🟢 Filter2 AFTER chain.doFilter()");
    }
}
