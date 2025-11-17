package com.wangquocthai.java4.lab3.dao.impl;

import com.wangquocthai.java4.lab3.dao.FavoriteDAO;
import com.wangquocthai.java4.lab3.entity.Favorite;
import com.wangquocthai.java4.lab3.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavoriteDAOImpl implements FavoriteDAO {

    @Override
    public List<Favorite> findAll() {
        EntityManager em = XJPA.getEntityManager();
        // o là viết tắt của Object (hoặc đặt là f cho favorite cũng được)
        String jpql = "SELECT o FROM Favorite o";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        List<Favorite> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public Favorite findById(Long id) {
        EntityManager em = XJPA.getEntityManager();
        Favorite favorite = em.find(Favorite.class, id);
        em.close();
        return favorite;
    }

    @Override
    public void create(Favorite favorite) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(favorite);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Favorite favorite) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(favorite);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Favorite favorite = em.find(Favorite.class, id);
            if (favorite != null) {
                em.remove(favorite);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Favorite> findByUserId(String userId) {
        EntityManager em = XJPA.getEntityManager();
        // Dùng JPQL để truy vấn dựa trên ID của đối tượng User liên kết
        String jpql = "SELECT o FROM Favorite o WHERE o.user.id = :uid";

        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        query.setParameter("uid", userId); // Gán tham số :uid

        List<Favorite> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Favorite> findByVideoId(String videoId) {
        EntityManager em = XJPA.getEntityManager();
        // Dùng JPQL để truy vấn dựa trên ID của đối tượng User liên kết
        String jpql = "SELECT o FROM Favorite o WHERE o.video.id = :vid";

        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        query.setParameter("vid", videoId); // Gán tham số :vid

        List<Favorite> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public Favorite findByUserIdAndVideoId(String userId, String videoId) {
        EntityManager em = XJPA.getEntityManager();
        String jpql = "SELECT o FROM Favorite o WHERE o.user.id = :uid AND o.video.id = :vid";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        query.setParameter("uid", userId);
        query.setParameter("vid", videoId);
        List<Favorite> list = query.getResultList();
        em.close();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Object[]> countFavoritesByVideo() {
        EntityManager em = XJPA.getEntityManager();

        // JPQL: Chọn đối tượng Video (o.video) và Đếm (COUNT) số lượt
        // "like" (o), sau đó Nhóm (GROUP BY) theo Video (o.video)
        String jpql = "SELECT o.video, COUNT(o) FROM Favorite o GROUP BY o.video";

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        List<Object[]> list = query.getResultList();
        em.close();
        return list;
    }
}