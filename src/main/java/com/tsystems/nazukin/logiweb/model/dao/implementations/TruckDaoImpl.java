package com.tsystems.nazukin.logiweb.model.dao.implementations;

import com.tsystems.nazukin.logiweb.model.dao.interfaces.TruckDao;
import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by 1 on 15.02.2016.
 */
public class TruckDaoImpl extends GenericDAOImpl<TruckEntity, Integer> implements TruckDao {

    @Override
    public List<TruckEntity> findByCityId(Integer id) {
        TypedQuery<TruckEntity> query = getEntityManager().createNamedQuery("TruckEntity.findByCityId", TruckEntity.class);
        query.setParameter("id", id);
        List<TruckEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<TruckEntity> findForOrder(Integer cityId) {
        TypedQuery<TruckEntity> query = getEntityManager().createNamedQuery("TruckEntity.findForOrder", TruckEntity.class);
        query.setParameter("id", cityId);
        List<TruckEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<TruckEntity> findAll() {
        TypedQuery<TruckEntity> query = getEntityManager().createNamedQuery("TruckEntity.findAll", TruckEntity.class);
        List<TruckEntity> results = query.getResultList();
        return results;
    }
}
