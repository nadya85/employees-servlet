package com.brainacademy.service.dao;

import java.util.List;

public interface GenericDao<T> {
    T create(T entity);

    void update(T entity);

    void delete(T entity);

    T getOne(int id);

    List<T> getAll();
}