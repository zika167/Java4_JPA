package poly.filter;

import com.wangquocthai.java4.lab6.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * AuthFilter - Bộ lọc phân quyền
 * Kiểm tra quyền truy cập cho các URL yêu cầu đăng nhập hoặc quyền admin
 */
@WebFilter({
    "/admin/*",
    "/account/change-password",
    "/account/edit-profile",
    "/video/like/*",
    "/video/share/*"
})
public class AuthFilter implements Filter {
    
    public static final String SECURITY_URI = "securityUri";
    
    @Override
    public void destroy() {
        // Cleanup nếu cần
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        // Lấy user từ session
        User user = (User) session.getAttribute("user");
        
        // Lấy URI hiện tại
        String uri = req.getRequestURI();
        
        // Kiểm tra quyền truy cập
        if (user == null || (uri.contains("/admin/") && !user.getAdmin())) {
            // Nếu chưa đăng nhập HOẶC truy cập /admin/ mà không phải admin
            
            System.out.println("🚫 AuthFilter: Access denied to " + uri);
            System.out.println("   User: " + (user != null ? user.getId() : "null"));
            System.out.println("   Is Admin: " + (user != null ? user.getAdmin() : "N/A"));
            
            // Lưu URI vào session để redirect về sau khi đăng nhập
            session.setAttribute(AuthFilter.SECURITY_URI, uri);
            System.out.println("💾 Saved security URI to session: " + uri);
            
            // Redirect đến servlet login (theo yêu cầu Bài 4)
            String loginUrl = req.getContextPath() + "/lab6/login";
            System.out.println("➡️ Redirecting to: " + loginUrl);
            resp.sendRedirect(loginUrl);
        } else {
            // Cho phép tiếp tục
            System.out.println("✅ AuthFilter: Access granted to " + uri + " for user " + user.getId());
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo nếu cần
    }
}
