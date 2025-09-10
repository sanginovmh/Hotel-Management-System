package com.org.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerDAO {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory =
                    Persistence
                            .createEntityManagerFactory("HMS_ORM");
        }

        return entityManagerFactory
                .createEntityManager();
    }
}
