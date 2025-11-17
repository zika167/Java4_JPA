package com.wangquocthai.java4.lab3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy session hiện tại (nếu có)
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Hủy session (xóa tất cả thuộc tính trong session)
            session.invalidate();
        }

        // Chuyển hướng về trang login
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
