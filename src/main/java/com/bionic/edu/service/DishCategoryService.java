package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;

import java.util.List;

public interface DishCategoryService {

    DishCategory findById(int id);

    List<DishCategory> findAll();

    void save(DishCategory dishCategory);

    void delete(int id);


    List<Dish> findDishes(int categoryId);

}