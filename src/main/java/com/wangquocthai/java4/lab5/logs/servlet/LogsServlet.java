package com.wangquocthai.java4.lab5.logs.servlet;

import com.wangquocthai.java4.lab5.logs.dao.LogsDAO;
import com.wangquocthai.java4.lab5.logs.dao.LogsDAOImpl;
import com.wangquocthai.java4.lab5.logs.entity.Logs;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/lab5/logs")
public class LogsServlet extends HttpServlet {
    private LogsDAO logsDAO = new LogsDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy tất cả logs từ database
        List<Logs> logsList = logsDAO.findAll();
        req.setAttribute("logsList", logsList);
        
        req.getRequestDispatcher("/lab5/views/log/logs.jsp").forward(req, resp);
    }
}
