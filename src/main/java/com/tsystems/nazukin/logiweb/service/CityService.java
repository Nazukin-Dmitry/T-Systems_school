package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.JPAUtil;
import com.tsystems.nazukin.logiweb.model.dao.implementations.CityDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.CityDao;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;

import java.util.List;

/**
 * Created by 1 on 14.02.2016.
 */
public class CityService {

    private final CityDao cityDao;

    public CityService() {
        cityDao = new CityDaoImpl();
    }

    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public void saveOrUpdate(final CityEntity entity) {
        try {
            JPAUtil.beginTransaction();
            cityDao.merge(entity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    public List<CityEntity> getAll() {
        List<CityEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = cityDao.findAll();
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }

        return result;
    }

    public CityEntity find(Integer id) {
        CityEntity result;
        try {
            JPAUtil.beginTransaction();
            result = cityDao.findById(CityEntity.class, id);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }
}
