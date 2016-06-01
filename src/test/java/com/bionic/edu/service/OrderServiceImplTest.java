package com.bionic.edu.service;

import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class OrderServiceImplTest {
    OrderService orderService;
    CustomerService customerService;
    OrderStatusService orderStatusService;
    DishCategoryService dishCategoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/hibernate-context.xml");
        orderService = context.getBean(OrderService.class);
        customerService = context.getBean(CustomerService.class);
        orderStatusService = context.getBean(OrderStatusService.class);
        dishCategoryService = context.getBean(DishCategoryService.class);
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
        assertEquals(7, orders.size());
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

    // DELETE on table 'ORDERS' caused a violation of foreign key constraint 'ORDER_FK' for key (1)
    @Ignore
    @Test
    public void testDelete() throws Exception {
        Orders order = orderService.findById(1);
        int id = order.getId();
        orderService.save(order);
        orderService.delete(id);
        assertEquals(null, orderService.findById(id));
    }


    @Test
    public void testSetOrderStatus() throws Exception {
        Orders order = orderService.findById(1);
        System.out.println(order.getOrderStatus());
        orderService.setOrderStatus(1, 2);
        assertEquals(2, orderService.findById(1).getOrderStatus().getId());
    }

    @Test
    public void testGetDeliveryListByTime() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByTime();
        orders.forEach(System.out::println);
        assertNotNull(orders);
        assertEquals(5, orders.size());
    }

    @Test
    public void testGetDeliveryListByStatus() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByStatus();
        orders.forEach(System.out::println);
        assertNotNull(orders);
        assertEquals(5, orders.size());
    }

    @Test
    public void testGetCustomersOrder() throws Exception {
        List<Orders> orders = orderService.getCustomersOrder(3);
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    public void testGetReportPeriod() throws Exception {
        List<ReportTotal> reports = orderService.getReportTotal(Date.valueOf("2015-12-01"), Date.valueOf("2015-12-15"));
        reports.forEach(System.out::println);
        assertNotNull(reports);
        assertEquals(0, reports.size());
    }

    @Test
    public void testGetReportCategory() throws Exception {
        List<ReportCategory> reports = orderService.getReportCategory(Date.valueOf("2015-12-01"), Date.valueOf("2015-12-15"));
        reports.forEach(System.out::println);
        assertNotNull(reports);
        assertEquals(0, reports.size());
    }
}