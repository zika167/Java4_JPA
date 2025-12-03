package com.wangquocthai.java4.lab7.servlet;

import com.wangquocthai.java4.lab7.entity.Employee;
import com.wangquocthai.java4.lab7.utils.RestIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Lab 7 - Bài 3: REST API quản lý nhân viên
 * Servlet xây dựng RESTful Web API phục vụ các tương tác quản lý nhân viên
 */
@WebServlet("/employees/*")
public class EmployeeRestServlet extends HttpServlet {
    
    // Map lưu trữ danh sách nhân viên (thay cho database)
    private Map<String, Employee> map = new HashMap<>();
    
    public EmployeeRestServlet() {
        // Khởi tạo dữ liệu mẫu
        map.put("NV01", new Employee("NV01", "Nhân viên 01", true, 500.0));
        map.put("NV02", new Employee("NV02", "Nhân viên 02", false, 600.0));
        map.put("NV03", new Employee("NV03", "Nhân viên 03", true, 5000.0));
        map.put("NV04", new Employee("NV04", "Nhân viên 04", false, 2500.0));
        map.put("NV05", new Employee("NV05", "Nhân viên 05", true, 3500.0));
    }
    
    /**
     * GET:/employees - lấy tất cả danh sách nhân viên => doGet()
     * GET:/employees/ID - lấy nhân viên có mã ID => doGet()
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Lấy thông tin từ PathInfo
        String info = req.getPathInfo();
        
        if (info == null || info.length() == 0) {
            // GET:/employees - Lấy tất cả
            RestIO.writeObject(resp, map.values());
        } else {
            // GET:/employees/ID - Lấy theo ID
            String id = info.substring(1).trim();
            RestIO.writeObject(resp, map.get(id));
        }
    }
    
    /**
     * POST:/employees, jsondata - thêm nhân viên mới => doPost()
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Đọc JSON từ client và chuyển sang Employee object
        Employee employee = RestIO.readObject(req, Employee.class);
        
        // Thêm vào map
        map.put(employee.getId(), employee);
        
        // Trả về employee vừa thêm
        RestIO.writeObject(resp, employee);
    }
    
    /**
     * PUT:/employees/ID, jsondata - cập nhật nhân viên có mã là ID => doPut()
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Đọc JSON từ client
        Employee employee = RestIO.readObject(req, Employee.class);
        
        // Cập nhật vào map
        map.put(employee.getId(), employee);
        
        // Trả về employee đã cập nhật
        RestIO.writeObject(resp, employee);
    }
    
    /**
     * DELETE:/employees/ID - xóa nhân viên có mã là ID => doDelete()
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Lấy ID từ PathInfo
        String info = req.getPathInfo();
        String id = info.substring(1).trim();
        
        // Xóa khỏi map
        map.remove(id);
        
        // Trả về đối tượng rỗng
        RestIO.writeEmptyObject(resp);
    }
}
