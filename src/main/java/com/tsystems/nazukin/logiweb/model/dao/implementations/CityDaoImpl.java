package com.tsystems.nazukin.logiweb.model.dao.implementations;

import com.tsystems.nazukin.logiweb.model.dao.interfaces.CityDao;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by 1 on 14.02.2016.
 */
public class CityDaoImpl extends GenericDAOImpl<CityEntity, Integer> implements CityDao {
    @Override
    public List<CityEntity> findAll() {
        TypedQuery<CityEntity> query = getEntityManager().createNamedQuery("CityEntity.findAll", CityEntity.class);
        List<CityEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<CityEntity> findAllWithout(Integer id) {
        TypedQuery<CityEntity> query = getEntityManager().createNamedQuery("CityEntity.findAllWithout", CityEntity.class);
        query.setParameter("city_id", id);
        List<CityEntity> results = query.getResultList();
        return results;
    }

}
