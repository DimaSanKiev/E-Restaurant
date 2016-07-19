package com.bionic.edu.service;

import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportDish;
import com.bionic.edu.util.ReportTotal;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static java.time.LocalDate.now;
import static org.junit.Assert.*;

public class OrderServiceImplTest {

    private OrderService orderService;
    private CustomerService customerService;
    private OrderStatusService orderStatusService;
    private DishCategoryService dishCategoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        orderService = context.getBean(OrderService.class);
        customerService = context.getBean(CustomerService.class);
        orderStatusService = context.getBean(OrderStatusService.class);
        dishCategoryService = context.getBean(DishCategoryService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        Orders order = orderService.findById(1);
        assertNotNull(order);
        assertEquals(1, order.getId());
    }

    @Test
    public void findAllListSize() throws Exception {
        List<Orders> orders = orderService.findAll();
        orders.forEach(System.out::println);
        assertNotNull(orders);
        assertEquals(12, orders.size());
    }

    @Test
    public void addingOrderSetsId() throws Exception {
        Orders order = createTestOrder();
        int originalId = order.getId();
        orderService.save(order);
        assertNotEquals(originalId, order.getId());
        orderService.delete(order.getId());
    }

    @Test
    public void addingOrderIncreasesListSize() throws Exception {
        List<Orders> list1 = orderService.findAll();
        Orders order = createTestOrder();
        orderService.save(order);
        List<Orders> list2 = orderService.findAll();
        assertEquals(1, list2.size() - list1.size());
        orderService.delete(order.getId());
    }

    @Test
    public void updatingOrderChangesCustomer() throws Exception {
        Orders order = createTestOrder();
        order.setCustomer(customerService.findById(2));
        orderService.save(order);
        assertEquals(2, order.getCustomer().getId());
        assertEquals("igor.shevchenko@yahoo.com", order.getCustomer().getEmail());
        orderService.delete(order.getId());
    }

    @Test
    public void deletingOrder() throws Exception {
        Orders order = createTestOrder();
        orderService.save(order);
        orderService.delete(order.getId());
        assertNull(orderService.findById(order.getId()));
    }

    @Test
    public void gettingDeliveryListByTimeReturnsList() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByTime();
        orders.forEach(System.out::println);
        assertNotNull(orders);
        assertEquals(3, orders.size());
    }

    @Test
    public void gettingDeliveryListByStatusReturnsList() throws Exception {
        List<Orders> orders = orderService.getDeliveryListByStatus();
        orders.forEach(System.out::println);
        assertNotNull(orders);
        assertEquals(3, orders.size());
    }

    @Test
    public void gettingCustomerOrderReturnsList() throws Exception {
        List<Orders> orders = orderService.getCustomersOrder(3);
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    public void gettingTotalReport_FromWeekAgoTillNow() throws Exception {
        List<ReportTotal> reports = orderService.getReportTotal(Date.valueOf(now().minusDays(7)), Date.valueOf(now()));
        reports.forEach(System.out::println);
        assertNotNull(reports);
        assertEquals(7, reports.size());
    }

    @Test
    public void gettingReportByCategory_FromWeekAgoTillNow() throws Exception {
        List<ReportCategory> reports = orderService.getReportCategory(Date.valueOf(now().minusDays(7)), Date.valueOf(now()));
        reports.forEach(System.out::println);
        assertNotNull(reports);
        assertEquals(5, reports.size());
    }

    @Test
    public void gettingReportByDishes_FromWeekAgoTillNow() throws Exception {
        List<ReportDish> reports = orderService.getReportDish(Date.valueOf(now().minusDays(7)), Date.valueOf(now()));
        reports.forEach(System.out::println);
        assertNotNull(reports);
        assertEquals(14, reports.size());
    }

    private Orders createTestOrder() {
        Orders order = new Orders();
        order.setCustomer(customerService.findById(1));
        order.setDateTimeTaken(new Timestamp(new java.util.Date().getTime() - 3600 * 1000));
        order.setDateTimeDelivered(new Timestamp(new java.util.Date().getTime()));
        order.setOrderStatus(orderStatusService.findById(1));
        return order;
    }
}