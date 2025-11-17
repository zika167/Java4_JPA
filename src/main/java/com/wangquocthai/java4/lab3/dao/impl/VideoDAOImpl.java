package com.wangquocthai.java4.lab3.dao.impl;

import com.wangquocthai.java4.lab3.dao.VideoDAO;
import com.wangquocthai.java4.lab3.entity.Video;
import com.wangquocthai.java4.lab3.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    @Override
    public List<Video> findAll() {
        EntityManager em = XJPA.getEntityManager();
        String jpql = "SELECT v FROM Video v";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        List<Video> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public Video findById(String id) {
        EntityManager em = XJPA.getEntityManager();
        Video video = em.find(Video.class, id);
        em.close();
        return video;
    }

    @Override
    public void create(Video video) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(video);
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
    public void update(Video video) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(video);
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
            Video video = em.find(Video.class, id);
            if (video != null) {
                em.remove(video);
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

    @Override
    public List<Video> findAllPaging(int pageNumber, int pageSize) {
        EntityManager em = XJPA.getEntityManager();

        // JPQL: Thêm "ORDER BY v.views DESC" để sắp xếp
        String jpql = "SELECT v FROM Video v ORDER BY v.views DESC";

        TypedQuery<Video> query = em.createQuery(jpql, Video.class);

        // Logic phân trang
        // Ví dụ: trang 1, 6 video -> (1-1)*6 = 0 -> lấy từ kết quả 0
        // Ví dụ: trang 2, 6 video -> (2-1)*6 = 6 -> lấy từ kết quả 6
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<Video> list = query.getResultList();
        em.close();
        return list;
    }
}