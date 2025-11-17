package com.wangquocthai.java4.lab3.dao;

import com.wangquocthai.java4.lab3.entity.Share;
import java.util.List;

public interface ShareDAO {
    List<Share> findAll();
    Share findById(Long id);
    void create(Share share);
    void update(Share share);
    void deleteById(Long id);
}