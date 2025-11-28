package com.wangquocthai.java4.lab6.servlet;

import com.wangquocthai.java4.lab6.dao.UserDAO;
import com.wangquocthai.java4.lab6.dao.UserDAOImpl;
import com.wangquocthai.java4.lab6.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/account/change-password")
public class ChangePasswordServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lab6/views/account/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/lab6/page.jsp");
            return;
        }

        try {
            User currentUser = (User) session.getAttribute("user");
            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");
            String confirmPassword = req.getParameter("confirmPassword");

            // Validate
            if (!currentUser.getPassword().equals(oldPassword)) {
                req.setAttribute("error", "Mật khẩu cũ không đúng!");
                req.getRequestDispatcher("/lab6/views/account/change-password.jsp").forward(req, resp);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                req.setAttribute("error", "Mật khẩu mới không khớp!");
                req.getRequestDispatcher("/lab6/views/account/change-password.jsp").forward(req, resp);
                return;
            }

            // Update password
            currentUser.setPassword(newPassword);
            userDAO.update(currentUser);
            session.setAttribute("user", currentUser);

            req.setAttribute("success", "Đổi mật khẩu thành công!");
            req.getRequestDispatcher("/lab6/views/account/change-password.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.getRequestDispatcher("/lab6/views/account/change-password.jsp").forward(req, resp);
        }
    }
}
