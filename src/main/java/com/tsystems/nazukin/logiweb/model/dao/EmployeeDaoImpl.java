package com.tsystems.nazukin.logiweb.model.dao;

import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by 1 on 06.02.2016.
 */
public class EmployeeDaoImpl extends EmployeeDao<EmployeeEntity, Integer> {
    public List<EmployeeEntity> getAll(){
        TypedQuery<EmployeeEntity> namedQuery = em.createNamedQuery("EmployeeEntity.getAll", EmployeeEntity.class);
        return namedQuery.getResultList();
    }

}
