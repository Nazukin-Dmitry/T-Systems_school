package com.tsystems.nazukin.logiweb;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by 1 on 06.02.2016.
 */
public class JPAUtil {
    private static EntityManager em = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();

    public static EntityManager getInstance(){
        return em;
    }

    public static void beginTransaction(){
        em.getTransaction().begin();
    }

    public static void endTransaction(){
        em.getTransaction().commit();
    }
}

