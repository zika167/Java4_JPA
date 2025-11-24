package com.wangquocthai.java4.lab5.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class VisitorCounterListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Đọc số đếm từ nguồn lưu trữ (file hoặc CSDL tùy chọn)
        // Ở đây khởi tạo = 0 để demo
        ServletContext context = sce.getServletContext();
        context.setAttribute("visitors", 0);
        System.out.println("Application started. Visitor counter initialized to 0");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Tăng số đếm lên 1 và chia sẻ trở lại vào application scope
        ServletContext context = se.getSession().getServletContext();
        synchronized (context) {
            Integer visitors = (Integer) context.getAttribute("visitors");
            if (visitors == null) {
                visitors = 0;
            }
            visitors++;
            context.setAttribute("visitors", visitors);
            System.out.println("New session created. Total visitors: " + visitors);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Lưu số đếm trở lại nguồn lưu trữ
        ServletContext context = sce.getServletContext();
        Integer visitors = (Integer) context.getAttribute("visitors");
        System.out.println("Application stopped. Total visitors was: " + visitors);
        // Có thể lưu vào file hoặc database ở đây
    }
}
