package com.wangquocthai.java4.lab3.controller; // (Em đặt vào package servlet của em)

import com.wangquocthai.java4.lab3.dao.UserDAO;
import com.wangquocthai.java4.lab3.dao.impl.UserDAOImpl;
import com.wangquocthai.java4.lab3.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/lab3/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        // Khởi tạo DAO một lần để tái sử dụng
        this.userDAO = new UserDAOImpl();
    }

    /**
     * Xử lý GET request: Hiển thị form login
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chỉ cần forward đến trang JSP
        request.getRequestDispatcher("/lab3/views/login.jsp").forward(request, response);
    }

    /**
     * Xử lý POST request: Kiểm tra thông tin đăng nhập
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy thông tin từ form
        String userId = request.getParameter("id");
        String password = request.getParameter("password");

        // 2. Kiểm tra CSDL
        User user = null;
        if (userId != null && !userId.isEmpty()) {
            user = this.userDAO.findById(userId);
        }

        // 3. Phân tích kết quả
        if (user != null && user.getPassword().equals(password)) {
            // ĐĂNG NHẬP THÀNH CÔNG

            // 3.1. Tạo session và lưu thông tin User vào
            HttpSession session = request.getSession(); // Lấy hoặc tạo session mới
            session.setAttribute("user", user); // "user" là key quan trọng mà FavoriteServlet sẽ dùng

            // 3.2. Chuyển hướng đến trang chính (ví dụ: trang CRUD user hoặc trang chủ)
            response.sendRedirect(request.getContextPath() + "/favorites");

        } else {
            // ĐĂNG NHẬP THẤT BẠI

            // 3.1. Gửi thông báo lỗi quay lại trang login
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");

            // 3.2. Forward (không phải redirect) để giữ lại request và hiển thị lỗi
            request.getRequestDispatcher("/lab3/views/login.jsp").forward(request, response);
        }
    }
}