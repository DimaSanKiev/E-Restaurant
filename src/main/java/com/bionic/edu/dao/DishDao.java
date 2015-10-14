package com.bionic.edu.dao;

import com.bionic.edu.entity.Dish;

import java.util.List;

public interface DishDao {

    Dish findById(int id);

    Dish findByEmail(String email);

    List<Dish> findAll();

    void add(Dish customer);

    void update(Dish customer);
}
