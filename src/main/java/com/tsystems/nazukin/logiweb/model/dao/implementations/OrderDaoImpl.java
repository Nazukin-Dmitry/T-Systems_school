package com.tsystems.nazukin.logiweb.model.dao.implementations;

import com.tsystems.nazukin.logiweb.model.dao.interfaces.OrderDao;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by 1 on 20.02.2016.
 */
public class OrderDaoImpl extends GenericDAOImpl<OrderEntity, Integer> implements OrderDao {
    @Override
    public OrderEntity findByTruckId(Integer truckId) {
        TypedQuery<OrderEntity> query = getEntityManager().createNamedQuery("OrderEntity.findByTruckId"
                , OrderEntity.class);
        query.setParameter("id", truckId);
        List<OrderEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public List<OrderEntity> findByCityIdWithoutDrivers(Integer cityId) {
        TypedQuery<OrderEntity> query = getEntityManager().createNamedQuery("OrderEntity.findByCityIdWithoutDrivers"
                , OrderEntity.class);
        query.setParameter("id", cityId);
        List<OrderEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<OrderEntity> findByStartCityId(Integer cityId) {
        TypedQuery<OrderEntity> query = getEntityManager().createNamedQuery("OrderEntity.findByCityId"
                , OrderEntity.class);
        query.setParameter("id", cityId);
        List<OrderEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<OrderEntity> findAll() {
        TypedQuery<OrderEntity> query = getEntityManager().createNamedQuery("OrderEntity.findAll", OrderEntity.class);
        List<OrderEntity> results = query.getResultList();
        return results;
    }
}
