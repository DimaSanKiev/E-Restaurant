package com.bionic.edu.service;

import com.bionic.edu.dao.OrderStatusDao;
import com.bionic.edu.entity.OrderStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class OrderStatusServiceImpl implements OrderStatusService {

    @Inject
    private OrderStatusDao orderStatusDao;

    @Override
    public OrderStatus findById(int id) {
        return orderStatusDao.findById(id);
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusDao.findAll();
    }

    @Transactional
    @Override
    public void save(OrderStatus orderStatus) {
        orderStatusDao.save(orderStatus);
    }

    @Transactional
    @Override
    public void delete(int id) {
        orderStatusDao.delete(id);
    }
}
