package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;

import java.util.List;

public interface DishService {

    Dish findById(int id);

    List<Dish> findAll();

    void add(Dish dish);

    void update(Dish dish);

    void delete(int id);
}
