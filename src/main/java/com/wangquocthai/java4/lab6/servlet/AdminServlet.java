package com.wangquocthai.java4.lab6.servlet;

import com.wangquocthai.java4.lab6.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet quản lý tất cả các trang Admin
 * Xử lý 4 URL: /admin/video, /admin/user, /admin/like, /admin/share
 */
@WebServlet({"/admin/video", "/admin/user", "/admin/like", "/admin/share"})
public class AdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy URI để xác định trang nào đang được truy cập
        String uri = req.getRequestURI();
        
        // Lấy thông tin admin từ session
        HttpSession session = req.getSession();
        User admin = (User) session.getAttribute("user");
        
        // Xác định trang JSP và title tương ứng
        String jspPage = "";
        String pageTitle = "";
        String pageIcon = "";
        
        if (uri.contains("/admin/video")) {
            jspPage = "/lab6/views/admin/video.jsp";
            pageTitle = "Quản lý Video";
            pageIcon = "📹";
        } else if (uri.contains("/admin/user")) {
            jspPage = "/lab6/views/admin/user.jsp";
            pageTitle = "Quản lý User";
            pageIcon = "👥";
        } else if (uri.contains("/admin/like")) {
            jspPage = "/lab6/views/admin/like.jsp";
            pageTitle = "Quản lý Favorite";
            pageIcon = "❤️";
        } else if (uri.contains("/admin/share")) {
            jspPage = "/lab6/views/admin/share.jsp";
            pageTitle = "Quản lý Share";
            pageIcon = "📤";
        }
        
        req.setAttribute("pageTitle", pageTitle);
        req.setAttribute("pageIcon", pageIcon);
        req.setAttribute("currentUri", uri);
        req.setAttribute("admin", admin);
        
        req.getRequestDispatcher(jspPage).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
