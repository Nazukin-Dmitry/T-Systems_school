package com.tsystems.nazukin.logiweb.model.dao.interfaces;

import com.tsystems.nazukin.logiweb.model.entity.CityEntity;

import java.util.List;

/**
 * Created by 1 on 14.02.2016.
 */
public interface CityDao extends GenericDAO<CityEntity, Integer> {
    List<CityEntity> findAll();

    List<CityEntity> findAllWithout(Integer id);
}
