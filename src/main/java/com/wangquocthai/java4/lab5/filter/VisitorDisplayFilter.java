package com.wangquocthai.java4.lab5.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/lab5/*")
public class VisitorDisplayFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ServletContext context = httpRequest.getServletContext();
        
        // Lấy số đếm từ application scope và đặt vào request để JSP có thể truy cập
        Integer visitors = (Integer) context.getAttribute("visitors");
        if (visitors == null) {
            visitors = 0;
        }
        request.setAttribute("visitorCount", visitors);
        
        // Tiếp tục xử lý request
        chain.doFilter(request, response);
    }
}
