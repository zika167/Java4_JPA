package com.wangquocthai.java4.lab5.logs.dao;

import com.wangquocthai.java4.lab5.logs.entity.Logs;

import java.util.List;

public interface LogsDAO {
    void create(Logs log);
    List<Logs> findAll();
    List<Logs> findByUsername(String username);
}
