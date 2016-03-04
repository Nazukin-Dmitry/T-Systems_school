package com.tsystems.nazukin.logiweb.model.dao.interfaces;

import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;

import java.util.List;

/**
 * Created by 1 on 15.02.2016.
 */
public interface TruckDao extends GenericDAO<TruckEntity, Integer> {

    List<TruckEntity> findByCityId(Integer id);

    List<TruckEntity> findForOrder(Integer cityId);

    List<TruckEntity> findAll();
}
