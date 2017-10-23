package com.brainacademy.service.dao;

import com.brainacademy.service.dao.impl.HbDepartmentDaoImpl;
import com.brainacademy.service.dao.impl.HbEmployeeDaoImpl;
import com.brainacademy.service.utils.HibernateUtil;
import org.hibernate.SessionFactory;

public class DaoFactory {
    private static DaoFactory instance;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public <T extends GenericDao> T createDao(Class<T> type) {
        if (type.equals(EmployeeDao.class)) {
            return (T) new HbEmployeeDaoImpl(sessionFactory);
        } else if (type.equals(DepartmentDao.class)) {
            return (T) new HbDepartmentDaoImpl(sessionFactory);
        }
        return null;
    }
}
