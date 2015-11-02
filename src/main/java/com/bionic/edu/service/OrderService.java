package com.bionic.edu.service;

import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.Report;

import java.sql.Date;
import java.util.List;

public interface OrderService {

    Orders findById(int id);

    List<Orders> findAll();

    void save(Orders order);

    void delete(int id);


    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();

    List<Orders> getCustomersOrder(int customerId);


    List<Report> getReport(Date startPeriod, Date endPeriod);

    List<Report> getReport(Date startPeriod, Date endPeriod, String category);

}
