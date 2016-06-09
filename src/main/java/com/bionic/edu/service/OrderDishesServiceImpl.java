package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.dao.OrderDishesDao;
import com.bionic.edu.dao.OrderStatusDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderDishesServiceImpl implements OrderDishesService {

    @Autowired
    private OrderDishesDao orderDishesDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderStatusDao orderStatusDao;

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

    // todo - check the combined order with kitchendone and non-kitchendone dishes
    @Transactional
    @Override
    // checks if there are any undone dishes from the same order, if no - changes order_status to "READY_FOR_SHIPMENT"
    public void checkIfOrderReady(Orders order) {
        List<OrderDishes> undoneDishes = getUndoneDishesFromOrder(order.getId());
        if (undoneDishes.isEmpty()) {
            order.setOrderStatus(orderStatusDao.findById(3));
            orderDao.save(order);
        } else
            order.setOrderStatus(orderStatusDao.findById(2));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addOrderDishes(Orders order, Map<Dish, Integer> cartMap) {
        orderDao.save(order);
        for (Dish dish : cartMap.keySet()) {
            OrderDishes orderDishes = new OrderDishes();
            orderDishes.setQuantity(cartMap.get(dish));
            orderDishes.setDish(dish);
            orderDishes.setOrder(order);
            orderDishes.setPrice(dish.getPrice());
            if (!orderDishes.getDish().isKitchenmade()) {
                orderDishes.setReadiness(true);
                checkIfOrderReady(order);
            } else {
                orderDishes.setReadiness(false);
            }
            orderDishesDao.save(orderDishes);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addOrderDishesCustomer(Customer customer, Orders order, Map<Dish, Integer> cartMap) {
        customerDao.save(customer);
        order.setCustomer(customer);
        addOrderDishes(order, cartMap);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void markDone(int orderDishId) {
        OrderDishes orderDish = orderDishesDao.findById(orderDishId);
        orderDish.setReadiness(true);
        checkIfOrderReady(orderDao.findById(orderDishesDao.findById(orderDishId).getOrder().getId()));
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
