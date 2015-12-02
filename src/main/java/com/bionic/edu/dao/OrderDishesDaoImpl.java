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
        return em.find(OrderDishes.class, id);
    }

    @Override
    public List<OrderDishes> findAll() {
        TypedQuery<OrderDishes> query = em.createQuery("SELECT od FROM order_dishes od", OrderDishes.class);
        return query.getResultList();
    }

    @Override
    public void save(OrderDishes orderDishes) {
        if (orderDishes.getId() == 0) {
            em.persist(orderDishes);
        } else
            em.merge(orderDishes);
    }

    @Override
    public void delete(int id) {
        OrderDishes orderDishes = em.find(OrderDishes.class, id);
        if (orderDishes != null) {
            em.remove(orderDishes);
        }
    }


    @Override
    public List<OrderDishes> getAllDishesFromOrder(int orderId) {
        return em.createQuery("SELECT od FROM order_dishes od WHERE od.order.id = :id", OrderDishes.class).
                setParameter("id", orderId).getResultList();
    }

    @Override
    public List<OrderDishes> getUndoneDishesFromOrder(int orderId) {
        return em.createQuery("SELECT od FROM order_dishes od " +
                "WHERE od.readiness = FALSE AND od.order.id = :order_id", OrderDishes.class).
                setParameter("order_id", orderId).getResultList();
    }

    @Override
    public List<OrderDishes> getKitchenPendingList() {
        return em.createQuery("SELECT od FROM order_dishes od " +
                "WHERE od.dish.kitchenmade = TRUE AND od.readiness = FALSE " +
                "ORDER BY od.order.dateTimeTaken ASC", OrderDishes.class).getResultList();
    }
}
