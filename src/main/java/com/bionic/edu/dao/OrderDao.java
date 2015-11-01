package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.Report;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    Orders findById(int id);

    List<Orders> findAll();

    void save(Orders order);

    void delete(int id);


    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();

    void submitByCustomer(Customer customer, Map<Dish, Integer> dishAmount);


    List<Report> getReport(Timestamp startPeriod, Timestamp endPeriod);

    List<Report> getReport(Timestamp startPeriod, Timestamp endPeriod, String category);
}
