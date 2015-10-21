package com.bionic.edu.dao;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DishCategoryDaoImpl implements DishCategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public DishCategory findById(int id) {
        DishCategory dishCategory;
        dishCategory = em.find(DishCategory.class, id);
        return dishCategory;
    }

    @Override
    public List<DishCategory> findAll() {
        TypedQuery<DishCategory> query = em.createQuery("SELECT dc FROM dish_category dc", DishCategory.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void add(DishCategory dishCategory) {
        em.persist(dishCategory);
    }

    @Override
    @Transactional
    public void update(DishCategory dishCategory) {
        em.merge(dishCategory);
    }

    @Override
    public void delete(int id) {
        DishCategory dishCategory = em.find(DishCategory.class, id);
        if (dishCategory != null) {
            em.remove(dishCategory);
        }
    }

    @Override
    public List<Dish> findDishes(int categoryId) {
        TypedQuery<Dish> query = em.createQuery(
                "SELECT d FROM Dish d WHERE d.category.id =" +
                        ":category_id AND d.available = true", Dish.class);
        query.setParameter("category_id", categoryId);
        return query.getResultList();
    }
}
