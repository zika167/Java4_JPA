package com.wangquocthai.java4.lab5.user.dao;

import com.wangquocthai.java4.lab5.user.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(String id);
    User findByIdOrEmail(String username);
    void create(User user);
    void update(User user);
    void deleteById(String id);
}
