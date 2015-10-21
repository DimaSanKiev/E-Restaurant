package com.bionic.edu.dao;

import com.bionic.edu.entity.Orders;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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

    }

    @Test
    public void testGetDeliveryListByStatus() throws Exception {

    }

    @Test
    public void testSetOrderStatus() throws Exception {

    }

    @Test
    public void testSubmitByCustomer() throws Exception {

    }
}