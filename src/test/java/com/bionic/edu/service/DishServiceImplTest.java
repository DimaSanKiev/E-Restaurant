package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

@Ignore
public class DishServiceImplTest {
    DishService dishService;
    DishCategoryService dishCategoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/hibernate-context.xml");
        dishService = context.getBean(DishService.class);
        dishCategoryService = context.getBean(DishCategoryService.class);
    }

    @Test
    public void testFindById() throws Exception {
        Dish dish = dishService.findById(1);
        assertNotNull(dish);
        assertEquals(1, dish.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Dish> dishes = dishService.findAll();
        assertNotNull(dishes);
        assertEquals(19, dishes.size());
    }

//    @Test
//    public void testAdd() throws Exception {
//        Dish dish = new Dish("testDishAdd", "justTestDish", 1.20, true, true, "photo", dishCategoryService.findById(1));
//        System.out.println(dish);
//        dishService.save(dish);
//        int id = dish.getId();
//        assertNotNull(dishService.findById(id));
//    }

    @Test
    public void testUpdate() throws Exception {
        Dish dish = dishService.findById(1);
        dish.setName("Test Name");
        dishService.save(dish);
        assertEquals("Test Name", dish.getName());
    }

//    @Test
//    public void testDelete() throws Exception {
//        Dish dish = new Dish("testDishDelete", "justTestDish", 0.00, true, true, "photo", dishCategoryService.findById(1));
//        dishService.save(dish);
//        int id = dish.getId();
//        dishService.delete(id);
//        assertNull(dishService.findById(id));
//    }

    @Test
    public void testFindByCategory() throws Exception {
        List<Dish> dishes = dishService.findByCategory(1);
        assertNotNull(dishes);
        assertEquals(4, dishes.size());
    }

    @Test
    public void testFindByAvailabilityTrue() throws Exception {
        List<Dish> dishes = dishService.findByAvailability(true);
        assertEquals(18, dishes.size());
    }

    @Test
    public void testFindByAvailabilityFalse() throws Exception {
        List<Dish> dishes = dishService.findByAvailability(false);
        assertEquals(1, dishes.size());
    }

}