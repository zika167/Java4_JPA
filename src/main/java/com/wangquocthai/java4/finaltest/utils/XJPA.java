package com.wangquocthai.java4.finaltest.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("Java4_FinalTest");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khởi tạo EntityManagerFactory!");
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
