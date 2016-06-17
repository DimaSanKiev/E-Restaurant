package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.Photo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class DishServiceImplTest {

    private String pathPrefixWin = "E:\\Dima\\Information\\IT\\MyProjects\\toGitHub\\E-Restaurant\\src\\main\\webapp\\resources\\images\\general\\";
    private String pathPrefixMac = "/Users/Dima/Java/Projects/E-Restaurant/src/main/webapp/resources/images/general/";
    private DishService dishService;
    private DishCategoryService dishCategoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        dishService = context.getBean(DishService.class);
        dishCategoryService = context.getBean(DishCategoryService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        Dish dish = dishService.findById(1);
        assertNotNull(dish);
        assertEquals(1, dish.getId());
    }

    @Test
    public void findAllListSize() throws Exception {
        List<Dish> dishes = dishService.findAll();
//        System.out.println("**************");
//        dishes.forEach(System.out::println);
        assertNotNull(dishes);
        assertEquals(18, dishes.size());
    }

    @Test
    public void addingDishSetsId() throws Exception {
        Dish dish = createTestDishWithPhoto();
        int originalId = dish.getId();
        dishService.save(dish);
        assertNotEquals(originalId, dishService.findById(originalId));
    }

    @Test
    public void addingCustomerIncreasesListSize() throws Exception {
        List<Dish> list1 = dishService.findAll();
        Dish dish = createTestDishWithPhoto();
        dishService.save(dish);
        List<Dish> list2 = dishService.findAll();
        assertEquals(list2.size() - list1.size(), 1);
    }

    @Test
    public void updatingDishName() throws Exception {
        Dish dish = createTestDishWithPhoto();
        dishService.save(dish);
        int dishId = dish.getId();
        Dish fetched = dishService.findById(dishId);
        fetched.setName("Updated Name");
        dishService.save(dish);
        assertEquals("Updated Name", fetched.getName());
    }

    @Test
    public void deletedDishIsNull() throws Exception {
        Dish dish = createTestDishWithPhoto();
        dishService.save(dish);
        int id = dish.getId();
        dishService.delete(id);
        assertNull(dishService.findById(id));
    }

    @Test
    public void findByCategoryReturnsListSize() throws Exception {
        List<Dish> dishes = dishService.findByCategory(1);
        assertNotNull(dishes);
        assertEquals(3, dishes.size());
    }

    @Test
    public void findByAvailabilityTrueReturnsListSize() throws Exception {
        List<Dish> dishes = dishService.findByAvailability(true);
        assertEquals(17, dishes.size());
    }

    @Test
    public void findByAvailabilityFalseReturnsListSize() throws Exception {
        List<Dish> dishes = dishService.findByAvailability(false);
        assertEquals(1, dishes.size());
    }

    private Dish createTestDishWithPhoto() throws IOException {
        Path path = Paths.get(pathPrefixMac + "test_image.png");
        Photo photo = new Photo(Files.readAllBytes(path));
        return new Dish("Test Dish", "Test Dish description", generateRandomPrice(), true, true, photo, dishCategoryService.findById(1));
    }

    private double generateRandomPrice() {
        Random r = new Random();
        double randomPrice = 1 + (10 - 1) * r.nextDouble();
        randomPrice = new BigDecimal(String.valueOf(randomPrice)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return randomPrice;
    }
}