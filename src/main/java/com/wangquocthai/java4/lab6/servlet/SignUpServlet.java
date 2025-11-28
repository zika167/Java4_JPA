package com.wangquocthai.java4.lab6.servlet;

import com.wangquocthai.java4.lab6.dao.UserDAO;
import com.wangquocthai.java4.lab6.dao.UserDAOImpl;
import com.wangquocthai.java4.lab6.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account/sign-up")
public class SignUpServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lab6/views/account/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");

            // Validate
            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
                req.getRequestDispatcher("/lab6/views/account/sign-up.jsp").forward(req, resp);
                return;
            }

            if (userDAO.findById(id) != null) {
                req.setAttribute("error", "Username đã tồn tại!");
                req.getRequestDispatcher("/lab6/views/account/sign-up.jsp").forward(req, resp);
                return;
            }

            // Create user
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setEmail(email);
            user.setAdmin(false);

            userDAO.create(user);

            req.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/lab6/page.jsp");

        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.getRequestDispatcher("/lab6/views/account/sign-up.jsp").forward(req, resp);
        }
    }
}
