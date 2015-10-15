package com.bionic.edu.dao;

import com.bionic.edu.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order findById(int id) {
        Order order;
        order = em.find(Order.class, id);
        return order;
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
        return query.getResultList();
    }

    @Override
    public void add(Order order) {
        em.persist(order);
    }

    @Override
    public void update(Order order) {
        em.merge(order);
    }

    @Override
    public void delete(int id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }

    }
}
