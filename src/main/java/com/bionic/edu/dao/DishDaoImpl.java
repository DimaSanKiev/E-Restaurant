package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Dish;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishDaoImpl extends GenericDao<Dish> implements DishDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> findByCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Dish WHERE category.id = :category_id AND available = TRUE");
        query.setParameter("category_id", categoryId);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> findByAvailability(boolean isAvailable) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Dish WHERE available = :availability");
        query.setParameter("availability", isAvailable);
        return query.list();
    }
}
