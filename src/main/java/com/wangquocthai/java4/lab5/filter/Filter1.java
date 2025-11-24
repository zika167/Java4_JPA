package com.wangquocthai.java4.lab5.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Bài 4: Filter1
 * - Set attribute "hello" = "Tôi là filter 1"
 */
public class Filter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // Set attribute vào request
        request.setAttribute("hello", "Tôi là filter 1");
        
        System.out.println("🔵 Filter1 BEFORE chain.doFilter() - URI: " + httpRequest.getRequestURI());
        System.out.println("   → Set attribute: hello = 'Tôi là filter 1'");
        
        // Cho phép request tiếp tục đến filter tiếp theo hoặc servlet
        chain.doFilter(request, response);
        
        System.out.println("🔵 Filter1 AFTER chain.doFilter()");
    }
}
