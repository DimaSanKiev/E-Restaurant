package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.service.DishCategoryService;
import com.bionic.edu.service.DishService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope("session")
public class DishBean {
    @Inject
    private DishService dishService;
    @Inject
    private DishCategoryService dishCategoryService;
    private List<Dish> dishes;
    private Dish dish;

}
