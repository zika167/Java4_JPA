package com.wangquocthai.java4.lab7.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet("/lab7/api/nhanvien")
public class AjaxFetchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Thiết lập resp type và định dạng kiểu chữ
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String json = """
            {
                "manv": "TeoNV",
                "hoTen": "Nguyễn Văn Tèo",
                "gioiTinh": true,
                "luong": 950.5
            }
        """;

        response.getWriter().print(json);
    }
    
}