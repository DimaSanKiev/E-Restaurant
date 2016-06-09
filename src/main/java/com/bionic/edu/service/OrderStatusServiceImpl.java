package com.bionic.edu.service;

import com.bionic.edu.dao.OrderStatusDao;
import com.bionic.edu.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
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
