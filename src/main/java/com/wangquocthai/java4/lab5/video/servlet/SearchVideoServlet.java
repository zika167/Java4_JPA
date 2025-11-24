package com.wangquocthai.java4.lab5.video.servlet;

import com.wangquocthai.java4.lab5.video.dao.VideoDAO;
import com.wangquocthai.java4.lab5.video.dao.VideoDAOImpl;
import com.wangquocthai.java4.lab5.video.entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/lab5-video/search")
public class SearchVideoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            VideoDAO videoDAO = new VideoDAOImpl();
            List<Video> videos = videoDAO.findByTitleContaining(keyword.trim());
            
            req.setAttribute("videos", videos);
            req.setAttribute("keyword", keyword);
            req.setAttribute("message", "Found " + videos.size() + " video(s) for keyword: \"" + keyword + "\"");
        }
        
        req.getRequestDispatcher("/lab4/views/video/search_video.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
