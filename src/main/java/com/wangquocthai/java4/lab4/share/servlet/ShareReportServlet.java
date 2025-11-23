package com.wangquocthai.java4.lab4.share.servlet;

import com.wangquocthai.java4.lab4.share.dao.ShareDAO;
import com.wangquocthai.java4.lab4.share.dao.ShareDAOImpl;
import com.wangquocthai.java4.lab4.share.dto.VideoShareReport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/share/report")
public class ShareReportServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShareDAO shareDAO = new ShareDAOImpl();
        List<VideoShareReport> reports = shareDAO.getVideoShareReport();
        
        req.setAttribute("reports", reports);
        req.getRequestDispatcher("/lab4/views/share/share_report.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
