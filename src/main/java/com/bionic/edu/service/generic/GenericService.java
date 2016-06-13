package com.bionic.edu.service.generic;

import java.util.List;

public interface GenericService<T> {

    T findById(int id);

    List<T> findAll();

    void save(T t);

    void delete(int id);
}
