package com.wangquocthai.java4.finaltest.controller;

import com.wangquocthai.java4.finaltest.dao.ProductDAO;
import com.wangquocthai.java4.finaltest.dao.impl.ProductDAOImpl;
import com.wangquocthai.java4.finaltest.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/finaltest/product/*")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/list")) {
            // Hiển thị danh sách sản phẩm
            List<Product> products = productDAO.findAll();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/finaltest/views/product-list.jsp").forward(request, response);

        } else if (pathInfo.equals("/create")) {
            // Hiển thị form tạo mới
            request.getRequestDispatcher("/finaltest/views/product-form.jsp").forward(request, response);

        } else if (pathInfo.equals("/edit")) {
            // Hiển thị form chỉnh sửa
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Integer id = Integer.parseInt(idParam);
                Product product = productDAO.findById(id);
                request.setAttribute("product", product);
            }
            request.getRequestDispatcher("/finaltest/views/product-form.jsp").forward(request, response);

        } else if (pathInfo.equals("/delete")) {
            // Xóa sản phẩm
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Integer id = Integer.parseInt(idParam);
                productDAO.deleteById(id);
            }
            response.sendRedirect(request.getContextPath() + "/finaltest/product/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");

        Double price = Double.parseDouble(priceParam);
        Integer quantity = Integer.parseInt(quantityParam);

        if (idParam == null || idParam.isEmpty()) {
            // Tạo mới
            Product product = Product.builder()
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .build();
            productDAO.create(product);
        } else {
            // Cập nhật
            Integer id = Integer.parseInt(idParam);
            Product product = Product.builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .build();
            productDAO.update(product);
        }

        response.sendRedirect(request.getContextPath() + "/finaltest/product/list");
    }
}
