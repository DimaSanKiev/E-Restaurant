package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;

import java.util.List;

public interface DishCategoryDao extends DaoInterface<DishCategory> {

    List<Dish> findDishes(int categoryId);
}
