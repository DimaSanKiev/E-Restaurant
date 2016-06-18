package com.bionic.edu.service;

import com.bionic.edu.entity.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class OrderStatusServiceImplTest {

    private OrderStatusService orderStatusService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        orderStatusService = context.getBean(OrderStatusService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        OrderStatus orderStatus = orderStatusService.findById(1);
        assertNotNull(orderStatus);
        assertEquals(1, orderStatus.getId());
    }

    @Test
    public void findAllReturnsListSize() throws Exception {
        List<OrderStatus> orderStatuses = orderStatusService.findAll();
        orderStatuses.forEach(System.out::println);
        assertNotNull(orderStatuses);
        assertEquals(5, orderStatuses.size());
    }

    @Test
    public void addingOrderStatusChangesId() throws Exception {
        OrderStatus orderStatus = new OrderStatus("REFUNDED");
        int originalId = orderStatus.getId();
        orderStatusService.save(orderStatus);
        assertNotEquals(originalId, orderStatus.getId());
        orderStatusService.delete(orderStatus.getId());
    }

    @Test
    public void addingOrderStatusIncreasesListSize() throws Exception {
        List<OrderStatus> list1 = orderStatusService.findAll();
        OrderStatus orderStatus = new OrderStatus("REFUNDED");
        orderStatusService.save(orderStatus);
        List<OrderStatus> list2 = orderStatusService.findAll();
        assertEquals(1, list2.size() - list1.size());
        orderStatusService.delete(orderStatus.getId());
    }

    @Test
    public void deletingOrderStatusReturnsNull() throws Exception {
        OrderStatus orderStatus = new OrderStatus("REJECTED");
        orderStatusService.save(orderStatus);
        int id = orderStatus.getId();
        orderStatusService.delete(id);
        assertNull(orderStatusService.findById(id));
    }
}