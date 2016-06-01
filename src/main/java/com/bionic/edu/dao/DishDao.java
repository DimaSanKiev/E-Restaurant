package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.Dish;

import java.util.List;

public interface DishDao extends DaoInterface<Dish> {

    List<Dish> findByCategory(int categoryId);

    List<Dish> findByAvailability(boolean isAvailable);

}
