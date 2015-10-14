package com.bionic.edu.dao;

import com.bionic.edu.entity.Order;

import java.util.List;

public interface OrderDao {

    Order findById(int id);

    Order findByEmail(String email);

    List<Order> findAll();

    void add(Order customer);

    void update(Order customer);
}
