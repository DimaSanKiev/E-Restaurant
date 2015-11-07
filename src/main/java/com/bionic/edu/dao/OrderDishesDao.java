package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderDishes;

import java.util.List;

public interface OrderDishesDao {

    OrderDishes findById(int id);

    List<OrderDishes> findAll();

    void save(OrderDishes orderDishes);

    void delete(int id);


    List<OrderDishes> getAllDishesFromOrder(int orderId);

    List<OrderDishes> getKitchenPendingList();
}
