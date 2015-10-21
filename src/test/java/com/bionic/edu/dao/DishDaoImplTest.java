package com.bionic.edu.dao;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DishDaoImplTest {

    DishDao dishDao;
    DishCategoryDao dishCategoryDao;
    ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        dishDao = (DishDao) context.getBean("dishDaoImpl");
    }

    @Test
    public void testFindById() throws Exception {
        Dish dish = dishDao.findById(1);
        assertNotNull(dish);
        assertEquals(1, dish.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Dish> dishes = dishDao.findAll();
        assertNotNull(dishes);
        assertEquals(18, dishes.size());
    }

    @Test
    public void testAdd() throws Exception {
        Dish dish = new Dish("testDishAdd", "justTestDish", 0.00, true, true, "photo", new DishCategory());
        dishDao.add(dish);
        int id = dish.getId();
        assertNotNull(dishDao.findById(id));
    }

    @Test
    public void testUpdate() throws Exception {
        Dish dish = dishDao.findById(1);
        dish.setName("Test Name");
        dishDao.update(dish);
        assertEquals("Test Name", dish.getName());
    }

    @Test
    public void testDelete() throws Exception {
        // todo NPE
        DishCategory dishCategory = dishCategoryDao.findById(1);
        Dish dish = new Dish("testDishDelete", "justTestDish", 0.00, true, true, "photo", dishCategory);
        dishDao.add(dish);
        int id = dish.getId();
        dishDao.delete(id);
        assertNull(dishDao.findById(id));
    }

    @Test
    public void testFindByCategoryId() throws Exception {
        List<Dish> dishes = dishDao.findByCategory(1);
        assertNotNull(dishes);
        assertEquals(3, dishes.size());
    }

    @Test
    public void testFindByCategoryName() throws Exception {
        List<Dish> dishes = dishDao.findByCategory("SOUP");
        assertNotNull(dishes);
        assertEquals(3, dishes.size());
    }

    @Test
    public void testFindByAvailabilityTrue() throws Exception {
        List<Dish> dishes = dishDao.findByAvailability(true);
        assertEquals(17, dishes.size());
    }

    @Test
    public void testFindByAvailabilityFalse() throws Exception {
        List<Dish> dishes = dishDao.findByAvailability(false);
        assertEquals(1, dishes.size());
    }
}