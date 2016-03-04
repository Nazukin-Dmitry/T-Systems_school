package com.tsystems.nazukin.logiweb.model.dao.interfaces;

import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;

import java.util.List;

/**
 * Created by 1 on 17.02.2016.
 */
public interface DriverDao extends GenericDAO<DriverEntity, Integer> {

    Integer maxSerialNumber();

    List<DriverEntity> findByCityId(Integer id);

    List<DriverEntity> findForOrder(Integer cityId);

    DriverEntity findByEmployeeId(Integer employeeId);

    List<DriverEntity> findAll();
}
