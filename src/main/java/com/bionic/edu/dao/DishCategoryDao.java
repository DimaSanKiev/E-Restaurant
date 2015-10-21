package com.bionic.edu.dao;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;

import java.util.List;

public interface DishCategoryDao {

    DishCategory findById(int id);

    List<DishCategory> findAll();

    void add(DishCategory dishCategory);

    void update(DishCategory dishCategory);

    void delete(int id);


    List<Dish> findDishes(int categoryId);

}
