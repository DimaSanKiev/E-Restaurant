package com.bionic.edu.service;

import com.bionic.edu.entity.Order;

import java.util.List;

public interface OrderService {

    Order findById(int id);

    List<Order> findAll();

    void add(Order order);

    void update(Order order);

    void delete(int id);
}
