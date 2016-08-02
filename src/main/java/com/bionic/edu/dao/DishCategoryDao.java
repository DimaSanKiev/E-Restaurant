package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;

import java.util.List;

public interface DishCategoryDao extends GenericDao<DishCategory> {

    /**
     * Returns the list of dishes that match the passed category.
     *
     * @param categoryId id of dish category
     * @return list of dishes with the given {@code categoryId} or {@literal null} if none found
     */
    List<Dish> findDishes(int categoryId);
}
