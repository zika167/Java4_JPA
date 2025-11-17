package com.wangquocthai.java4.lab2.dao;

import com.wangquocthai.java4.lab2.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    /**Truy vấn tất cả*/
    List<User> findAll();
    /**Truy vấn theo mã*/
    User findById(String userId);
    /**Thêm mới*/
    void create(User item);
    /**Cập nhật*/
    void update(User item);
    /**Xóa theo mã*/
    void deleteById(String id);
}