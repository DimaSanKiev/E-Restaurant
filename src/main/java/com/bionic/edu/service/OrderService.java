package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.Report;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Orders findById(int id);

    List<Orders> findAll();

    void save(Orders order);

    void delete(int id);


    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();

    void submitByCustomer(Customer customer, Map<Dish, Integer> dishAmount);

    
    List<Report> getReport(LocalDateTime startPeriod, LocalDateTime endPeriod);

    List<Report> getReport(LocalDateTime startPeriod, LocalDateTime endPeriod, String category);

}
