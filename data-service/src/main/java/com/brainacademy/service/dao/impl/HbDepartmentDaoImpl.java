package com.brainacademy.service.dao.impl;

import com.brainacademy.service.dao.DepartmentDao;
import com.brainacademy.service.model.Department;
import org.hibernate.SessionFactory;

public class HbDepartmentDaoImpl
        extends AbstractDao<Department>
        implements DepartmentDao {

    public HbDepartmentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Department> getEntityType() {
        return Department.class;
    }
}
