package com.wangquocthai.java4.lab5.user.servlet;

import com.wangquocthai.java4.lab5.user.dao.UserDAO;
import com.wangquocthai.java4.lab5.user.dao.UserDAOImpl;
import com.wangquocthai.java4.lab5.user.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/lab5/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lab5/views/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String errorMessage = null;

        // Sử dụng dao.findById() để truy vấn User từ CSDL
        User user = userDAO.findById(username);

        // Nếu tìm ko thấy thì báo lỗi sai username
        if (user == null) {
            errorMessage = "Username không tồn tại!";
        } else {
            // Ngược lại so sánh mật khẩu nhập vào và mật khẩu của user truy vấn được
            if (!password.equals(user.getPassword())) {
                // Nếu không giống thì báo sai password
                errorMessage = "Mật khẩu không đúng!";
            } else {
                // Ngược lại thì lưu user vào session và báo đăng nhập thành công
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/lab5/login");
                return;
            }
        }

        req.setAttribute("errorMessage", errorMessage);
        req.getRequestDispatcher("/lab5/views/user/login.jsp").forward(req, resp);
    }
}
