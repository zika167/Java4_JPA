package com.wangquocthai.java4.lab3.dao;

import com.wangquocthai.java4.lab3.entity.User;
import java.util.List;

/**
 * Interface định nghĩa các phương thức truy xuất dữ liệu cho thực thể User.
 */
public interface UserDAO {
    /**
     * Truy vấn tất cả các thực thể User.
     * @return Danh sách tất cả User
     */
    List<User> findAll();

    /**
     * Truy vấn thực thể User theo ID.
     * @param id Khóa chính của User
     * @return thực thể User hoặc null nếu không tìm thấy
     */
    User findById(String id);

    /**
     * Tạo mới một thực thể User.
     * @param user Đối tượng User chứa thông tin cần tạo
     */
    void create(User user);

    /**
     * Cập nhật một thực thể User.
     * @param user Đối tượng User chứa thông tin cần cập nhật
     */
    void update(User user);

    /**
     * Xóa một thực thể User dựa trên ID.
     * @param id Khóa chính của User cần xóa
     */
    void deleteById(String id);
}