package com.bionic.edu.service;

import com.bionic.edu.dao.DishCategoryDao;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

public class DishCategoryServiceImpl implements DishCategoryService {

    @Inject
    private DishCategoryDao dishCategoryDao;

    @Override
    public DishCategory findById(int id) {
        return dishCategoryDao.findById(id);
    }

    @Override
    public List<DishCategory> findAll() {
        return dishCategoryDao.findAll();
    }

    @Transactional
    @Override
    public void save(DishCategory dishCategory) {
        dishCategoryDao.save(dishCategory);
    }

    @Transactional
    @Override
    public void delete(int id) {
        dishCategoryDao.delete(id);
    }


    @Override
    public List<Dish> findDishes(int categoryId) {
        return dishCategoryDao.findDishes(categoryId);
    }
}
