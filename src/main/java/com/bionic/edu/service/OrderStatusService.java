package com.bionic.edu.service;

import com.bionic.edu.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    OrderStatus findById(int id);

    List<OrderStatus> findAll();

    void save(OrderStatus orderStatus);

    void delete(int id);

}
