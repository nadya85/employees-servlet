package com.brainacademy.service.dao.impl;

import com.brainacademy.service.dao.EmployeeDao;
import com.brainacademy.service.model.Employee;
import org.hibernate.SessionFactory;

public class HbEmployeeDaoImpl
        extends AbstractDao<Employee>
        implements EmployeeDao {

    public HbEmployeeDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Employee> getEntityType() {
        return Employee.class;
    }
}
