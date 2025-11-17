package com.wangquocthai.java4.lab3.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class XJPA {
    private static EntityManagerFactory factory;
    // Sử dụng static block để khởi tạo factory một cách an toàn
    static {
        try {
            factory = Persistence.createEntityManagerFactory("Java4_Lab3_PolyOE");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khởi tạo EntityManagerFactory!");
        }
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
