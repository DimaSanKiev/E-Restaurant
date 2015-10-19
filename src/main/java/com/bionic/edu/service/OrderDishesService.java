package com.bionic.edu.service;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;

import java.util.List;

public interface OrderDishesService {

    OrderDishes findById(int id);

    List<OrderDishes> findAll();

    void add(OrderDishes orderDishes);

    void update(OrderDishes orderDishes);

    void delete(int id);

    List<OrderDishes> getAllFromOrder(Orders order);

    void addKitchenmadeToOrder(OrderDishes orderDishes);

    List<OrderDishes> createListForKitchen();
}
