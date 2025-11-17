package com.wangquocthai.java4.lab3.controller; // (Em đặt vào package servlet của em)

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

@WebServlet("/favorites") // URL để truy cập trang này
public class FavoriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Kiểm tra xem User đã đăng nhập chưa (Yêu cầu bảo mật)
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user"); // Giả sử em lưu User trong session với key là "user"

        if (currentUser == null) {
            // Nếu chưa đăng nhập, "đá" về trang login
            response.sendRedirect(request.getContextPath() + "/login"); // (Em đổi /login thành trang login của em)
            return;
        }

        // 2. User đã đăng nhập -> Lấy danh sách yêu thích
        // Dùng phương thức DAO nâng cao ta vừa tạo
        FavoriteDAO dao = new FavoriteDAOImpl();
        List<Favorite> favoritesList = dao.findByUserId(currentUser.getId());

        // 3. Gửi 2 đối tượng (User hiện tại và Danh sách) sang JSP
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("favoritesList", favoritesList);

        // 4. Forward sang View (JSP)
        request.getRequestDispatcher("/lab3/views/favorites.jsp").forward(request, response);
    }
}