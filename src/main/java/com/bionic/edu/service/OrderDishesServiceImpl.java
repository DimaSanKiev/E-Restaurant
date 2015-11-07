package com.bionic.edu.service;

import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.dao.OrderDishesDao;
import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class OrderDishesServiceImpl implements OrderDishesService {

    @Inject
    private OrderDishesDao orderDishesDao;
    @Inject
    private OrderDao orderDao;
    @Inject
    private OrderService orderService;

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
    public void save(OrderDishes orderDishes) {
        orderDishesDao.save(orderDishes);
    }

    @Transactional
    @Override
    public void delete(int id) {
        orderDishesDao.delete(id);
    }

    @Transactional
    @Override
    public void setDishReady(int orderDishesId) {
        OrderDishes orderDish = orderDishesDao.findById(orderDishesId);
        orderDish.setReadiness(true);
        orderDishesDao.save(orderDish);
        // checks if there are any undone dishes from the same order, if no - changes order_status to "READY_FOR_SHIPMENT"
        Orders order = orderDao.findById(orderDishesDao.findById(orderDishesId).getOrder().getId());
        List<OrderDishes> undoneDishes = getUndoneDishesFromOrder(order.getId());
        if (undoneDishes.size() == 0) {
            orderService.setOrderStatus(order.getId(), 3);
            orderService.save(order);
        }
    }

    @Override
    public List<OrderDishes> getAllDishesFromOrder(int orderId) {
        return orderDishesDao.getAllDishesFromOrder(orderId);
    }

    @Override
    public List<OrderDishes> getUndoneDishesFromOrder(int orderId) {
        return orderDishesDao.getUndoneDishesFromOrder(orderId);
    }

    @Override
    public List<OrderDishes> getKitchenPendingList() {
        return orderDishesDao.getKitchenPendingList();
    }
}
