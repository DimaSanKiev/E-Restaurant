package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDaoImpl;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Orders;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderServiceImplTest {
    OrderService orderService;
    CustomerService customerService;
    DishService dishService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        orderService = context.getBean(OrderService.class);
    }


    @Test
    public void testFindById() throws Exception {
        Orders order = orderService.findById(1);
        assertNotNull(order);
        assertEquals(1, order.getId());
    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        List<Orders> orders = orderService.findAll();
        assertNotNull(orders);
        assertEquals(5, orders.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Orders order = orderService.findById(1);
        order.setCustomer(new CustomerDaoImpl().findById(1));
        orderService.save(order);
        assertEquals(1, order.getCustomer().getId());
        assertEquals("olga.romanova@gmail.com", order.getCustomer().getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        Orders order = orderService.findById(1);
        orderService.save(order);
        int id = order.getId();
        orderService.delete(id);
        assertNotNull(orderService.findById(id));
    }

    @Test
    public void testGetDeliveryListByTime() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByTime();
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    @Test
    public void testGetDeliveryListByStatus() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByStatus();
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

//    todo - allMethods - find ORDERSTATUS_ID - ERROR 42X04: Column 'ORDERSTATUS_ID' is either not in any table in the FROM list or appears within...
    @Test
    public void testSetOrderStatus() throws Exception {
        Orders order = orderService.findById(1);
        orderService.setOrderStatus(1, 2);
        orderService.save(order);
        List<Orders> orders = orderService.getDeliveryListByStatus();
        assertEquals(1, orders.size());
    }

    @Test
    public void testSubmitByCustomer() throws Exception {
        Customer customer = customerService.findById(1);
        Dish dish = dishService.findById(1);
        Map<Dish, Integer> dishesQuantity = new HashMap<>();
        dishesQuantity.put(dish, 2);
        orderService.submitByCustomer(customer, dishesQuantity);
    }
}