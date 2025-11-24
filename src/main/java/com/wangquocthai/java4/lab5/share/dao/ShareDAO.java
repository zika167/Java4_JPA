package com.wangquocthai.java4.lab5.share.dao;

import com.wangquocthai.java4.lab5.share.dto.VideoShareReport;
import com.wangquocthai.java4.lab5.share.entity.Share;

import java.util.List;

public interface ShareDAO {
    List<Share> findAll();
    Share findById(Integer id);
    List<VideoShareReport> getVideoShareReport();
    void create(Share share);
    void update(Share share);
    void deleteById(Integer id);
}
