package com.bionic.edu.service;

import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.dao.OrderStatusDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportDish;
import com.bionic.edu.util.ReportTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderStatusDao orderStatusDao;
    @Autowired
    private OrderDishesService orderDishesService;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addFromCart(Map<Dish, Integer> cartMap, Customer customer, double sum) {
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setTotalPrice(sum);
        order.setDateTimeTaken(new Timestamp(new java.util.Date().getTime()));
        order.setOrderStatus(orderStatusDao.findById(1));
//        orderDishesService.checkIfOrderReady(order);
        if (customer.getId() == 0) {
            orderDishesService.addOrderDishes(order, cartMap);
        } else {
            orderDishesService.addOrderDishesCustomer(customer, order, cartMap);
        }
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
    public List<ReportTotal> getReportTotal(Date startPeriod, Date endPeriod) {
        return orderDao.getReportTotal(startPeriod, endPeriod);
    }

    @Override
    public List<ReportCategory> getReportCategory(Date startPeriod, Date endPeriod) {
        return orderDao.getReportCategory(startPeriod, endPeriod);
    }

    @Override
    public List<ReportDish> getReportDish(Date startPeriod, Date endPeriod) {
        return orderDao.getReportDish(startPeriod, endPeriod);
    }
}
