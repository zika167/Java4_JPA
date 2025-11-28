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
import poly.filter.AuthFilter;

import java.io.IOException;

@WebServlet({"/lab6/login", "/account/sign-in"})
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lab6/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            // Tìm user trong database
            User user = userDAO.findById(username);

            if (user == null) {
                req.setAttribute("error", "Username không tồn tại!");
                req.getRequestDispatcher("/lab6/views/auth/login.jsp").forward(req, resp);
                return;
            }

            if (!user.getPassword().equals(password)) {
                req.setAttribute("error", "Mật khẩu không đúng!");
                req.getRequestDispatcher("/lab6/views/auth/login.jsp").forward(req, resp);
                return;
            }

            // Đăng nhập thành công
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            
            System.out.println("✅ Login successful: " + user.getId());

            // Kiểm tra xem có URI cần redirect không (từ AuthFilter)
            String securityUri = (String) session.getAttribute(AuthFilter.SECURITY_URI);
            System.out.println("🔍 Security URI from session: " + securityUri);
            
            if (securityUri != null && !securityUri.isEmpty()) {
                session.removeAttribute(AuthFilter.SECURITY_URI);
                System.out.println("➡️ Redirecting to: " + securityUri);
                resp.sendRedirect(securityUri);
            } else {
                String defaultPage = req.getContextPath() + "/lab6/page.jsp";
                System.out.println("➡️ Redirecting to default: " + defaultPage);
                resp.sendRedirect(defaultPage);
            }

        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.getRequestDispatcher("/lab6/views/auth/login.jsp").forward(req, resp);
        }
    }
}
