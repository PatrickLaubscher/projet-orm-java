package com.hb.cda.projetorm.repository.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Database {
    private static EntityManager em;

    public static EntityManager getManager() {
        if(em == null) {
            em = Persistence
                .createEntityManagerFactory("main").createEntityManager();
        }

        return em;
    }

    
}
