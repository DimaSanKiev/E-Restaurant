package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderDishes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderDishesDaoImpl implements OrderDishesDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OrderDishes findById(int id) {
        OrderDishes orderDishes;
        orderDishes = em.find(OrderDishes.class, id);
        return orderDishes;
    }

    @Override
    public List<OrderDishes> findAll() {
        TypedQuery<OrderDishes> query = em.createQuery("SELECT od FROM OrderDishes od", OrderDishes.class);
        return query.getResultList();
    }

    @Override
    public void add(OrderDishes orderDishes) {
        em.persist(orderDishes);
    }

    @Override
    public void update(OrderDishes orderDishes) {
        em.merge(orderDishes);
    }

    @Override
    public void delete(int id) {
        OrderDishes orderDishes = em.find(OrderDishes.class, id);
        if (orderDishes != null) {
            em.remove(orderDishes);
        }
    }
}
