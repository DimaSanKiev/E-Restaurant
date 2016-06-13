package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import com.bionic.edu.service.generic.ServiceInterface;

import java.util.List;

public interface DishCategoryService extends ServiceInterface<DishCategory> {

    List<Dish> findDishes(int categoryId);

}
