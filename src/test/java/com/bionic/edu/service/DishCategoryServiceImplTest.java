package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DishCategoryServiceImplTest {

    private DishCategoryService dishCategoryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        dishCategoryService = context.getBean(DishCategoryService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        DishCategory dishCategory = dishCategoryService.findById(1);
        assertNotNull(dishCategory);
        assertEquals(1, dishCategory.getId());
    }

    @Test
    public void findAllListSize() throws Exception {
        List<Dish> dishes = dishCategoryService.findDishes(1);
        assertEquals(3, dishes.size());
    }
}