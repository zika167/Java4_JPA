package com.wangquocthai.java4.lab7.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Lớp tiện ích poly.util.RestIO hỗ trợ chuyển đổi giữa Java Object và chuỗi JSON
 * cũng như gửi và nhận dữ liệu JSON giữa client và REST API
 */
public class RestIO {
    
    // ObjectMapper để chuyển đổi JSON
    private static ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Đọc chuỗi JSON gửi từ client
     */
    public static String readJson(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("utf-8");
        
        BufferedReader reader = req.getReader();
        StringBuilder buffer = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        
        reader.close();
        return buffer.toString();
    }
    
    /**
     * Gửi chuỗi JSON về client
     */
    public static void writeJson(HttpServletResponse resp, String json) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.getWriter().flush();
    }
    
    /**
     * Đọc chuỗi JSON gửi từ client và chuyển đổi sang Java Object
     */
    public static <T> T readObject(HttpServletRequest req, Class<T> clazz) throws IOException {
        String json = RestIO.readJson(req);
        T bean = mapper.readValue(json, clazz);
        return bean;
    }
    
    /**
     * Chuyển đổi Java Object sang chuỗi JSON và gửi về client
     */
    public static void writeObject(HttpServletResponse resp, Object data) throws IOException {
        String json = mapper.writeValueAsString(data);
        RestIO.writeJson(resp, json);
    }
    
    /**
     * Gửi đối tượng rỗng về client
     */
    public static void writeEmptyObject(HttpServletResponse resp) throws IOException {
        RestIO.writeObject(resp, java.util.Map.of());
    }
}
