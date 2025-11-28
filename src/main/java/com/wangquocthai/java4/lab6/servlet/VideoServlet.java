package com.wangquocthai.java4.lab6.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet xử lý các trang Video cho user
 */
@WebServlet({"/video/list", "/video/detail/*", "/video/like/*", "/video/share/*"})
public class VideoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        if (uri.contains("/video/list")) {
            req.getRequestDispatcher("/lab6/views/video/list.jsp").forward(req, resp);
        } else if (uri.contains("/video/detail/")) {
            req.getRequestDispatcher("/lab6/views/video/detail.jsp").forward(req, resp);
        } else if (uri.contains("/video/like/")) {
            req.getRequestDispatcher("/lab6/views/video/like.jsp").forward(req, resp);
        } else if (uri.contains("/video/share/")) {
            req.getRequestDispatcher("/lab6/views/video/share.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
