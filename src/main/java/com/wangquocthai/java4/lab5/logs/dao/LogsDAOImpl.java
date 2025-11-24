package com.wangquocthai.java4.lab5.logs.dao;

import com.wangquocthai.java4.lab5.logs.entity.Logs;
import com.wangquocthai.java4.lab5.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LogsDAOImpl implements LogsDAO {
    private EntityManager em = XJPA.getEntityManager();

    @Override
    public void create(Logs log) {
        try {
            em.getTransaction().begin();
            em.persist(log);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Logs> findAll() {
        String jpql = "SELECT l FROM Logs l ORDER BY l.time DESC";
        TypedQuery<Logs> query = em.createQuery(jpql, Logs.class);
        return query.getResultList();
    }

    @Override
    public List<Logs> findByUsername(String username) {
        String jpql = "SELECT l FROM Logs l WHERE l.username = :username ORDER BY l.time DESC";
        TypedQuery<Logs> query = em.createQuery(jpql, Logs.class);
        query.setParameter("username", username);
        return query.getResultList();
    }
}
