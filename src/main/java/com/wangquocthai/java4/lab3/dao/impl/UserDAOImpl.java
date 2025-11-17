package com.wangquocthai.java4.lab3.dao.impl;

import com.wangquocthai.java4.lab3.dao.UserDAO;
import com.wangquocthai.java4.lab3.entity.User;
import com.wangquocthai.java4.lab3.utils.XJPA; // Class JpaUtil của em
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        EntityManager em = XJPA.getEntityManager();
        // JPQL: Truy vấn trên TÊN LỚP THỰC THỂ (Entity), không phải tên bảng
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public User findById(String id) {
        EntityManager em = XJPA.getEntityManager();
        // Dùng hàm find() để tìm theo khóa chính
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public void create(User user) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user); // Dùng persist() để tạo mới
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(user); // Dùng merge() để cập nhật
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(String id) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Cần tìm thực thể trước khi xóa
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user); // Dùng remove() để xóa
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}