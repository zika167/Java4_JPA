package com.wangquocthai.java4.lab3.dao.impl;

import com.wangquocthai.java4.lab3.dao.ShareDAO;
import com.wangquocthai.java4.lab3.entity.Share;
import com.wangquocthai.java4.lab3.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShareDAOImpl implements ShareDAO {

    @Override
    public List<Share> findAll() {
        EntityManager em = XJPA.getEntityManager();
        String jpql = "SELECT o FROM Share o";
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);
        List<Share> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public Share findById(Long id) {
        EntityManager em = XJPA.getEntityManager();
        Share share = em.find(Share.class, id);
        em.close();
        return share;
    }

    @Override
    public void create(Share share) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(share);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Share share) {
        EntityManager em = XJPA.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(share);
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
            Share share = em.find(Share.class, id);
            if (share != null) {
                em.remove(share);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}