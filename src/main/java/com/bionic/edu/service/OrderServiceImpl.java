package com.bionic.edu.service;

import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.Report;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Date;
import java.util.List;

@Named
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Override
    public Orders findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Orders> findAll() {
        return orderDao.findAll();
    }

    @Transactional
    @Override
    public void save(Orders order) {
        orderDao.save(order);
    }

    @Transactional
    @Override
    public void delete(int id) {
        orderDao.delete(id);
    }

    @Override
    public List<Orders> getDeliveryListByTime() {
        return orderDao.getDeliveryListByTime();
    }

    @Override
    public List<Orders> getDeliveryListByStatus() {
        return orderDao.getDeliveryListByStatus();
    }

    @Override
    public List<Orders> getCustomersOrder(int customerId) {
        return orderDao.getCustomersOrder(customerId);
    }


    @Override
    public List<Report> getReport(Date startPeriod, Date endPeriod) {
        return orderDao.getReport(startPeriod, endPeriod);
    }

    @Override
    public List<Report> getReport(Date startPeriod, Date endPeriod, String category) {
        return orderDao.getReport(startPeriod, endPeriod, category);
    }
}
