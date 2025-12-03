package com.wangquocthai.java4.lab7.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

/**
 * Lab 7 - Bài 2: Servlet nhận file upload
 * Trả về JSON: { "name": ?, "type": ?, "size": ? }
 */
@WebServlet("/lab7/api/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        // Lấy file từ request
        Part filePart = req.getPart("file");
        
        // Lấy thông tin file
        String fileName = filePart.getSubmittedFileName();
        String contentType = filePart.getContentType();
        long fileSize = filePart.getSize();
        
        // Lưu file vào thư mục uploads ( trong target/java4... )
//        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
//        new File(uploadPath).mkdirs();
//        filePart.write(uploadPath + File.separator + fileName);

        // Lưu file vào thư mục file ( nhưng phải tạo trước thư mục file/.gitkeep ở webapp thì file mới được up lên target )
        String name = req.getServletContext().getRealPath("/file/" + fileName);
        filePart.write(name);
        
        // Trả về JSON
        String json = String.format("""
            {
                "name": "%s",
                "type": "%s",
                "size": %d
            }
            """, fileName, contentType, fileSize);
        
        resp.getWriter().print(json);
    }

}
