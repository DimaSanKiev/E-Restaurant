package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;

import java.util.List;

public interface DishService {

    Dish findById(int id);

    Dish findByEmail(String email);

    List<Dish> findAll();

    void add(Dish customer);

    void update(Dish customer);
}
