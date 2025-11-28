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

@WebServlet("/account/edit-profile")
public class EditProfileServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lab6/views/account/edit-profile.jsp").forward(req, resp);
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
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");

            // Update profile
            currentUser.setFullname(fullname);
            currentUser.setEmail(email);
            userDAO.update(currentUser);
            session.setAttribute("user", currentUser);

            req.setAttribute("success", "Cập nhật thông tin thành công!");
            req.getRequestDispatcher("/lab6/views/account/edit-profile.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.getRequestDispatcher("/lab6/views/account/edit-profile.jsp").forward(req, resp);
        }
    }
}
