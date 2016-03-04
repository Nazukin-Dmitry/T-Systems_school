package com.tsystems.nazukin.logiweb.model.dao.interfaces;

import com.tsystems.nazukin.logiweb.model.entity.MapEntity;

import java.util.List;

/**
 * Created by 1 on 14.02.2016.
 */
public interface MapDao extends GenericDAO<MapEntity, Integer> {
    List<MapEntity> findAllByCity1(Integer id);

    List<MapEntity> findAllByCity2(Integer id);

    MapEntity findByIds(Integer id1, Integer id2);
}
