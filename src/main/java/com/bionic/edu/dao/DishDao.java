package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Dish;

import java.util.List;

public interface DishDao extends GenericDao<Dish> {

    /**
     * Returns the list of dishes that match the passed category.
     *
     * @param categoryId id of dish category
     * @return list of dishes with the given {@code categoryId} or {@literal null} if none found
     */
    List<Dish> findByCategory(int categoryId);

    /**
     * Returns the list of dishes that match the passed availability parameter.
     *
     * @param isAvailable {@literal true} for available dishes, {@literal false} otherwise
     * @return list of dishes with the given {@code isAvailable} parameter
     * or {@literal null} if none found
     */
    List<Dish> findByAvailability(boolean isAvailable);
}
