package com.wangquocthai.java4.lab5.filter;

import com.wangquocthai.java4.lab5.logs.dao.LogsDAO;
import com.wangquocthai.java4.lab5.logs.dao.LogsDAOImpl;
import com.wangquocthai.java4.lab5.logs.entity.Logs;
import com.wangquocthai.java4.lab5.user.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Bài 3: AppFilter - Ghi nhận thông tin truy cập
 * - Thiết lập mã hóa UTF-8 cho tất cả request
 * - Ghi nhận thông tin truy cập vào CSDL (Id, URI, Access Time, Username)
 */
@WebFilter(urlPatterns = {"/lab5/*"})
public class AppFilter implements Filter {
    private LogsDAO logsDAO = new LogsDAOImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // 1. Thiết lập mã hóa UTF-8 cho tất cả trang web
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 2. Ghi nhận thông tin truy cập vào CSDL
        try {
            // Lấy thông tin từ request
            String uri = httpRequest.getRequestURI();
            LocalDateTime accessTime = LocalDateTime.now();
            
            // Lấy username từ session nếu user đã đăng nhập
            HttpSession session = httpRequest.getSession(false);
            String username = null;
            if (session != null) {
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    username = user.getId();
                }
            }
            
            // Tạo log mới
            Logs log = new Logs();
            log.setUrl(uri);
            log.setTime(accessTime);
            log.setUsername(username);
            
            // Gọi dao.create() để thêm vào CSDL
            logsDAO.create(log);
            
            System.out.println("✅ Log created: " + uri + " | User: " + username + " | Time: " + accessTime);
            
        } catch (Exception e) {
            System.err.println("❌ Error creating log: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Cho phép request tiếp tục
        chain.doFilter(request, response);
    }
}
