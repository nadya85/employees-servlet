package com.brainacademy.service.dao;

import com.brainacademy.service.model.Employee;

import java.util.List;

public interface GenericDao<T> {
    T create(T entity);

    void update(T entity);

    void delete(T entity);

    T getOne(int id);

    List<T> getAll();

    List<T> getAll(int page);

    int count();
}