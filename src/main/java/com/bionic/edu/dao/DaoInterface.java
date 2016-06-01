package com.bionic.edu.dao;

import java.util.List;

public interface DaoInterface<T> {

    T findById(int id);

    List<T> findAll();

    void save(T t);

    void delete(int id);
}
