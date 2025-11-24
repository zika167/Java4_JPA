package com.wangquocthai.java4.lab5.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/lab5/filter-demo/test")
public class FilterDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("🎯 Servlet đang xử lý request");
        
        // Lấy attribute từ Filter1
        String helloValue = (String) req.getAttribute("hello");
        req.setAttribute("messageFromFilter", helloValue);
        
        req.getRequestDispatcher("/lab5/views/filter-demo.jsp").forward(req, resp);
    }
}
