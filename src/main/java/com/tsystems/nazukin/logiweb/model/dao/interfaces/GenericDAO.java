package com.tsystems.nazukin.logiweb.model.dao.interfaces;

import java.io.Serializable;

/**
 * Created by 1 on 11.02.2016.
 */
public interface GenericDAO<T, ID extends Serializable> {
    void save(T entity);

    T merge(T entity);

    void delete(T entity);

    T findById(Class<T> clazz, ID id);
}

