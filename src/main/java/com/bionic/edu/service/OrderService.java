package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Orders findById(int id);

    List<Orders> findAll();

    void add(Orders order);

    void update(Orders order);

    void delete(int id);


    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();


    /*  1 = NOT_READY
        2 = READY_FOR_SHIPMENT
        3 = DELIVERING
        4 = DONE
    */
    void setOrderStatus(Orders order, int statusId);

    void submitByCustomer(Customer customer, Map<Dish, Integer> dishAmount);
}
