package com.bionic.edu.dao;

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
import static org.junit.Assert.assertNull;

public class OrderDaoImplTest {

    OrderDao orderDao;
    ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        orderDao = (OrderDao) context.getBean("orderDaoImpl");
    }

    @Test
    public void testFindById() throws Exception {
        Orders order = orderDao.findById(1);
        assertNotNull(order);
        assertEquals(1, order.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Orders> orders = orderDao.findAll();
        assertNotNull(orders);
        assertEquals(5, orders.size());
    }

    @Test
    public void testAdd() throws Exception {
        Orders order = orderDao.findById(1);
        orderDao.add(order);
        int id = order.getId();
        assertNotNull(orderDao.findById(id));
    }

    @Test
    public void testUpdate() throws Exception {
        Orders order = orderDao.findById(1);
        // todo - NPE
        order.setCustomer(new CustomerDaoImpl().findById(1));
        orderDao.update(order);
        assertEquals(1, order.getCustomer().getId());
        assertEquals("olga.romanova@gmail.com", order.getCustomer().getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        Orders order = orderDao.findById(2);
        orderDao.add(order);
        int id = order.getId();
        orderDao.delete(id);
        assertNull(orderDao.findById(id));

    }

    @Test
    public void testGetDeliveryListByTime() throws Exception {
        List<Orders> orders = orderDao.getDeliveryListByTime();
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    @Test
    public void testGetDeliveryListByStatus() throws Exception {
        List<Orders> orders = orderDao.getDeliveryListByStatus();
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    @Test
    //todo - You have attempted to set a value of type class java.lang.Integer for parameter
    //order_status_id with expected type of class com.bionic.edu.entity.OrderStatus from query
    //string UPDATE Orders o SET o.orderStatus = :order_status_id WHERE o.id = :order_id.
    public void testSetOrderStatus() throws Exception {
        Orders order = orderDao.findById(1);
        orderDao.setOrderStatus(order, 2);
        orderDao.add(order);
        List<Orders> orders = orderDao.getDeliveryListByStatus();
        assertEquals(1, orders.size());
    }

    @Test
    // todo NPE
    public void testSubmitByCustomer() throws Exception {
        Customer customer = new CustomerDaoImpl().findById(1);
        Dish dish = new DishDaoImpl().findById(1);
        Map<Dish, Integer> dishesQuantity = new HashMap<>();
        dishesQuantity.put(dish, 2);
        orderDao.submitByCustomer(customer, dishesQuantity);
    }
}