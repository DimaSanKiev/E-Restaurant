package com.bionic.edu.service;

import com.bionic.edu.entity.OrderStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class OrderStatusServiceImplTest {

    OrderStatusService orderStatusService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/hibernate-context.xml");
        orderStatusService = context.getBean(OrderStatusService.class);
    }

    @Test
    public void testFindById() throws Exception {
        OrderStatus orderStatus = orderStatusService.findById(1);
        assertNotNull(orderStatus);
        assertEquals(1, orderStatus.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<OrderStatus> orderStatuses = orderStatusService.findAll();
        assertNotNull(orderStatuses);
        assertEquals(5, orderStatuses.size());
    }

    @Test
    public void testSave() throws Exception {
        OrderStatus orderStatus = new OrderStatus("REFUNDED");
        orderStatusService.save(orderStatus);
        int id = orderStatus.getId();
        assertNotNull(orderStatusService.findById(id));
        orderStatusService.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        OrderStatus orderStatus = new OrderStatus("REJECTED");
        orderStatusService.save(orderStatus);
        int id = orderStatus.getId();
        orderStatusService.delete(id);
        assertNull(orderStatusService.findById(id));
    }
}