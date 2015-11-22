package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrderDishesService {

    OrderDishes findById(int id);

    List<OrderDishes> findAll();

    void save(OrderDishes orderDishes);

    void delete(int id);


    void addOrderDishes(Orders order, Map<Dish, Integer> cartMap);

    void addOrderDishesCustomer(Customer customer, Orders order, Map<Dish, Integer> cartMap);

    void setDishReady(int orderDishesId);

    List<OrderDishes> getAllDishesFromOrder(int orderId);

    List<OrderDishes> getUndoneDishesFromOrder(int orderId);

    List<OrderDishes> getKitchenPendingList();
}
