package com.bionic.edu.service;

import com.bionic.edu.dao.DishDao;
import com.bionic.edu.entity.Dish;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DishServiceImpl implements DishService {

    @Inject
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

    @Override
    public List<Dish> findByCategory(int categoryId) {
        return dishDao.findByCategory(categoryId);
    }

    @Override
    public List<Dish> findByAvailability(boolean isAvailable) {
        return dishDao.findByAvailability(isAvailable);
    }
}
