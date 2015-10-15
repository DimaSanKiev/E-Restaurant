package com.bionic.edu.dao;

import com.bionic.edu.entity.Order;

import java.util.List;

public interface OrderDao {

    Order findById(int id);

    List<Order> findAll();

    void add(Order order);

    void update(Order order);

    void delete(int id);
}
