package com.tsystems.nazukin.logiweb.model.dao;

import com.tsystems.nazukin.logiweb.JPAUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by 1 on 06.02.2016.
 */
public abstract class EmployeeDao<T, ID extends Serializable> {
    public EntityManager em = JPAUtil.getInstance();

    public void insert(T entity) {
        em.persist(entity);
    }

    public void update(T entity) {
        em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public T findById(Class clazz, int id){
       return (T) em.find(clazz, id);
    }
}
