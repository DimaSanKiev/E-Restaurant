package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class DishServiceImplTest {

    private DishService dishService;
    private DishCategoryService dishCategoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
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
        assertEquals(18, dishes.size());
    }

    @Test
    public void testAdd_notNull() throws Exception {
        Path path = Paths.get("/Users/Dima/Java/Projects/E-Restaurant/src/main/webapp/resources/images/test_image.png");
        Dish dish = new Dish("testDishAdd", "justTestDish", 1.20, true, true, Files.readAllBytes(path), dishCategoryService.findById(1));
        dishService.save(dish);
        int id = dish.getId();
        assertNotNull(dishService.findById(id));
    }

    @Test
    public void testAdd_listSize() throws Exception {
        List<Dish> list1 = dishService.findAll();
        Path path = Paths.get("/Users/Dima/Java/Projects/E-Restaurant/src/main/webapp/resources/images/test_image.png");
        Dish dish = new Dish("testDishAdd", "justTestDish", 1.20, true, true, Files.readAllBytes(path), dishCategoryService.findById(1));
        dishService.save(dish);
        List<Dish> list2 = dishService.findAll();
        assertEquals(list2.size() - list1.size(), 1);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish dish = dishService.findById(1);
        dish.setName("Test Name");
        dishService.save(dish);
        assertEquals("Test Name", dish.getName());
    }

    @Test
    public void testDelete() throws Exception {
        Path path = Paths.get("/Users/Dima/Java/Projects/E-Restaurant/src/main/webapp/resources/images/test_image.png");
        Dish dish = new Dish("testDishDelete", "justTestDish", 0.00, true, true, Files.readAllBytes(path), dishCategoryService.findById(1));
        dishService.save(dish);
        int id = dish.getId();
        dishService.delete(id);
        assertNull(dishService.findById(id));
    }

    @Test
    public void testFindByCategory() throws Exception {
        List<Dish> dishes = dishService.findByCategory(1);
        assertNotNull(dishes);
        assertEquals(3, dishes.size());
    }

    @Test
    public void testFindByAvailabilityTrue() throws Exception {
        List<Dish> dishes = dishService.findByAvailability(true);
        assertEquals(17, dishes.size());
    }

    @Test
    public void testFindByAvailabilityFalse() throws Exception {
        List<Dish> dishes = dishService.findByAvailability(false);
        assertEquals(1, dishes.size());
    }

}