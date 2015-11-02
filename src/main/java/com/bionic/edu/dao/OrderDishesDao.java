package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;

import java.util.List;

public interface OrderDishesDao {

    OrderDishes findById(int id);

    List<OrderDishes> findAll();

    void save(OrderDishes orderDishes);

    void delete(int id);


    List<OrderDishes> getAllFromOrder(Orders order);

    List<OrderDishes> getKitchenPendingList();
}
