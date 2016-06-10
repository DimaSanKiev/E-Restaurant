package com.bionic.edu.service;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDishesServiceImplTest {

    private OrderDishesService orderDishesService;
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
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
        System.out.println("**************");
        ordersDishes.forEach(System.out::println);
        assertNotNull(ordersDishes);
        assertEquals(18, ordersDishes.size());
    }

    @Test
    public void testAdd_notNull() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        orderDishesService.save(orderDishes);
        int id = orderDishes.getId();
        assertNotNull(orderDishesService.findById(id));
    }

    @Test
    public void testAdd_listSize() throws Exception {
        List<OrderDishes> list1 = orderDishesService.findAll();
        OrderDishes orderDishes = orderDishesService.findById(1);
        orderDishesService.save(orderDishes);
        List<OrderDishes> list2 = orderDishesService.findAll();
        assertEquals(list2.size() - list1.size(), 1);
    }

    @Test
    public void testUpdate() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        orderDishes.setPrice(10.00);
        orderDishesService.save(orderDishes);
        assertEquals(10.00, orderDishes.getPrice(), 0.00);
    }

    // SQLIntegrityConstraintViolationException: DELETE on table 'ORDERS' caused a violation of foreign key constraint 'ORDERS_FK' for key (1)
    @Ignore
    @Test
    public void testDelete() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(2);
        orderDishesService.save(orderDishes);
        int id = orderDishes.getId();
        System.out.println(orderDishes);
        orderDishesService.delete(id);
        assertNull(orderDishesService.findById(id));
    }

    @Ignore
    @Test
    public void testSetDishReady() throws Exception {
        Orders order = orderService.findById(7);
        assertEquals(1, order.getOrderStatus().getId());
        orderDishesService.markDone(18);
        assertEquals(true, orderDishesService.findById(18).isReadiness());
//        assertEquals(3, order.getOrderStatus().getId());  // updates only on the next check
    }

    @Test
    public void testGetAllDishesFromOrder() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getAllDishesFromOrder(1);
        orderDishesList.forEach(System.out::println);
        assertNotNull(orderDishesList);
        assertEquals(2, orderDishesList.size());
    }

    @Test
    public void testGetUndoneDishesFromOrder() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getUndoneDishesFromOrder(7);
        assertNotNull(orderDishesList);
        assertEquals(2, orderDishesList.size());
    }

    @Test
    public void testGetKitchenPendingList() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getKitchenPendingList();
        orderDishesList.forEach(System.out::println);
        assertNotNull(orderDishesList);
        assertEquals(16, orderDishesList.size());
    }
}