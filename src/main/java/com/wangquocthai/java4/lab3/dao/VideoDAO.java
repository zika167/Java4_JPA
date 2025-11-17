package com.wangquocthai.java4.lab3.dao;

import com.wangquocthai.java4.lab3.entity.Video;
import java.util.List;

public interface VideoDAO {
    List<Video> findAll();
    Video findById(String id);
    void create(Video video);
    void update(Video video);
    void deleteById(String id);

    /**
     * PHƯƠNG THỨC NÂNG CAO
     * Truy vấn các Video có phân trang và sắp xếp theo lượt xem giảm dần.
     * @param pageNumber Số trang hiện tại (bắt đầu từ 1)
     * @param pageSize Số lượng video trên mỗi trang
     * @return Danh sách Video đã được phân trang
     */
    List<Video> findAllPaging(int pageNumber, int pageSize);
}