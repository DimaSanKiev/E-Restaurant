package com.bionic.edu.service;

import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

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
    public void setOrderStatus(Orders order, int statusId) {
        orderDao.setOrderStatus(order, statusId);
    }

    @Override
    public void submitByCustomer(Customer customer, Map<Dish, Integer> dishAmount) {
        orderDao.submitByCustomer(customer, dishAmount);
    }
}
