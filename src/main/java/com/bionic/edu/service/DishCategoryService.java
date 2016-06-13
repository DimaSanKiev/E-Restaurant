package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import com.bionic.edu.service.generic.GenericService;

import java.util.List;

public interface DishCategoryService extends GenericService<DishCategory> {

    List<Dish> findDishes(int categoryId);

}
