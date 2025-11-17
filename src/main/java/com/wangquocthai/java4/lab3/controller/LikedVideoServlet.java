package com.wangquocthai.java4.lab3.controller;

import com.wangquocthai.java4.lab3.dao.FavoriteDAO;
import com.wangquocthai.java4.lab3.dao.impl.FavoriteDAOImpl;
import com.wangquocthai.java4.lab3.entity.Favorite;
import com.wangquocthai.java4.lab3.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/liked-videos")
public class LikedVideoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra xem User đã đăng nhập chưa
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null) {
            // Nếu chưa đăng nhập, chuyển về trang login
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy tất cả video đã được yêu thích
        FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
        List<Favorite> favorites = favoriteDAO.findAll();

        request.setAttribute("favorites", favorites);
        request.getRequestDispatcher("/lab3/views/liked-video.jsp").forward(request, response);
    }
}
