package com.bionic.edu.service;

import com.bionic.edu.entity.OrderDishes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDishesServiceImplTest {
    OrderDishesService orderDishesService;
    OrderService orderService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        orderDishesService = context.getBean(OrderDishesService.class);
        orderService = context.getBean(OrderService.class);
    }

    @Test
    public void testFindById() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        assertNotNull(orderDishes);
        assertEquals(1, orderDishes.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<OrderDishes> ordersDishes = orderDishesService.findAll();
        assertNotNull(ordersDishes);
        assertEquals(6, ordersDishes.size());
    }

    @Test
    public void testAdd() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        orderDishesService.save(orderDishes);
        int id = orderDishes.getId();
        assertNotNull(orderDishesService.findById(id));
    }

    @Test
    public void testUpdate() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        orderDishes.setPrice(10.00);
        orderDishesService.save(orderDishes);
        assertEquals(10.00, orderDishes.getPrice(), 0.00);
    }

    // todo - SQLIntegrityConstraintViolationException: DELETE on table 'ORDERS' caused a violation of foreign key constraint 'ORDERS_FK' for key (1)
    @Test
    public void testDelete() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(2);
        orderDishesService.save(orderDishes);
        int id = orderDishes.getId();
        orderDishesService.delete(id);
        assertNull(orderDishesService.findById(id));
    }


    @Test
    public void testGetAllDishesFromOrder() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getAllDishesFromOrder(1);
        orderDishesList.forEach(System.out::println);
        assertNotNull(orderDishesList);
        assertEquals(2, orderDishesList.size());
    }

    @Test
    public void testGetKitchenPendingList() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getKitchenPendingList();
        orderDishesList.forEach(System.out::println);
        assertNotNull(orderDishesList);
        assertEquals(4, orderDishesList.size());
    }
}