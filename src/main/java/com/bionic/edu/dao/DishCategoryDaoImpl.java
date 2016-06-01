package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishCategoryDaoImpl extends GenericDao<DishCategory> implements DishCategoryDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> findDishes(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Dish WHERE category.id = :category_id AND available = TRUE");
        query.setParameter("category_id", categoryId);
        return query.list();
    }
}
