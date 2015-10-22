package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    Orders findById(int id);

    List<Orders> findAll();

    void add(Orders order); // save()

    void update(Orders order); // dell

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
