package com.wangquocthai.java4.lab5.favorite.servlet;

import com.wangquocthai.java4.lab4.favorite.dao.FavoriteDAO;
import com.wangquocthai.java4.lab4.favorite.dao.FavoriteDAOImpl;
import com.wangquocthai.java4.lab4.favorite.entity.Favorite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/lab5-favorite/all")
public class FavoriteListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
        
        List<Favorite> favorites = favoriteDAO.findAll();
        
        req.setAttribute("favorites", favorites);
        req.getRequestDispatcher("/lab4/views/favorite/favorite_all.jsp").forward(req, resp);
    }
}
