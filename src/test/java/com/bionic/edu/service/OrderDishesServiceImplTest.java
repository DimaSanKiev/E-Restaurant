package com.bionic.edu.service;

import com.bionic.edu.entity.OrderDishes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDishesServiceImplTest {

    private OrderDishesService orderDishesService;
    private OrderService orderService;
    private DishService dishService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        orderDishesService = context.getBean(OrderDishesService.class);
        orderService = context.getBean(OrderService.class);
        dishService = context.getBean(DishService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        assertNotNull(orderDishes);
        assertEquals(1, orderDishes.getId());
    }

    @Test
    public void findAllReturnsListSize() throws Exception {
        List<OrderDishes> ordersDishes = orderDishesService.findAll();
//        System.out.println("**************");
//        ordersDishes.forEach(System.out::println);
        assertNotNull(ordersDishes);
        assertEquals(38, ordersDishes.size());
    }

    @Test
    public void addingOrderDishIncreasesListSize() throws Exception {
        List<OrderDishes> list1 = orderDishesService.findAll();
        OrderDishes orderDish = createTestOrderDish(true, 6);
        orderDishesService.save(orderDish);
        List<OrderDishes> list2 = orderDishesService.findAll();
        assertEquals(1, list2.size() - list1.size());
        orderDishesService.delete(orderDish.getId());
    }

    @Test
    public void updatingOrderDishChangesPrice() throws Exception {
        OrderDishes orderDishes = orderDishesService.findById(1);
        orderDishes.setPrice(10.00);
        orderDishesService.save(orderDishes);
        assertEquals(10.00, orderDishes.getPrice(), 0.00);
    }

    @Test
    public void testDelete() throws Exception {
        OrderDishes orderDishes = createTestOrderDish(true, 6);
        orderDishesService.save(orderDishes);
        int id = orderDishes.getId();
        System.out.println(orderDishes);
        orderDishesService.delete(id);
        assertNull(orderDishesService.findById(id));
    }

    @Test
    public void testThatOrderReadyForShippingWhenAllItsDishesDone() throws Exception {
        orderDishesService.markDone(20);
        assertEquals(3, orderService.findById(7).getOrderStatus().getId());
    }

    @Test
    public void gettingAllDishesFromOrderReturnsList() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getAllDishesFromOrder(5);
        assertNotNull(orderDishesList);
        assertEquals(6, orderDishesList.size());
    }

    @Test
    public void gettingAllUndoneDishesFromOrderReturnsList() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getUndoneDishesFromOrder(5);
        assertNotNull(orderDishesList);
        assertEquals(2, orderDishesList.size());
    }

    @Test
    public void gettingKitchenPendingList() throws Exception {
        List<OrderDishes> orderDishesList = orderDishesService.getKitchenPendingList();
        assertNotNull(orderDishesList);
        assertEquals(4, orderDishesList.size());
    }

    private OrderDishes createTestOrderDish(boolean readiness, int orderId) {
        return new OrderDishes(1, 4.30, readiness, dishService.findById(9), orderService.findById(orderId));
    }
}