package com.bionic.edu.service;

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
    OrderStatusService orderStatusService;
    DishService dishService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        orderService = context.getBean(OrderService.class);
        customerService = context.getBean(CustomerService.class);
        orderStatusService = context.getBean(OrderStatusService.class);
        dishService = context.getBean(DishService.class);
    }


    @Test
    public void testFindById() throws Exception {
        Orders order = orderService.findById(1);
        assertNotNull(order);
        assertEquals(1, order.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Orders> orders = orderService.findAll();
        assertNotNull(orders);
        assertEquals(5, orders.size());
    }

    @Test
    public void testAdd() throws Exception {
        Orders order = orderService.findById(1);
        int id = order.getId();
        orderService.save(order);
        assertNotNull(order.getId());
        assertEquals(id, order.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        Orders order = orderService.findById(1);
        order.setCustomer(customerService.findById(1));
        orderService.save(order);
        assertEquals(1, order.getCustomer().getId());
        assertEquals("olga.romanova@gmail.com", order.getCustomer().getEmail());
    }

    // todo - DELETE on table 'ORDERS' caused a violation of foreign key constraint 'ORDER_FK' for key (1)
    @Test
    public void testDelete() throws Exception {
        Orders order = orderService.findById(1);
        int id = order.getId();
        orderService.save(order);
        orderService.delete(id);
        assertEquals(null, orderService.findById(id));
    }

    @Test
    public void testGetDeliveryListByTime() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByTime();
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    public void testGetDeliveryListByStatus() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByStatus();
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    public void testSetOrderStatus() throws Exception {
        Orders order = orderService.findById(1);
        order.setOrderStatus(orderStatusService.findById(2));
        orderService.save(order);
        List<Orders> orders = orderService.getDeliveryListByStatus();
        assertEquals(1, orders.size());
    }

    // todo - No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
    @Test
    public void testSubmitByCustomer() throws Exception {
        Customer customer = customerService.findById(1);
        Dish dish = dishService.findById(1);
        Map<Dish, Integer> dishesQuantity = new HashMap<>();
        dishesQuantity.put(dish, 2);
        orderService.submitByCustomer(customer, dishesQuantity);
    }
}