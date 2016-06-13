package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.service.generic.GenericService;

import java.util.List;
import java.util.Map;

public interface OrderDishesService extends GenericService<OrderDishes> {

    void checkIfOrderReady(Orders order);

    void addOrderDishes(Orders order, Map<Dish, Integer> cartMap);

    void addOrderDishesCustomer(Customer customer, Orders order, Map<Dish, Integer> cartMap);

    void markDone(int orderDishId);


    List<OrderDishes> getAllDishesFromOrder(int orderId);

    List<OrderDishes> getUndoneDishesFromOrder(int orderId);

    List<OrderDishes> getKitchenPendingList();
}
