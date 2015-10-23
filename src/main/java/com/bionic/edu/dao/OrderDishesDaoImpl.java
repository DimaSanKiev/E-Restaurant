package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.entity.Orders;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        TypedQuery<OrderDishes> query = em.createQuery("SELECT od FROM order_dishes od", OrderDishes.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(OrderDishes orderDishes) {
        if (orderDishes.getId() == 0) {
            em.persist(orderDishes);
        } else
            em.merge(orderDishes);
    }

    @Override
    @Transactional
    public void delete(int id) {
        OrderDishes orderDishes = em.find(OrderDishes.class, id);
        if (orderDishes != null) {
            em.remove(orderDishes);
        }
    }

    @Override
    public List<OrderDishes> getAllFromOrder(Orders order) {
        return em.createQuery("SELECT o FROM order_dishes o WHERE o.order.id = :id", OrderDishes.class).
                setParameter("id", order.getId()).getResultList();
    }

    @Override
    public void addKitchenmadeToOrder(OrderDishes orderDishes) {
        // todo
    }

    @Override
    public List<OrderDishes> createListForKitchen() {
        return em.createQuery("SELECT o FROM order_dishes o where o.dish.kitchenmade = true " +
                "AND o.order.orderStatus = :status", OrderDishes.class) // sort by time - oldest first
                .setParameter("status", 1).getResultList();
    }

}
