package com.tsystems.nazukin.logiweb.model.dao.implementations;

import com.tsystems.nazukin.logiweb.model.dao.interfaces.MapDao;
import com.tsystems.nazukin.logiweb.model.entity.MapEntity;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by 1 on 14.02.2016.
 */
public class MapDaoImpl extends GenericDAOImpl<MapEntity, Integer> implements MapDao {

    @Override
    public List<MapEntity> findAllByCity1(Integer id) {
        TypedQuery<MapEntity> query = getEntityManager().createNamedQuery("MapEntity.findAllByCity1", MapEntity.class);
        query.setParameter("city_id", id);
        List<MapEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<MapEntity> findAllByCity2(Integer id) {
        TypedQuery<MapEntity> query = getEntityManager().createNamedQuery("MapEntity.findAllByCity2", MapEntity.class);
        query.setParameter("city_id", id);
        List<MapEntity> results = query.getResultList();
        return results;
    }

    @Override
    public MapEntity findByIds(Integer id1, Integer id2) {
        TypedQuery<MapEntity> query = getEntityManager().createNamedQuery("MapEntity.findByIds", MapEntity.class);
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        List<MapEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
