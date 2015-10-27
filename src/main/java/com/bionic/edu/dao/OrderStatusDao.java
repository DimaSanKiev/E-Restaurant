package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    OrderStatus findById(int id);

    List<OrderStatus> findAll();

    void save(OrderStatus orderStatus);

    void delete(int id);

}
