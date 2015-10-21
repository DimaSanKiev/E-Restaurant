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

public class DishCategoryDaoImplTest {

    DishCategoryDao dishCategoryDao;
    ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        dishCategoryDao = (DishCategoryDao) context.getBean("dishCategoryDaoImpl");
    }

    @Test
    public void testFindById() throws Exception {
        DishCategory dishCategory = dishCategoryDao.findById(1);
        assertNotNull(dishCategory);
        assertEquals(1, dishCategory.getId());
    }

    @Test
    public void testFindDishes() throws Exception {
        List<Dish> dishes = dishCategoryDao.findDishes(1);
        assertEquals(3, dishes.size());
    }

}