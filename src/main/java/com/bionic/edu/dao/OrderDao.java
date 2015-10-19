package com.bionic.edu.dao;

import com.bionic.edu.entity.Orders;

import java.util.List;

public interface OrderDao {

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
}
