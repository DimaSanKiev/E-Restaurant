package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.dao.OrderDao;
import com.bionic.edu.dao.OrderDishesDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class OrderDishesServiceImpl implements OrderDishesService {

    @Inject
    private OrderDishesDao orderDishesDao;
    @Inject
    private OrderDao orderDao;
    @Inject
    private OrderService orderService;
    @Inject
    private CustomerDao customerDao;

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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addOrderDishes(Orders order, Map<Dish, Integer> cartMap) {
        orderService.save(order);
        for (Dish dish : cartMap.keySet()) {
            OrderDishes orderDishes = new OrderDishes();
            orderDishes.setQuantity(cartMap.get(dish));
            orderDishes.setDish(dish);
            orderDishes.setOrder(order);
            orderDishes.setPrice(dish.getPrice());
            orderDishes.setReadiness(false);
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

    @Transactional
    @Override
    // todo - don't set readiness
    public void setDishReady(int orderDishId) {
        OrderDishes orderDish = orderDishesDao.findById(orderDishId);
//        System.out.println("OrderDishesService: READINESS is " + orderDish.isReadiness());
        orderDish.setReadiness(true);
        orderDishesDao.save(orderDish);
//        System.out.println("OrderDishesService: READINESS is " + orderDish.isReadiness());
        // checks if there are any undone dishes from the same order, if no - changes order_status to "READY_FOR_SHIPMENT"
//        Orders order = orderDao.findById(orderDishesDao.findById(orderDish.getId()).getOrder().getId());
//        List<OrderDishes> undoneDishes = getUndoneDishesFromOrder(order.getId());
//        if (undoneDishes.size() == 0) {
//            orderService.setOrderStatus(order.getId(), 3);
//            orderService.save(order);
//        }
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
