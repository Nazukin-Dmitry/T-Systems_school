package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.JPAUtil;
import com.tsystems.nazukin.logiweb.customexceptions.UserAllreadyExistException;
import com.tsystems.nazukin.logiweb.model.dao.implementations.EmployeeDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.EmployeeDao;
import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.model.enums.EmployeeType;

import java.util.List;

/**
 * Created by 1 on 06.02.2016.
 */
public class EmployeeService {

    private final EmployeeDao dao;

    public EmployeeService() {
        dao = new EmployeeDaoImpl();
    }

    public EmployeeService(EmployeeDao dao) {
        this.dao = dao;
    }

    public List<EmployeeEntity> getAllNew() {
        List<EmployeeEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = dao.findAllNew();
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    public void newToManager(Integer id) {
        EmployeeEntity entity;
        try {
            JPAUtil.beginTransaction();
            entity = dao.findById(EmployeeEntity.class, id);
            entity.setEmployeeType(EmployeeType.MANAGER);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    public EmployeeEntity getByLoginAndPassword(String login, String password) {
        EmployeeEntity result;
        try {
            JPAUtil.beginTransaction();
            result = dao.findByLoginAndPassword(login, password);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    public void save(EmployeeEntity entity) {
        try {
            JPAUtil.beginTransaction();
            EmployeeEntity userWithSameLogin = dao.findByLogin(entity.getLogin());
            if (userWithSameLogin != null) {
                throw new UserAllreadyExistException("This login has already used");
            }
            dao.save(entity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }
}
