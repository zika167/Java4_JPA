package com.wangquocthai.java4.lab4.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
    static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory("Java4_Lab4_PolyOE");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
