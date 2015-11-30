package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Orders findById(int id);

    List<Orders> findAll();

    void save(Orders order);

    void delete(int id);


    void addFromCart(Map<Dish, Integer> cartMap, Customer customer, double sum);

    void setOrderStatus(int orderId, int statusId);


    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();

    List<Orders> getCustomersOrder(int customerId);


    List<ReportTotal> getReportTotal(Date startPeriod, Date endPeriod);

    List<ReportCategory> getReportCategory(Date startPeriod, Date endPeriod);
}
