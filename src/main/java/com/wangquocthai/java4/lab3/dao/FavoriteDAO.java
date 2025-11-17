package com.wangquocthai.java4.lab3.dao;

import com.wangquocthai.java4.lab3.entity.Favorite;
import java.util.List;

public interface FavoriteDAO {
    List<Favorite> findAll();
    Favorite findById(Long id);
    void create(Favorite favorite);
    void update(Favorite favorite);
    void deleteById(Long id);

    /**
     * Lấy tất cả video yêu thích của một User cụ thể.
     * @param userId ID của người dùng
     * @return Danh sách Favorite của user đó, hoặc danh sách rỗng
     */
    List<Favorite> findByUserId(String userId);

    /**
     * Lấy tất cả video yêu thích của một User cụ thể.
     * @param videoId ID của người dùng
     * @return Danh sách Favorite của user đó, hoặc danh sách rỗng
     */
    List<Favorite> findByVideoId(String videoId);

    /**
     * Tìm một record Favorite dựa trên ID của User và ID của Video.
     * @param userId ID của người dùng
     * @param videoId ID của video
     * @return một đối tượng Favorite, hoặc null nếu chưa "like"
     */
    Favorite findByUserIdAndVideoId(String userId, String videoId);

    /**
     * PHƯƠNG THỨC NÂNG CAO (BÁO CÁO)
     * Thống kê số lượt "like" cho mỗi video.
     * @return Danh sách các mảng Object, mỗi mảng [Video (Entity), số lượng like (Long)]
     */
    List<Object[]> countFavoritesByVideo();
}