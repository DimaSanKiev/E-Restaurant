package com.bionic.edu.service;

import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;

import java.sql.Date;
import java.util.List;

public interface OrderService {

    Orders findById(int id);

    List<Orders> findAll();

    void save(Orders order);

    void delete(int id);


    void setOrderStatus(int orderId, int statusId);

    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();

    List<Orders> getCustomersOrder(int customerId);


    List<ReportTotal> getReportTotal(Date startPeriod, Date endPeriod);

    List<ReportCategory> getReportCategory(Date startPeriod, Date endPeriod);

}
