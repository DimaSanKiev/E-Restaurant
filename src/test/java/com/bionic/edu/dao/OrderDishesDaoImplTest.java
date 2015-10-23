package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDishesDaoImplTest {

    OrderDishesDao orderDishesDao;
    ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        orderDishesDao = (OrderDishesDao) context.getBean("orderDishesDaoImpl");
    }

    @Test
    public void testFindById() throws Exception {
        OrderDishes orderDishes = orderDishesDao.findById(1);
        assertNotNull(orderDishes);
        assertEquals(1, orderDishes.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<OrderDishes> ordersDishes = orderDishesDao.findAll();
        assertNotNull(ordersDishes);
        assertEquals(5, ordersDishes.size());
    }

    @Test
    public void testAdd() throws Exception {
        OrderDishes orderDishes = orderDishesDao.findById(1);
        orderDishesDao.add(orderDishes);
        int id = orderDishes.getId();
        assertNotNull(orderDishesDao.findById(id));
    }

    @Test
    public void testUpdate() throws Exception {
        OrderDishes orderDishes = orderDishesDao.findById(1);
        orderDishes.setPrice(10.00);
        orderDishesDao.save(orderDishes);
        assertEquals(10.00, orderDishes.getPrice(), 0.00);
    }

    @Test
    public void testDelete() throws Exception {
        OrderDishes orderDishes = orderDishesDao.findById(2);
        orderDishesDao.add(orderDishes);
        int id = orderDishes.getId();
        orderDishesDao.delete(id);
        assertNull(orderDishesDao.findById(id));

    }

    @Test
    public void testGetAllFromOrder() throws Exception {
        Orders order = new OrderDaoImpl().findById(1);
        List<OrderDishes> orderDishesList = orderDishesDao.getAllFromOrder(order);
        assertNotNull(orderDishesList);
        assertEquals(2, orderDishesList.size());
    }

    @Test
    public void testAddKitchenmadeToOrder() throws Exception {

    }

    @Test
    public void testCreateListForKitchen() throws Exception {

    }
}