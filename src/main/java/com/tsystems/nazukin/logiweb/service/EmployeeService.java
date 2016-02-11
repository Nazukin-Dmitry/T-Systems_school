package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.model.dao.EmployeeDaoImpl;
import com.tsystems.nazukin.logiweb.JPAUtil;
import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;

import java.util.List;

/**
 * Created by 1 on 06.02.2016.
 */
public class EmployeeService {
    private EmployeeDaoImpl dao = new EmployeeDaoImpl();

    public List<EmployeeEntity> loadAllPersons() {
        List<EmployeeEntity> allPersons;

            JPAUtil.beginTransaction();
            allPersons = dao.getAll();
            JPAUtil.endTransaction();

        return allPersons;
    }

    public void save(EmployeeEntity entity){
        JPAUtil.beginTransaction();
        dao.insert(entity);
        JPAUtil.endTransaction();
    }
}
