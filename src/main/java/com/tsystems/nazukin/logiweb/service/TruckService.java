package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.JPAUtil;
import com.tsystems.nazukin.logiweb.customexceptions.TruckAllreadyUsedException;
import com.tsystems.nazukin.logiweb.model.dao.implementations.CityDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.OrderDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.TruckDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.CityDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.OrderDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.TruckDao;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;

import java.util.List;

/**
 * Created by 1 on 15.02.2016.
 */
public class TruckService {
    private final CityDao cityDao;
    private final TruckDao truckDao;
    private final OrderDao orderDao;

    public TruckService() {
        cityDao = new CityDaoImpl();
        truckDao = new TruckDaoImpl();
        orderDao = new OrderDaoImpl();
    }

    public TruckService(CityDao cityDao, TruckDao truckDao, OrderDao orderDao) {
        this.cityDao = cityDao;
        this.truckDao = truckDao;
        this.orderDao = orderDao;
    }

    public List<TruckEntity> findAllByCityId(Integer id) {
        List<TruckEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = truckDao.findByCityId(id);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }

        return result;
    }

    public void saveOrUpdate(TruckEntity entity, String cityId) {
        try {
            JPAUtil.beginTransaction();
            entity.setCurrentCity(cityDao.findById(CityEntity.class, Integer.parseInt(cityId)));
            truckDao.merge(entity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    public void delete(Integer id) {
        try {
            JPAUtil.beginTransaction();
            TruckEntity entity = truckDao.findById(TruckEntity.class, id);
            ///check that truck isn't used now
            OrderEntity orderEntity = orderDao.findByTruckId(entity.getId());
            if (orderEntity != null) {
                throw new TruckAllreadyUsedException();
            }

            truckDao.delete(entity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    public TruckEntity find(Integer id) {
        TruckEntity entity;
        try {
            JPAUtil.beginTransaction();
            entity = truckDao.findById(TruckEntity.class, id);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return entity;
    }

    public List<TruckEntity> findAllForOrder(Integer cityId) {
        List<TruckEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = truckDao.findForOrder(cityId);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }

        return result;
    }

    public List<TruckEntity> findAll() {
        List<TruckEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = truckDao.findAll();
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }

        return result;
    }

}
