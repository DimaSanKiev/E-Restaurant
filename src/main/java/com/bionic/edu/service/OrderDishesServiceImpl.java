package com.bionic.edu.service;

import com.bionic.edu.dao.OrderDishesDao;
import com.bionic.edu.entity.OrderDishes;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class OrderDishesServiceImpl implements OrderDishesService {

    @Inject
    private OrderDishesDao orderDishesDao;

    @Override
    public OrderDishes findById(int id) {
        return orderDishesDao.findById(id);
    }

    @Override
    public List<OrderDishes> findAll() {
        return orderDishesDao.findAll();
    }

    @Transactional
    @Override
    public void add(OrderDishes orderDishes) {
        orderDishesDao.add(orderDishes);
    }

    @Override
    public void update(OrderDishes orderDishes) {
        orderDishesDao.update(orderDishes);
    }

    @Transactional
    @Override
    public void delete(int id) {
        orderDishesDao.delete(id);
    }
}
