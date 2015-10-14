package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderDishes;

import java.util.List;

public interface OrderDishesDao {

    OrderDishes findById(int id);

    OrderDishes findByEmail(String email);

    List<OrderDishes> findAll();

    void add(OrderDishes customer);

    void update(OrderDishes customer);
}
