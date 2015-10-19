package com.bionic.edu.service;

import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.entity.Orders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
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
    public void add(Orders order) {
        orderDao.add(order);
    }

    @Override
    public void update(Orders order) {
        orderDao.update(order);
    }

    @Transactional
    @Override
    public void delete(int id) {
        orderDao.delete(id);
    }
}
