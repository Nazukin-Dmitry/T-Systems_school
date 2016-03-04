package com.tsystems.nazukin.logiweb.model.dao.implementations;

import com.tsystems.nazukin.logiweb.JPAUtil;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.GenericDAO;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by 1 on 11.02.2016.
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected EntityManager getEntityManager() {
        return JPAUtil.getEntityManager();
    }

    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    public T findById(Class<T> clazz, ID id) {
        return getEntityManager().find(clazz, id);
    }
}
