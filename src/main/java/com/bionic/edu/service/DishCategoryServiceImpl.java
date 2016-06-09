package com.bionic.edu.service;

import com.bionic.edu.dao.DishCategoryDao;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DishCategoryServiceImpl implements DishCategoryService {

    @Autowired
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
