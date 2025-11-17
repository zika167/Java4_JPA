package com.wangquocthai.java4.lab2.dao.impl;

import com.wangquocthai.java4.lab2.dao.UserDAO;
import com.wangquocthai.java4.lab2.entity.User;
import com.wangquocthai.java4.lab2.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.lang.Object;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        EntityManager em = null;
        try {
            em = XJPA.getEntityManager();
            String jpql = "SELECT o FROM User o";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            List<User> users = query.getResultList();
            System.out.println("--- DANH SÁCH TẤT CẢ USER ---");
            users.forEach(user -> {
                System.out.println("ID: " + user.getId() + " | Tên: " + user.getFullname()
                        + " | Email: " + user.getEmail() + " | Admin: " + user.isAdmin());
            });
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(String id) {
        EntityManager em = null;
        User user = null;
        try {
            em = XJPA.getEntityManager();
            user = em.find(User.class, id);
            if (user != null) {
                System.out.println("--- TÌM THẤY USER: " + id + " ---");
                System.out.println("ID: " + user.getId() + " | Tên: " + user.getFullname()
                        + " | Email: " + user.getEmail() + " | Admin: " + user.isAdmin());
            } else {
                System.out.println("!!! KHÔNG TÌM THẤY user với ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.find(User.class, id);
    }

    /**
     * Hàm CREATE: Thêm một User mới vào CSDL
     * @param item Đối tượng User (chưa có trong CSDL)
     */
    @Override
    public void create(User item) {
        EntityManager em = null;
        try {
            em = XJPA.getEntityManager();
            // Bắt đầu Transaction
            em.getTransaction().begin();

            // Dùng persist() để lưu đối tượng mới
            em.persist(item);

            // Chấp nhận thay đổi
            em.getTransaction().commit();
            System.out.println("Tạo user thành công: " + item.getId());
        } catch (Exception e) {
            System.err.println("Lỗi khi tạo user: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Hoàn tác nếu có lỗi
            }
            e.printStackTrace();
        }
    }

    /**
     * Hàm UPDATE: Cập nhật thông tin một User đã có
     * @param item Đối tượng User đã bị thay đổi thông tin
     */
    @Override
    public void update(User item) {
        EntityManager em = null;
        try {
            em = XJPA.getEntityManager();
            em.getTransaction().begin();

            // Dùng merge() để cập nhật đối tượng đã có
            // merge() sẽ tìm user có cùng ID và cập nhật các trường
            em.merge(item);

            em.getTransaction().commit();
            System.out.println("Cập nhật user thành công: " + item.getId());

        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật user: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Hàm DELETE: Xoá một User khỏi CSDL bằng ID
     * @param id ID của user cần xoá
     */
    @Override
    public void deleteById(String id) {
        EntityManager em = null;
        try {
            em = XJPA.getEntityManager();
            em.getTransaction().begin();

            // 1. Phải TÌM user trước khi xoá
            User entity = em.find(User.class, id);

            // 2. XỬ LÝ NULL: Rất quan trọng!
            if (entity != null) {
                // 3. Dùng remove() để xoá đối tượng
                em.remove(entity);
                System.out.println("Xoá user thành công: " + id);
            } else {
                System.out.println("Không thể xoá, user không tồn tại: " + id);
            }

            // 4. Commit (kể cả khi không tìm thấy user, vẫn commit để kết thúc transaction)
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá user: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
