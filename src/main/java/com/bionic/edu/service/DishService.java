package com.bionic.edu.service;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.service.generic.GenericService;

import java.util.List;

public interface DishService extends GenericService<Dish> {

    List<Dish> findByCategory(int categoryId);

    List<Dish> findByAvailability(boolean isAvailable);
}
