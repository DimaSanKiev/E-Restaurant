package com.bionic.edu.service;

import com.bionic.edu.dao.DishDao;
import com.bionic.edu.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDao dishDao;

    @Override
    public Dish findById(int id) {
        return dishDao.findById(id);
    }

    @Override
    public List<Dish> findAll() {
        return dishDao.findAll();
    }

    @Transactional
    @Override
    public void save(Dish dish) {
        dishDao.save(dish);
    }

    @Transactional
    @Override
    public void delete(int id) {
        dishDao.delete(id);
    }


    @Transactional
    @Override
    public void changeAvailability(int dishId) {
        Dish dish = dishDao.findById(dishId);
        dish.setAvailable(!dish.isAvailable());
        dishDao.save(dish);
    }

    @Override
    public List<Dish> findByCategory(int categoryId) {
        return dishDao.findByCategory(categoryId);
    }

    @Override
    public List<Dish> findByAvailability(boolean isAvailable) {
        return dishDao.findByAvailability(isAvailable);
    }
}
