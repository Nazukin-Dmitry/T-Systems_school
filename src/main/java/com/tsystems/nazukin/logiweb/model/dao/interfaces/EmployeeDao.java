package com.tsystems.nazukin.logiweb.model.dao.interfaces;

import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;

import java.util.List;

/**
 * Created by 1 on 06.02.2016.
 */
public interface EmployeeDao extends GenericDAO<EmployeeEntity, Integer> {
    List<EmployeeEntity> findAll();

    EmployeeEntity findByLoginAndPassword(String login, String password);

    EmployeeEntity findByLogin(String login);

    List<EmployeeEntity> findAllNew();
}
