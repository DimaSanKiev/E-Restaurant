package com.bionic.edu.dao;

import com.bionic.edu.entity.Dish;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DishDaoImpl implements DishDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Dish findById(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    public List<Dish> findAll() {
        TypedQuery<Dish> query = em.createQuery("SELECT d FROM Dish d", Dish.class);
        return query.getResultList();
    }

    @Override
    public void save(Dish dish) {
        if (dish.getId() == 0) {
            em.persist(dish);
        } else
            em.merge(dish);
    }

    @Override
    public void delete(int id) {
        Dish dish = em.find(Dish.class, id);
        if (dish != null) {
            em.remove(dish);
        }
    }

    @Override
    public List<Dish> findByCategory(int categoryId) {
        TypedQuery<Dish> query = em.createQuery(
                "SELECT d FROM Dish d WHERE d.category.id =" +
                        ":category_id AND d.available = true", Dish.class);
        query.setParameter("category_id", categoryId);
        return query.getResultList();
    }

    @Override
    public List<Dish> findByAvailability(boolean isAvailable) {
        TypedQuery<Dish> query = em.createQuery(
                "SELECT d FROM Dish d WHERE d.available = :availability", Dish.class).
                setParameter("availability", isAvailable);
        return query.getResultList();
    }


}
