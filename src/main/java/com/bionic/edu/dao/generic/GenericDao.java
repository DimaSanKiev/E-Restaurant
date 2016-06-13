package com.bionic.edu.dao.generic;

import java.util.List;

public interface GenericDao<T> {

    T findById(int id);

    List<T> findAll();

    void save(T t);

    void delete(int id);
}
