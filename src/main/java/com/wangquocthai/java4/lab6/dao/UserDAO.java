package com.wangquocthai.java4.lab6.dao;

import com.wangquocthai.java4.lab6.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(String id);
    User findByEmail(String email);
    void create(User user);
    void update(User user);
    void delete(String id);
}
